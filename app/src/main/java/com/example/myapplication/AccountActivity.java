package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class AccountActivity extends AppCompatActivity {

    Button exit, save, calendar;
    EditText user_email, user_name, user_birthday, user_weight, user_password;
    DatabaseReference dbRef;
    FirebaseAuth auth;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        user_email = findViewById(R.id.acc_email_text);
        user_name = findViewById(R.id.acc_name_text);
        user_birthday = findViewById(R.id.acc_bday_text);
        user_weight = findViewById(R.id.acc_weight_text);
        user_password = findViewById(R.id.acc_passwd_text);

        exit = findViewById(R.id.exit_btn);
        save = findViewById(R.id.save_btn);
        calendar = findViewById(R.id.calendar_btn);

        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
            showUserInfo();
        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AccountActivity.this,SignInActivity.class));
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeUserInfo();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this,CalendarActivity.class));
                finish();
            }
        });
    }

    private void showUserInfo(){
        dbRef.child("Users").child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    User data = task.getResult().getValue(User.class);
                    user_email.setText(data.getEmail());
                    user_name.setText(data.getName());
                    user_password.setText(data.getPassword());
                    user_birthday.setText(String.valueOf(data.getBirthday()));
                    user_weight.setText(String.valueOf(data.getWeight()));
                }
            }
        });
    }

    private void changeUserInfo(){
        Float w = Float.valueOf(user_weight.getText().toString());

        User u = new User (user_name.getText().toString(), user_password.getText().toString(), user_email.getText().toString(),
                user_birthday.getText().toString(),w);

        Map<String, Object> userValues = u.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Users/" + uid, userValues);

        dbRef.updateChildren(childUpdates);
    }
}