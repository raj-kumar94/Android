package com.example.raj.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    MyDBHandler handler;
    public TextView dbArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etAge =  (EditText)findViewById(R.id.etAge);
        final EditText etName =  (EditText)findViewById(R.id.etName);
        final EditText etUsername =  (EditText)findViewById(R.id.etUsername);
        final EditText etPassword =  (EditText)findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final Button bDelete = (Button) findViewById(R.id.bDelete);

        dbArea = (TextView)findViewById(R.id.dbArea);

        etUsername.setText(getIntent().getStringExtra("PERSON_NAME"));
        etPassword.setText(getIntent().getStringExtra("PERSON_PASSWORD"));
        etAge.setText(getIntent().getStringExtra("PERSON_AGE"));

        //db Handler
        handler = new MyDBHandler(this, null,null,1);
       // printDatabase();
        Log.i("UserDetails","UserDetails Constructor");


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser(v);
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(v);
            }
        });

    }


    //add a user to database
    public void addUser(View v)
    {
        EditText etName = (EditText)findViewById(R.id.etName);
        EditText etUsername = (EditText)findViewById(R.id.etUsername);
        EditText etPassword = (EditText)findViewById(R.id.etPassword);
        EditText etAge = (EditText)findViewById(R.id.etAge);

        Log.i("UserDetails","UserDetails Constructor");
        UserDetails ud = new UserDetails(etName.getText().toString(),etUsername.getText().toString(),etPassword.getText().toString(), etAge.getText().toString());
        handler.addUsers(ud);
        // handler.deleteUsers("raj");
        printDatabase();
    }

    public void printDatabase() //problem is here
    {
        String dbString = handler.databaseToString();
        dbArea.setText(dbString);
    }
    public void deleteUser(View v)
    {
        EditText etName = (EditText)findViewById(R.id.etName);
        String name = etName.getText().toString();
        handler.deleteUsers(name);
        printDatabase();
    }
}
