package com.example.welcome.fliptee;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rtugeek.android.colorseekbar.ColorSeekBar;

public class frag2_clipart extends android.support.v4.app.Fragment {
    private RecyclerView recyclerView,horizontalList;
    TextView tv1;
    EditText et;
    ColorSeekBar colorSeekBar;
    final int [] iv_int={R.drawable.adine,R.drawable.scripalt,R.drawable.walt2,R.drawable.ymms,R.drawable.adine,R.drawable.scripalt,R.drawable.walt2,R.drawable.ymms};
    final String [] iv_text={"fonts/AdineKirnberg.ttf","fonts/SCRIPALT.ttf","fonts/waltograph42.ttf","fonts/YouMakeMeSmile.ttf","fonts/AdineKirnberg.ttf","fonts/SCRIPALT.ttf","fonts/waltograph42.ttf","fonts/YouMakeMeSmile.ttf"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_frag2_clipart,container,false);
        //LinearLayout lin=(LinearLayout)getActivity().findViewById(R.id.dragView);
        //lin.setVisibility(View.INVISIBLE);
        //tv1=(TextView)vCust.findViewById(R.id.clipartTry);
        //Typeface typeface= Typeface.createFromAsset(getActivity().getAssets(),"fonts/AdineKirnberg.ttf");
        //tv1.setTypeface(typeface);
        recyclerView = (RecyclerView)v. findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));
        recyclerView.setAdapter(new CustomAdapter());
        et=(EditText)v.findViewById(R.id.eT);
        Button but=(Button)v.findViewById(R.id.text_button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv=(TextView) getActivity().findViewById(R.id.clipartTry);
                String str=et.getText().toString();
                tv.setText(str);
            }
        });
        colorSeekBar=(ColorSeekBar) getActivity().findViewById(R.id.id_colorTextSlider);
        colorSeekBar.setMaxValue(255);
        colorSeekBar.setColors(R.array.material_colors); // material_colors is defalut included in res/color,just use it.
        colorSeekBar.setColorBarValue(0); //0 - maxValue
        //colorSeekBar.setAlphaBarValue(0); //0-255
        //colorSeekBar.setShowAlphaBar(true);
        colorSeekBar.setBarHeight(5); //5dpi
        colorSeekBar.setThumbHeight(30); //30dpi
        //colorSeekBar.setBarMargin(10); //set the margin between colorBar and alphaBar 10dpi
        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int colorBarValue, int alphaBarValue, int color) {
                //ImageView iv1=(ImageView)getActivity().findViewById(R.id.image_tshirt);
                TextView tv=(TextView)getActivity().findViewById(R.id.clipartTry);
                //iv1.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccent));
                // iv1.setColorFilter(ContextCompat.getColor(getContext(),color ));
                //tv.setColorFilter(color);
                tv.setTextColor(color);
                //textView=(TextView)v.findViewById(R.id.testcolor);
                //textView.setTextColor(color);
            }
        });
        return v;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView itemText;
        CustomViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            imageView = (ImageView) view.findViewById(R.id.item_img);
            //itemText=(TextView)view.findViewById(R.id.item_img_text);
        }

        public void bindData(int position) {
            imageView.setImageResource(iv_int[position]);
            //itemText.setText(list.get(position));
        }
        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(), "ON click", Toast.LENGTH_SHORT).show();
            final Typeface typeface;
            Context cxt = view.getContext();
            int pos=recyclerView.getChildLayoutPosition(view);
            Toast.makeText(getActivity(),String.valueOf(pos), Toast.LENGTH_SHORT).show();
               // Toast.makeText(getActivity(),String.valueOf(pos), Toast.LENGTH_SHORT).show();
            TextView tv1 = (TextView)getActivity().findViewById(R.id.clipartTry);
            EditText et=(EditText)getActivity().findViewById(R.id.eT);
            SeekBar skbar=(SeekBar)getActivity().findViewById(R.id.seekbar_height);
            skbar.setVisibility(View.VISIBLE);
            colorSeekBar.setVisibility(View.VISIBLE);
            skbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    TextView tv1 = (TextView)getActivity().findViewById(R.id.clipartTry);
                    tv1.setTextSize(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
                typeface = Typeface.createFromAsset(getActivity().getAssets(), iv_text[pos]);
                tv1.setTypeface(typeface);

        }

    }
    class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_images, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            holder.bindData(position);
        }

        @Override
        public int getItemCount() {
            return iv_int.length;
        }
    }
}
