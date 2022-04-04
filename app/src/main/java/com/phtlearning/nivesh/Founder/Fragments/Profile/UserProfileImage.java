package com.phtlearning.nivesh.Founder.Fragments.Profile;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.phtlearning.nivesh.Founder.Fragments.RaiseFund.DescriptionFragment;
import com.phtlearning.nivesh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserProfileImage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserProfileImage extends Fragment {
    ImageView ProfileImageView;
    Button ProfilePickImageBtn, ProfileImageNxtBtn;
    ActivityResultLauncher<String> mGetContent;
    String ProfileImage;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserProfileImage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFrofileImage.
     */
    // TODO: Rename and change types and number of parameters
    public static UserProfileImage newInstance(String param1, String param2) {
        UserProfileImage fragment = new UserProfileImage();
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
        View view =  inflater.inflate(R.layout.fragment_user_frofile_image, container, false);
        String UserName = getArguments().getString("UserName");
        String UserProfession = getArguments().getString("UserProfession");
        String UserDOB = getArguments().getString("UserDOB");
        String UserGender = getArguments().getString("UserGender");

        ProfileImageView = view.findViewById(R.id.profile_image_view);
        ProfilePickImageBtn = (Button)view.findViewById(R.id.profile_image_btn);
        ProfileImageNxtBtn = (Button)view.findViewById(R.id.profile_next_btn);

        ProfilePickImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(getActivity())
                        .crop()
                        .galleryOnly()//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start(2);
            }
        });

        ProfileImageNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutMe obj = new AboutMe();
                Bundle args = new Bundle();
                args.putString("UserName", UserName);
                args.putString("UserProfession", UserProfession);
                args.putString("UserDOB", UserDOB);
                args.putString("UserGender", UserGender);
                args.putString("ProfileImage", ProfileImage);
                obj.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, obj).commit();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2)
        {
            Uri uri = data.getData();
            if(uri == null)
            {
                Toast.makeText(getApplicationContext(), "Please Select Image", Toast.LENGTH_LONG).show();
            }
            else
            {
                ProfileImageView.setImageURI(uri);
                ProfileImage = uri.toString();
                ProfilePickImageBtn.setText("Change Profile Image");
                ProfileImageNxtBtn.setVisibility(View.VISIBLE);
            }


        }

    }
}