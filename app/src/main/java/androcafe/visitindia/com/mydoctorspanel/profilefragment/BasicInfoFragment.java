package androcafe.visitindia.com.mydoctorspanel.profilefragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import androcafe.visitindia.com.mydoctorspanel.HomeActivity;
import androcafe.visitindia.com.mydoctorspanel.OnBackPressed;
import androcafe.visitindia.com.mydoctorspanel.R;
import androcafe.visitindia.com.mydoctorspanel.SignInActivity;

public class BasicInfoFragment extends Fragment implements OnBackPressed {

    EditText edt_name,edt_address,edt_phone_no;
    Button btn_save;
    CircleImageView imageView_doc_profile;
    TextView tvSkip;

    FloatingActionButton fbChangePhoto;
    RelativeLayout rl_profile;
    
    SharedPreferences sharedPreferences;

    private int GALLERY = 1, CAMERA = 2;

    private static final String IMAGE_DIRECTORY = "/docpanel";

    String docImage="";

    public static BasicInfoFragment newInstance() {

        Bundle args = new Bundle();

        BasicInfoFragment fragment = new BasicInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile_basic_info,null);

        sharedPreferences=getActivity().getSharedPreferences(SignInActivity.MYDOCPREF, Context.MODE_PRIVATE);

        imageView_doc_profile=view.findViewById(R.id.iv_doc_profile);
        
        edt_name=view.findViewById(R.id.edt_name);
        edt_address=view.findViewById(R.id.edt_address);
        edt_phone_no=view.findViewById(R.id.edt_phone_no);

        tvSkip=view.findViewById(R.id.tv_skip);

        btn_save=view.findViewById(R.id.btn_save);

        fbChangePhoto=view.findViewById(R.id.fb_change_image);
        rl_profile=view.findViewById(R.id.rl_profile);

        rl_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name="";
                if(edt_name.getText().toString().length()>0) {
                   name  = edt_name.getText().toString();
                }
                String address="";
                if(edt_address.getText().toString().length()>0)
                {
                    address=edt_address.getText().toString();
                }
                String phoneno="";
                if(edt_phone_no.getText().toString().length()>0){
                    phoneno=edt_phone_no.getText().toString();
                }

                saveDataToSharedPref(name,address,phoneno);
                //saveDocProfileData(name,address,phoneno);

                Fragment fragmentEduFragment=EducationFragment.newInstance();
                getActivity().getSupportFragmentManager()
                      .beginTransaction()
                      .replace(R.id.framelayout_profile,fragmentEduFragment)
                      .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                      .addToBackStack(null)
                      .commit();
            }
        });

        fbChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome=new Intent(getActivity(), HomeActivity.class);
                startActivity(intentHome);
            }
        });

        return view;
    }

    private void checkPermissions() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, CAMERA);
        }
    }


    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getContext());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                checkPermissions();
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA);
    }

    private void saveDataToSharedPref(String name, String address, String phoneno) {

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("PHOTO",docImage);
        editor.putString("NAME",name);
        editor.putString("ADDRESS",address);
        editor.putString("PHONENO",phoneno);
        editor.commit();
      
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri selectedImage = data.getData();

                try {
                    Bitmap photo = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    imageView_doc_profile.setImageBitmap(photo);

                    docImage=getStringImage(photo);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } else if (requestCode == CAMERA && resultCode == Activity.RESULT_OK) {

                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    imageView_doc_profile.setImageBitmap(photo);

                    docImage=getStringImage(photo);
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void hideKeyboard(View view) {
        // Get the input method manager
        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        // Hide the soft keyboard
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    @Override
    public void onBackPressed() {

    }
}
