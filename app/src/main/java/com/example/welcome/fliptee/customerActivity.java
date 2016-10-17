package com.example.welcome.fliptee;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rtugeek.android.colorseekbar.ColorSeekBar;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class customerActivity extends AppCompatActivity implements TaskDelegate {
    final int[] iv_int = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.drawable.scripalt, R.drawable.walt2};
    ArrayList<String> arrayList;
    private SlidingUpPanelLayout slidingLayout;//sl
    private Button btnShow;//sl
    private Button btnHide;//sl
    private TextView textView;//sl
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String imgurl, type;
    ArrayList<DrawerItem> mDrawerItemList;
    RecyclerView recyclerView;
    Toolbar mToolbar;
    ViewPager vp;
    ImageView iv_tee;
    LinearLayout lin;
    ColorSeekBar colorSeekBar;
    BitmapFactory.Options bmOptions;


    @Override
    public void sendData(String result) {
      /*  editor.putString("IMGURL",imgurl);
        editor.putString("TYPES",type);

        editor.commit();*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = new ArrayList<String>();
        setContentView(R.layout.activity_customer);
        new FetchUrl(getApplicationContext()).execute();
        iv_tee = (ImageView) findViewById(R.id.image_tshirt);
        iv_tee.setImageResource(R.mipmap.whtee);
        bmOptions = new BitmapFactory.Options();
        vp = (ViewPager) findViewById(R.id.id_viewPager);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                slidingLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
                if (position == 2) {
                    slidingLayout.setPanelHeight(60);
                    //slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
                } else {
                    //slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                    slidingLayout.setPanelHeight(0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mDrawerItemList = new ArrayList<DrawerItem>();
        DrawerItem item = new DrawerItem();
        DrawerItem item2 = new DrawerItem();
        DrawerItem item3 = new DrawerItem();
        DrawerItem item4 = new DrawerItem();
        DrawerItem item5 = new DrawerItem();

        item.setIcon(R.mipmap.ic_launcher);
        mDrawerItemList.add(item);
        item2.setIcon(R.mipmap.ic_launcher);
        mDrawerItemList.add(item2);
        item.setIcon(R.mipmap.ic_launcher);
        mDrawerItemList.add(item3);
        item2.setIcon(R.mipmap.ic_launcher);
        mDrawerItemList.add(item4);
        item.setIcon(R.mipmap.ic_launcher);
        mDrawerItemList.add(item5);

        DrawerAdapter adapter = new DrawerAdapter(mDrawerItemList);
        recyclerView = (RecyclerView) findViewById(R.id.drawerRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        //drawerLayout=new ActionBarDrawerToggle()
    }

    private SlidingUpPanelLayout.PanelSlideListener onSlideListener() {
        return new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View view, float v) {
                //textView.setText("panel is sliding");
            }

            @Override
            public void onPanelCollapsed(View view) {
                //textView.setText("panel Collapse");
                Button b = (Button) findViewById(R.id.button_pull_push);
                b.setText("SLIDE ME UPWARDS!!!!");
                Log.i("HERE", "coll");
            }

            @Override
            public void onPanelExpanded(View view) {
                Button b = (Button) findViewById(R.id.button_pull_push);
                b.setText("SLIDE ME DOWNWARDS!!!!");
                Log.i("HERE", "exp");
            }

            @Override
            public void onPanelAnchored(View view) {
            }

            @Override
            public void onPanelHidden(View view) {
            }
        };
    }

    private class CustomAdapter extends FragmentPagerAdapter {
        private String[] fragments = {"frag1", "frag2", "frag3"};

        public CustomAdapter(FragmentManager suppFragMan, Context appContext) {
            super(suppFragMan);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    //slidingLayout.setPanelHeight(0);
                    return new frag1_color();
                case 1:
                    //slidingLayout.setPanelHeight(0);
                    return new frag2_clipart();
                case 2:
                    //slidingLayout.setPanelHeight(20);
                    return new frag3_designs();
                default:
                    return null;
                //return new frag1_color();
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }
    }

    class CustomViewHolder////////////////////////////////
            extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textView;

        CustomViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            imageView = (ImageView) view.findViewById(R.id.item_grid_image);
        }

        public void bindData(int position) {
            try {
                new DownloadImageTask(imageView).execute(arrayList.get(position));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*public void bindData1(int position) {
            imageView.setImageResource(iv_int[position]);//this also haas to be removed after we have database
        }*/
        @Override
        public void onClick(View view) {
            ImageView img_from_grid=(ImageView)findViewById(R.id.image_from_grid);
            int k=getAdapterPosition();
            try {
                new DownloadImageTask(img_from_grid).execute(arrayList.get(k));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //img_from_grid.setImageResource(arrayList.get(k));

        }
    }

    class CustomAdapter_recycler extends RecyclerView.Adapter<CustomViewHolder> {////////////////

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_recycler_images, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            holder.bindData(position);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();//thi sarray has to be removed after database
        }
    }

    public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder> {
        private ArrayList<DrawerItem> drawerMenuList;

        public DrawerAdapter(ArrayList<DrawerItem> drawerMenuList) {
            this.drawerMenuList = drawerMenuList;
        }

        @Override
        public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_kart, parent, false);
            return new DrawerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DrawerViewHolder holder, int position) {
            // holder.title.setText(drawerMenuList.get(position).getTitle());
            holder.icon.setImageResource(drawerMenuList.get(position).getIcon());
        }

        @Override
        public int getItemCount() {
            return drawerMenuList.size();
        }

        class DrawerViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            ImageView icon;

            public DrawerViewHolder(View itemView) {
                super(itemView);
                //title = (TextView) itemView.findViewById(R.id.title);
                icon = (ImageView) itemView.findViewById(R.id.imageView_kart);
            }
        }
    }

    public class DrawerItem {
        private int icon;

        //private String title;
        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }
    }

    void reflect() {
        vp.setAdapter(new CustomAdapter(getSupportFragmentManager(), getApplicationContext()));
        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView_grid_slider);//////////////
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        slidingLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        slidingLayout.setPanelHeight(0);
        slidingLayout.setPanelSlideListener(onSlideListener());
        slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        rv.setAdapter(new CustomAdapter_recycler());
    }

    class FetchUrl extends AsyncTask<String, String, JSONArray> {
        private Context context;
        public TaskDelegate delegate;

        public FetchUrl(Context context) {
            this.context = context;
        }

        @Override
        protected JSONArray doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("url", "trying")
                    .build();
            Request request = new Request.Builder()
                    .url("http://agenta1.pythonanywhere.com/getalldesign/")
                    .post(body)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                String jsonData = response.body().string();
                try {
                    Log.d("debugkaro", jsonData);
                    JSONArray jsonArray = new JSONArray(jsonData);
                    return jsonArray;
                } catch (JSONException e) {
                    Log.d("debugkaro", e.toString());
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONArray res) {
            super.onPostExecute(res);
            String result = "";
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < res.length(); i++) {
                try {
                    Log.d("debugkaro", i + "");
                    jsonObject = res.getJSONObject(i);
                    arrayList.add(jsonObject.getString("imageUrl"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            reflect();
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            if (result.equals("success")) {
                delegate.sendData(result);
            }
            //  SignUpActivity.progressDialog.dismiss();
        }
    }
    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

/*public class customerActivity extends AppCompatActivity {
    final int [] iv_int={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.drawable.scripalt,R.drawable.walt2};
    private SlidingUpPanelLayout slidingLayout;//sl
    private Button btnShow;//sl
    private Button btnHide;//sl
    private TextView textView;//sl
    ArrayList<DrawerItem> mDrawerItemList;
    RecyclerView recyclerView;
    Toolbar mToolbar;
    ViewPager vp;
    ImageView iv_tee;
    LinearLayout lin;
    ColorSeekBar colorSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        iv_tee=(ImageView)findViewById(R.id.image_tshirt);
        iv_tee.setImageResource(R.mipmap.whtee);
        vp=(ViewPager)findViewById(R.id.id_viewPager);
        vp.setAdapter(new CustomAdapter(getSupportFragmentManager(),getApplicationContext()));
        RecyclerView rv=(RecyclerView)findViewById(R.id.recyclerView_grid_slider);//////////////
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        slidingLayout=(SlidingUpPanelLayout)findViewById(R.id.sliding_layout);
        slidingLayout.setPanelHeight(0);
        slidingLayout.setPanelSlideListener(onSlideListener());
        slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        rv.setAdapter(new CustomAdapter_recycler());
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                slidingLayout=(SlidingUpPanelLayout)findViewById(R.id.sliding_layout);
                if(position==2 ) {
                    slidingLayout.setPanelHeight(60);
                    //slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
                }
                else {
                    //slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                    slidingLayout.setPanelHeight(0);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mDrawerItemList = new ArrayList<DrawerItem>();
        DrawerItem item = new DrawerItem();
        DrawerItem item2 = new DrawerItem();
        DrawerItem item3 = new DrawerItem();
        DrawerItem item4 = new DrawerItem();
        DrawerItem item5 = new DrawerItem();

        item.setIcon(R.mipmap.ic_launcher);
        mDrawerItemList.add(item);
        item2.setIcon(R.mipmap.ic_launcher);
        mDrawerItemList.add(item2);
        item.setIcon(R.mipmap.ic_launcher);
        mDrawerItemList.add(item3);
        item2.setIcon(R.mipmap.ic_launcher);
        mDrawerItemList.add(item4);
        item.setIcon(R.mipmap.ic_launcher);
        mDrawerItemList.add(item5);

        DrawerAdapter adapter = new DrawerAdapter(mDrawerItemList);
        recyclerView=(RecyclerView)findViewById(R.id.drawerRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        //drawerLayout=new ActionBarDrawerToggle()
    }
    private SlidingUpPanelLayout.PanelSlideListener onSlideListener() {
        return new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View view, float v) {
                //textView.setText("panel is sliding");
            }
            @Override
            public void onPanelCollapsed(View view) {
                //textView.setText("panel Collapse");
                Button b=(Button)findViewById(R.id.button_pull_push);
                b.setText("SLIDE ME UPWARDS!!!!");
                Log.i("HERE","coll");
            }
            @Override
            public void onPanelExpanded(View view) {
                Button b=(Button)findViewById(R.id.button_pull_push);
                b.setText("SLIDE ME DOWNWARDS!!!!");
                Log.i("HERE","exp");
            }
            @Override
            public void onPanelAnchored(View view) {
            }
            @Override
            public void onPanelHidden(View view) {
            }
        };
    }
    private class CustomAdapter extends FragmentPagerAdapter {
        private String[] fragments = {"frag1", "frag2", "frag3"};
        public CustomAdapter(FragmentManager suppFragMan, Context appContext) {
            super(suppFragMan);
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    //slidingLayout.setPanelHeight(0);
                    return new frag1_color();
                case 1:
                    //slidingLayout.setPanelHeight(0);
                    return new frag2_clipart();
                case 2:
                    //slidingLayout.setPanelHeight(20);
                    return new frag3_designs();
                default:
                    return null;
                //return new frag1_color();
            }
        }
        @Override
        public int getCount() {
            return fragments.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }
    }
    class CustomViewHolder////////////////////////////////
            extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView textView;
        CustomViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            imageView = (ImageView) view.findViewById(R.id.item_grid_image);
        }
        public void bindData(int position) {
            imageView.setImageResource(iv_int[position]);//this also haas to be removed after we have database
        }
        public void bindData1(int position) {
            imageView.setImageResource(iv_int[position]);//this also haas to be removed after we have database
        }
        @Override
        public void onClick(View view) {
            Toast.makeText(customerActivity.this, textView.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
    /*class CustomAdapter_recycler extends RecyclerView.Adapter<CustomViewHolder> {
        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_recycler_images, parent, false);
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
    public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder> {
        private ArrayList<DrawerItem> drawerMenuList;
        public DrawerAdapter(ArrayList<DrawerItem> drawerMenuList) {
            this.drawerMenuList = drawerMenuList;
        }
        @Override
        public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_kart, parent, false);
            return new DrawerViewHolder(view);
        }
        @Override
        public void onBindViewHolder(DrawerViewHolder holder, int position) {
           // holder.title.setText(drawerMenuList.get(position).getTitle());
            holder.icon.setImageResource(drawerMenuList.get(position).getIcon());
        }
        @Override
        public int getItemCount() {
            return drawerMenuList.size();
        }
        class DrawerViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            ImageView icon;
            public DrawerViewHolder(View itemView) {
                super(itemView);
                //title = (TextView) itemView.findViewById(R.id.title);
                icon = (ImageView) itemView.findViewById(R.id.imageView_kart);
            }
        }
    }*/
    /*public class DrawerItem {
        private int icon;
        //private String title;
        public int getIcon() {
            return icon;
        }
        public void setIcon(int icon) {
            this.icon = icon;
        }
    }
}*/