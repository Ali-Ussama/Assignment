package com.example.aliosama.contacts.RecAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aliosama.contacts.Activities.EditActivity;
import com.example.aliosama.contacts.Model.ContactModel;
import com.example.aliosama.contacts.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aliosama on 5/3/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomHolder> {
    Context mContext;
    ArrayList<ContactModel> data;
    public RecyclerAdapter(Context context, ArrayList<ContactModel> data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public RecyclerAdapter.CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflateView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item,null);
        CustomHolder customHolder = new CustomHolder(inflateView,mContext,data);
        return customHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.CustomHolder holder, int position) {
        holder.itemContactName.setText(data.get(position).getName());
        holder.itemContectPhone.setText(data.get(position).getPhone());
        byte[] iamgebyte = data.get(position).getContactImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(iamgebyte, 0, iamgebyte.length);
        holder.ItemImage.setImageBitmap(bmp);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemContactName,itemContectPhone;
        ImageView ItemImage;
        Context context;
        ArrayList<ContactModel> data;
        public CustomHolder(View itemView,Context context,ArrayList<ContactModel> data) {
            super(itemView);
            itemContactName = (TextView)itemView.findViewById(R.id.ItemContactName);
            itemContectPhone = (TextView)itemView.findViewById(R.id.ItemContactPhone);
            ItemImage = (ImageView)itemView.findViewById(R.id.ItemContactImage);
            this.context = context;
            this.data = data;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v){
           try{
               Intent intent = new Intent(this.context, EditActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               intent.putExtra("data",new ContactModel(data.get(getAdapterPosition()).getName(),data.get(getAdapterPosition()).getPhone(),
                       data.get(getAdapterPosition()).getContactImage()));
               this.context.startActivity(intent);
            }catch (Exception e){
               e.printStackTrace();
           }

        }
    }
}
