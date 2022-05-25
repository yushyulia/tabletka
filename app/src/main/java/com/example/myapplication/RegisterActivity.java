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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    EditText email, name, birthday, weight, password, rePassword;
    Button register, back;
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
        back = findViewById(R.id.back_register);

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

                if(TextUtils.isEmpty(weight.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "Введите вес", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.getText().toString().length()<5){
                    Toast.makeText(RegisterActivity.this, "Минимальная длина пароля 5 символов", Toast.LENGTH_SHORT).show();
                    return;
                }

                register();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    private void register(){
        auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            String datee = birthday.getText().toString();
                            try {
                                dateObject = formatter.parse(datee);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Float w = Float.valueOf(weight.getText().toString());

                            User u = new User (name.getText().toString(), password.getText().toString(), email.getText().toString(),dateObject,w);

                            users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(u);
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                            Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Не удалось зарегистироваться",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
    }
}