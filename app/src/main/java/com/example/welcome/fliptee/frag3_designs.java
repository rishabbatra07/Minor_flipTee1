package com.example.welcome.fliptee;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class frag3_designs extends android.support.v4.app.Fragment {
    private static int request=1;
    String ImageDecode;
    TextView textView;
    Button btnHide,btnShow;
    SlidingUpPanelLayout slidingLayout;
    final int [] iv_int={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.drawable.scripalt,R.drawable.walt2};
    String[] FILE;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_frag3_designs,container,false);
        //LinearLayout lin=(LinearLayout)getActivity().findViewById(R.id.dragView);
        //lin.setVisibility(View.VISIBLE);
        Button but_gall=(Button)v.findViewById(R.id.id_gallery_designs);
        Button but_des=(Button)v.findViewById(R.id.button_grid_designs);
        //textView=(TextView)getActivity().findViewById(R.id.id_text_slide);
        but_gall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,request);
            }

        });
        but_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //frag_grid_recycler f=new frag_grid_recycler();
                //RecyclerView rv=(RecyclerView)getActivity().findViewById(R.id.recycler_customer);
                //rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                //rv.setAdapter(new CustomAdapter());


            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if (requestCode == request && resultCode == RESULT_OK && data != null)
        if(data!=null)
        {
            Uri URI = data.getData();
            String[] FILE = { MediaStore.Images.Media.DATA };
            Cursor cursor =getActivity(). getContentResolver().query(URI, FILE, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(FILE[0]);
            ImageDecode = cursor.getString(columnIndex);
            cursor.close();
            final Dialog dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_gallery);
            Button okButton=(Button)dialog.findViewById(R.id.id_dialog_ok);
            Button cancelButton=(Button)dialog.findViewById(R.id.id_dialog_cancal);
            ImageView iv=(ImageView)dialog.findViewById(R.id.id_dialog_gallery);
            iv.setImageBitmap(BitmapFactory.decodeFile(ImageDecode));
            //but_val=(Button)findViewById(R.id.button_validate);
            //TextView tv1=(TextView)findViewById(R.id.text_title);
            //tv1.setVisibility(View.VISIBLE);
            //but_val.setVisibility(View.VISIBLE);
            okButton.setOnClickListener(new View.OnClickListener() {       //here add to admin database for validation
                @Override
                public void onClick(View v) {
                    //Toast.makeText(designerActivity.this,"Submitted",Toast.LENGTH_LONG).show();
                }
            });
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 dialog.dismiss();
                }
            });
            dialog.show();
        }
        else
        {
            Toast.makeText(getActivity(),"Could Not Fetch Image",Toast.LENGTH_LONG).show();
        }
    }
    class CustomViewHolder
            extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView textView;


        CustomViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            imageView = (ImageView) view.findViewById(R.id.item_grid_image);
        }

        public void bindData(int position) {
            //imageView.setImageResource(R.mipmap.ic_launcher);
            imageView.setImageResource(iv_int[position]);//this also haas to be removed after we have database
        }
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_recycler_images, parent, false);
            return new CustomViewHolder(view);
        }
        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            holder.bindData(position);
        }
        @Override
        public int getItemCount() {
            return iv_int.length;//thi sarray has to be removed after database
        }
    }
}
