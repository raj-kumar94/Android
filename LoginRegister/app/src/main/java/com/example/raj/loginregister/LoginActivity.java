package com.example.raj.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    MyDBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        handler = new MyDBHandler(this, null,null,1);

        final EditText etUsername =  (EditText)findViewById(R.id.etUsername);
        final EditText etPassword =  (EditText)findViewById(R.id.etPassword);
        final Button bLogin = (Button)findViewById(R.id.bLogin);
        final TextView registerLink = (TextView)findViewById(R.id.tvRegisterHere);

        //listerner for register link
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent =  new Intent(LoginActivity.this, RegisterActivity.class);  //this is where we want to target on click of register link
                registerIntent.putExtra("PERSON_NAME", etUsername.getText().toString());
                registerIntent.putExtra("PERSON_PASSWORD",etPassword.getText().toString());
                registerIntent.putExtra("PERSON_AGE","18");
                LoginActivity.this.startActivity(registerIntent);  //this will perform the intent

            }
        });


        assert bLogin != null;
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //checking the database
                String uname=etUsername.getText().toString();
                String pass= etPassword.getText().toString();
                if(handler.isUserValid(uname,pass))
                {
                    //applying intent
                    Intent loginIntent =  new Intent(LoginActivity.this, UserAreaActivity.class);  //this is where we want to target on click of register link
                    loginIntent.putExtra("username",uname);
                    loginIntent.putExtra("password",pass);
                    LoginActivity.this.startActivity(loginIntent);  //this will perform the intent
                }
                else
                {
                    //toast notification
                    Toast.makeText(LoginActivity.this, "Wrong Info!",
                            Toast.LENGTH_LONG).show();
                }

                 }
        });
    }
}
