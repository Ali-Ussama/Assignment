package com.example.aliosama.contacts.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aliosama.contacts.Model.ContactHelper;
import com.example.aliosama.contacts.Model.ContactModel;
import com.example.aliosama.contacts.R;

public class EditActivity extends AppCompatActivity {
    EditText Name, Phone;
    ImageView ContactImage;
    Intent intent;
    ContactModel mContactModel;
    ContactHelper mContactHelper;
    byte[] imagebyte;
    Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        try {
            mContactHelper = new ContactHelper(this);
            intent = getIntent();

            mContactModel = (ContactModel) intent.getSerializableExtra("data");
            Name = (EditText) findViewById(R.id.UpdateNameET);
            Phone = (EditText) findViewById(R.id.UpdatePhoneET);
            ContactImage = (ImageView) findViewById(R.id.UpdateImageView);

            Name.setText(mContactModel.getName());
            Phone.setText(mContactModel.getPhone());
            imagebyte = mContactModel.getContactImage();
            bmp = BitmapFactory.decodeByteArray(imagebyte, 0, imagebyte.length);
            ContactImage.setImageBitmap(bmp);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void onUpdateListener(View v){
        try{
            if(Name.getText().toString().isEmpty() || Phone.getText().toString().isEmpty()){
                Toast.makeText(this, " Please Enter Data ", Toast.LENGTH_SHORT).show();
            }else{
                mContactHelper.UpdateContact(new ContactModel(Name.getText().toString(),Phone.getText().toString(),imagebyte));
            }
        }catch (Exception e){
            Toast.makeText(this, " OnUpdateListener Exception ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void onDeleteListener(View v){
        try{
            if(Name.getText().toString().isEmpty()){
                Toast.makeText(this, "Please Enter Data ", Toast.LENGTH_SHORT).show();
            }else{
                mContactHelper.DeleteContact(Name.getText().toString());
            }

        }catch (Exception e){
            Toast.makeText(this, " OnDeleteListener Exception ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
