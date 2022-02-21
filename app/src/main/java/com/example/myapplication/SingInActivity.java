package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SingInActivity extends AppCompatActivity {

    EditText email,password;
    Button btn_signIn,btn_register_screen;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        email = (EditText) findViewById(R.id.user_email);
        password = (EditText) findViewById(R.id.user_passwd);
        btn_signIn = (Button) findViewById(R.id.btn_signIn);
        btn_register_screen = (Button) findViewById(R.id.btn_register_screen);
        DB=new DBHelper(this);

        btn_register_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail=email.getText().toString();
                String userPasswd=password.getText().toString();

                if(userPasswd.equals("")||userEmail.equals(""))
                    Toast.makeText(SingInActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                else{

                    Boolean checkUserPass=DB.checkUserPassword(userEmail,userPasswd);
                    if(checkUserPass){
                        User searchUser=DB.searchUser(userEmail);
                        Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
                        intent.putExtra(User.class.getSimpleName(),searchUser);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(SingInActivity.this, "Неправильный email или пароль", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}