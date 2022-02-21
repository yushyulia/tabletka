package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText email,user_name,birthday,weight,passwd,repasswd;
    Button btn_register;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.email);
        user_name =(EditText) findViewById(R.id.name);
        birthday=(EditText) findViewById(R.id.birthday);
        weight = (EditText) findViewById(R.id.weight);
        passwd = (EditText) findViewById(R.id.passwd);
        repasswd = (EditText) findViewById(R.id.rePasswd);
        btn_register = (Button) findViewById(R.id.btn_register);
        DB=new DBHelper(this);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = user_name.getText().toString();
                String userEmail = email.getText().toString();
                String userBirthday = birthday.getText().toString();
                Long userWeight = Long.parseLong(weight.getText().toString());
                String userPasswd = passwd.getText().toString();
                String userRepass = repasswd.getText().toString();

                if(name.equals("")||userEmail.equals("")||userBirthday.equals("")||userWeight<=0||userPasswd.equals("")||userRepass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                else{
                    if(userPasswd.equals(userRepass)){
                        Boolean checkuser = DB.checkUser(userEmail);
                        if(!checkuser){
                            Boolean insert = DB.insertUser(name,userPasswd,userEmail,userBirthday,userWeight);
                            if(insert){
                                User user=new User(name,userEmail,userPasswd,userBirthday,userWeight);
                                Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
                                intent.putExtra(User.class.getSimpleName(),user);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"Что-то пошло не так",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this,"К данному email уже привязан аккаунт",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Пароли не одинаковые",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}