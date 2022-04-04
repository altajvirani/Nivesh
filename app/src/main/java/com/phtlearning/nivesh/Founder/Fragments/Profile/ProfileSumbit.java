package com.phtlearning.nivesh.Founder.Fragments.Profile;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.phtlearning.nivesh.Founder.DatabaseHelper.ProfileHelper;
import com.phtlearning.nivesh.Founder.DatabaseHelper.RaiseFundHelper;
import com.phtlearning.nivesh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileSumbit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileSumbit extends Fragment {
    Button SubmitButton;
    DatabaseReference founderReference, userTypeReference;
    StorageReference storageReference;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileSumbit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileSumbit.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileSumbit newInstance(String param1, String param2) {
        ProfileSumbit fragment = new ProfileSumbit();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_sumbit, container, false);
        ProgressDialog progressDialog =  new ProgressDialog(getContext());
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");

        SubmitButton = (Button)view.findViewById(R.id.profile_submit);

        String UserName = getArguments().getString("UserName");
        String UserProfession = getArguments().getString("UserProfession");
        String UserDOB = getArguments().getString("UserDOB");
        String UserGender = getArguments().getString("UserGender");
        String ProfileImage = getArguments().getString("ProfileImage");
        String AboutMe = getArguments().getString("AboutMe");
        String ContactNumber = getArguments().getString("ContactNumber");

        Uri ProfileImageUri = Uri.parse(ProfileImage);

        userTypeReference = FirebaseDatabase.getInstance().getReference("UserType");
        founderReference = FirebaseDatabase.getInstance().getReference().child("Founder");
        storageReference = FirebaseStorage.getInstance().getReference();

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                StorageReference fileref = storageReference.child(System.currentTimeMillis() + "." +getFileExtension(ProfileImageUri));

                fileref.putFile(ProfileImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String CurrentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                userTypeReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String UserType = snapshot.child(CurrentUserUid).child("userType").getValue().toString();
                                        if(UserType.equals("Founder"))
                                        {
                                            ProfileHelper profileHelper = new ProfileHelper(UserName,UserProfession,UserDOB,UserGender,uri.toString(),AboutMe,"+91 "+ContactNumber);

                                            founderReference.child(CurrentUserUid).child("Profile").setValue(profileHelper);

                                            progressDialog.hide();
                                        }
                                        else
                                        {
                                            progressDialog.hide();
                                            Toast.makeText(getContext(), "User Not Found!", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        progressDialog.hide();
                                        Toast.makeText(getContext(), "Please Check Your Internet Connectivity!", Toast.LENGTH_SHORT).show();
                                    }
                                });


                            }
                        });
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.hide();
                        Toast.makeText(getContext(), "Uploading Failed!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }

    private String getFileExtension(Uri companyLogoUri) {
        ContentResolver contentResolver = getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(companyLogoUri));

    }
}