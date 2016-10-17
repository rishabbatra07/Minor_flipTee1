package com.example.welcome.fliptee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rtugeek.android.colorseekbar.ColorSeekBar;

public class frag1_color extends android.support.v4.app.Fragment {
    ColorSeekBar colorSeekBar;
    TextView textView;
    View v;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

         v=inflater.inflate(R.layout.fragment_frag1_color,container,false);
        //LinearLayout lin=(LinearLayout)getActivity().findViewById(R.id.dragView);
        //lin.setVisibility(View.INVISIBLE);
        colorSeekBar=(ColorSeekBar)v.findViewById(R.id.id_colorSlider);
        colorSeekBar.setMaxValue(255);
        colorSeekBar.setColors(R.array.material_colors); // material_colors is defalut included in res/color,just use it.
        colorSeekBar.setColorBarValue(0); //0 - maxValue
        colorSeekBar.setAlphaBarValue(0); //0-255
        colorSeekBar.setShowAlphaBar(true);
        colorSeekBar.setBarHeight(10); //5dpi
        colorSeekBar.setThumbHeight(40); //30dpi
        colorSeekBar.setBarMargin(10); //set the margin between colorBar and alphaBar 10dpi
        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int colorBarValue, int alphaBarValue, int color) {
                ImageView iv1=(ImageView)getActivity().findViewById(R.id.image_tshirt);
                //iv1.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccent));
               // iv1.setColorFilter(ContextCompat.getColor(getContext(),color ));
                iv1.setColorFilter(color);
                //textView=(TextView)v.findViewById(R.id.testcolor);
                //textView.setTextColor(color);
            }
        });
        return v;
    }
}
