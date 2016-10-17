package com.example.welcome.fliptee;

/*public class MainActivity extends AppCompatActivity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    Thread splashTread;
    boolean is_logged_in;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("FLIP",0);
        editor = pref.edit();
        is_logged_in = pref.getBoolean("IS_LOGGED_IN",false);
        String email= pref.getString("USER_EMAIL",null);
        String name= pref.getString("USER_NAME",null);
        StartAnimations();
    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpa);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    //Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    if (is_logged_in==true) {
                        Intent i = new Intent(getApplicationContext(), designer_customer_Activity.class);
                        startActivity(i);
                        //startActivity(intent);
                        MainActivity.this.finish();
                    }
                    else
                    {
                        Intent startActivity = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(startActivity);
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    MainActivity.this.finish();
                }
            }
        };
        splashTread.start();
    }
}*/
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences shap=getSharedPreferences("LOGIN_GOOGLE",MODE_PRIVATE);
        SharedPreferences.Editor editor=shap.edit();
        editor.putInt("login",50);
        pref = getSharedPreferences("FLIP",0);
        editor = pref.edit();
        boolean is_logged_in = pref.getBoolean("IS_LOGGED_IN",false);
        String email= pref.getString("USER_EMAIL",null);
        String name= pref.getString("USER_NAME",null);
        Toast.makeText(getApplicationContext(),""+is_logged_in,Toast.LENGTH_LONG).show();
        //if(is_logged_in==true)
        if(shap.getInt("login",89)==100)
        {
            new CountDownTimer(3000, 1000) {
                public void onFinish() {
                    Intent i = new Intent(getApplicationContext(),designer_customer_Activity.class);
                    startActivity(i);
                    finish();
                }
                public void onTick(long millisUntilFinished) {
                }
            }.start();
        }
        else {
            new CountDownTimer(3000, 1000) {
                public void onFinish() {
                    Intent startActivity = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(startActivity);
                    finish();
                }
                public void onTick(long millisUntilFinished) {
                }
            }.start();
        }
    }
}