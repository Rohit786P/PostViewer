package com.example.rsharma.postsviewer.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.* ;
import android.content.Intent;

import com.example.rsharma.postsviewer.R;

public class LoginActivity extends AppCompatActivity {

    EditText textOfUsername;
    EditText textOfPassword;
    TextView forgotpw, createAc;
    String username1;
    String nameOfUser = null;
    String userPassword = null;
    String password1;
    Button buttonOfAccept;
    String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[@#$%]).{6,20})";

    public static final String MyPref = "MyPrefrences";

    SharedPreferences sharedpreferences;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LoginActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setLogo(R.mipmap.post1);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);*/

        sharedpreferences = this.getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        String nameOfUser = sharedpreferences.getString("Username", "");
        String userPassword = sharedpreferences.getString("Password", "");


        if (!sharedpreferences.getString("Username", "").equals("")) {
            System.out.println(nameOfUser + " " + userPassword); //for checking sharedprefrance values
            Intent intent = new Intent(LoginActivity.this, PostViewAct.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            LoginActivity.this.finish();
        } else {
            setContentView(R.layout.activity_login);
            textOfUsername = (EditText) findViewById(R.id.textOfUsername);
            textOfPassword = (EditText) findViewById(R.id.textOfPassword);
            textOfUsername.setTypeface(Typeface.SERIF);
            textOfPassword.setTypeface(Typeface.SERIF);
            buttonOfAccept = (Button) findViewById(R.id.btSignIn);
            textOfUsername.setText("");
            textOfPassword.setText("");
            forgotpw = (TextView) findViewById(R.id.fgtpw);
            createAc = (TextView) findViewById(R.id.createAc);
            forgotpw.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    moveToMessageActivity(v);

                }
            });
            createAc.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    moveToMessageActivity(v);

                }
            });

            buttonOfAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (TextUtils.isEmpty(textOfUsername.getText().toString().trim()) || TextUtils.isEmpty(textOfPassword.getText().toString().trim())) {
                        Toast.makeText(LoginActivity.this, "Please enter both Values ", Toast.LENGTH_LONG).show();
//                        textOfUsername.setText("");
                    } else {
                        if (!textOfUsername.getText().toString().trim().matches(EMAIL_REGEX)) {
                            Toast.makeText(LoginActivity.this, "You are entering an INVALID userid ", Toast.LENGTH_LONG).show();

                            textOfPassword.setText("");
                        } else if (!textOfPassword.getText().toString().trim().matches(PASSWORD_PATTERN)) {
                            Toast.makeText(LoginActivity.this, "Password Credentials not matching(must have atleast one numeric and one special chracter)", Toast.LENGTH_LONG).show();


                        } else {
                            sharedpreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
                            //saving prefrences
                            String uname = textOfUsername.getText().toString();
                            String pass = textOfPassword.getText().toString();
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            //now putting values into prefrences
                            editor.putString("Username", uname);
                            editor.putString("Password", pass);
                            editor.commit();


                            LoginActivity.this.finish();


                            moveToMessageActivity(view);


                        }

                    }
                }


            });


        }
    }

    public void moveToMessageActivity(View view) {
        Intent intent = new Intent(LoginActivity.this, PostViewAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        LoginActivity.this.finish();

    }
}