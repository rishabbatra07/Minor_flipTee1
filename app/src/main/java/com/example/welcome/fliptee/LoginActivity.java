package com.example.welcome.fliptee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;

public class LoginActivity extends AppCompatActivity {
    private SignInButton signInButton;
    private LoginButton sFB;//check
    private Button signInButtonFB;
    private LoginButton fblogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton=(SignInButton)findViewById(R.id.button_Google_Signin);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","heli");
                Intent i=new Intent(LoginActivity.this,Login_google_Activity.class);
                startActivity(i);
            }
        });
        signInButtonFB=(Button)findViewById(R.id.fbloginbutton);
        signInButtonFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,Login_FB_Activity.class);
                startActivity(i);
            }
        });
    }
}

