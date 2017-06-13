package com.example.aliosama.contacts.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aliosama.contacts.Model.ContactHelper;
import com.example.aliosama.contacts.Model.ContactModel;
import com.example.aliosama.contacts.R;
import com.example.aliosama.contacts.RecAdapter.RecyclerAdapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int CAMERA_REQUEST = 2;
    ContactHelper mContactHelper;
    RecyclerView RVContacts ;
    RecyclerAdapter mRecyclerAdapter;
    LinearLayoutManager mLinearLayoutManager;
    ArrayList<ContactModel> data;
    EditText ETContactName , ETContactPhone;
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RVContacts = (RecyclerView) findViewById(R.id.RecViewOfContacts);
        data = new ArrayList<>();
        mContactHelper = new ContactHelper(this);
        mImageView = (ImageView) findViewById(R.id.ContactImage);
        ETContactName = (EditText) findViewById(R.id.ETName);
        ETContactPhone = (EditText) findViewById(R.id.ETPhone);


        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence items[] = {"Gallery","Camera","Cancel"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("choose Image..");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Choose = (String) items[which];
                        switch (Choose){
                            case "Gallery":
                                Gallery();
                                break;
                            case "Camera":
                                Camera();
                                break;
                            case "Cancel":
                                dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });
        try{
           ShowData();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void Gallery(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(i.ACTION_GET_CONTENT);
        startActivityForResult(i.createChooser(i, "Select Picture"), RESULT_LOAD_IMAGE);
    }
    private void Camera(){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(imageBitmap);
        }else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
                mImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void onAddListener(View v){
        try {
            if(ETContactName.getText().toString().isEmpty() || ETContactPhone.getText().toString().isEmpty()){
                Toast.makeText(this, " Plese Enter Data ", Toast.LENGTH_SHORT).show();
            }else{
                mContactHelper.InsertContact(new ContactModel(ETContactName.getText().toString(),ETContactPhone.getText().toString(),
                        ConvertImageToByte(((BitmapDrawable)mImageView.getDrawable()).getBitmap())));
            }
            ETContactName.setText("");
            ETContactPhone.setText("");
            mImageView.setImageResource(R.mipmap.ic_launcher);
//            onViewListener(v);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void onViewListener(View v){
        try {
            ArrayList<ContactModel> data;
            data = mContactHelper.ViewContacts();
            mLinearLayoutManager = new LinearLayoutManager(this);
            RVContacts.setLayoutManager(mLinearLayoutManager);
            mRecyclerAdapter = new RecyclerAdapter(getBaseContext(),data);
            RVContacts.setAdapter(mRecyclerAdapter);
            ETContactName.setText("");
            ETContactPhone.setText("");
            mImageView.setImageResource(R.mipmap.ic_launcher);
        }catch (Exception e){
            Toast.makeText(this, " ViewListener ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    public void ShowData(){
        try {
            ArrayList<ContactModel> data;
            data = mContactHelper.ViewContacts();
            mLinearLayoutManager = new LinearLayoutManager(this);
//            RVContacts.setLayoutManager(mLinearLayoutManager);
            mRecyclerAdapter = new RecyclerAdapter(getBaseContext(),data);
            RVContacts.setAdapter(mRecyclerAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static byte[] ConvertImageToByte(Bitmap image){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte imageInByte[] = stream.toByteArray();
        return imageInByte;
    }

}
