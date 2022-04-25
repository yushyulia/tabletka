package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    EditText email, name, birthday, weight, password, rePassword;
    Button register;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    Date dateObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        birthday = findViewById(R.id.birthday);
        weight = findViewById(R.id.weight);
        password = findViewById(R.id.passwd);
        rePassword = findViewById(R.id.rePasswd);
        register = findViewById(R.id.btn_register);

        auth = FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "Введите email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "Введите имя", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(birthday.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "Введите дату рождения", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(weight.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "Введите вес", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.getText().toString().length()<5){
                    Toast.makeText(RegisterActivity.this, "Минимальная длина пароля 5 символов", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                String datee = birthday.getText().toString();
                                try {
                                    dateObject = formatter.parse(datee);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Float w = Float.valueOf(weight.getText().toString());

                                User user = new User (name.getText().toString(), password.getText().toString(), email.getText().toString(),dateObject,w);

                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                startActivity(new Intent(RegisterActivity.this,AccountActivity.class));
                                                finish();
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterActivity.this, "Пользователь с таким email уже существует", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}