package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.myapplication.Models.Medicine;
import com.example.myapplication.Models.Reminder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ChangeMedicineActivity extends AppCompatActivity {

    EditText medicineName, firstDay, lastDay, time;
    Spinner dosageSpinner, unitsSpinner, remindSpinner, daysSpinner;
    Button editMedicineButton, back_btn;
    RadioGroup applying;
    RadioButton radio_btn;
    String uid, med_id;

    String[] dosage = {"Расчет по весу","Ввести значение самостоятельно"};
    String[] units = {"ампулы","граммы","капли","чайная ложка", "столовая ложка", "таблетки", "штука"};
    String[] remind = {"в момент события","за 10 минут","за 15 минут","за 30 минут","за 1 час","нет"};
    String[] days = {"понедельник","вторник","среда","четверг","пятница","суббота","воскресенье"};

    String med_dosage,med_unit,med_remind,med_day,med_applying;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_medicine);

        db = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
        }

        medicineName = findViewById(R.id.medname_id);
        firstDay = findViewById(R.id.startTextDate);
        lastDay = findViewById(R.id.endTextDate);
        time = findViewById(R.id.time_id);

        dosageSpinner = findViewById(R.id.dosage_spinner);
        unitsSpinner = findViewById(R.id.units_spinner);
        remindSpinner = findViewById(R.id.remind_spinner);
        daysSpinner = findViewById(R.id.days_spinner);

        editMedicineButton = findViewById(R.id.save_change_btn);
        back_btn = findViewById(R.id.change_med_exit_btn);

        applying = findViewById(R.id.radioGroup);

        ArrayAdapter<String> dosageAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dosage);
        dosageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dosageSpinner.setAdapter(dosageAdapter);

        ArrayAdapter<String> unitsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, units);
        unitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitsSpinner.setAdapter(unitsAdapter);

        ArrayAdapter<String> remindAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, remind);
        remindAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        remindSpinner.setAdapter(remindAdapter);

        ArrayAdapter<String> daysAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, days);
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daysSpinner.setAdapter(daysAdapter);

        dosageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                med_dosage = adapterView.getAdapter().getItem(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        unitsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                med_unit = adapterView.getAdapter().getItem(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        remindSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                med_remind = adapterView.getAdapter().getItem(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        daysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                med_day = adapterView.getAdapter().getItem(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        applying.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_red:
                        radio_btn = findViewById(R.id.radio_red);
                        med_applying=radio_btn.getText().toString();
                        break;
                    case R.id.radio_blue:
                        radio_btn = findViewById(R.id.radio_blue);
                        med_applying=radio_btn.getText().toString();
                        break;
                    case R.id.radio_green:
                        radio_btn = findViewById(R.id.radio_green);
                        med_applying=radio_btn.getText().toString();
                        break;
                    case R.id.radio_gray:
                        radio_btn = findViewById(R.id.radio_gray);
                        med_applying=radio_btn.getText().toString();
                        break;
                    default:
                        break;
                }
            }
        });

        getMedIntent();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangeMedicineActivity.this,CalendarActivity.class));
                finish();
            }
        });

        editMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeMedicine();
            }
        });
    }

    private void getMedIntent(){
        Intent intent = getIntent();
        med_id = intent.getStringExtra("med_id");
        if(intent != null){
            medicineName.setText(intent.getStringExtra("med_name"));
            for(int i = 0; (i<dosage.length); i++){
                if (dosage[i].equals(intent.getStringExtra("med_dosage"))){
                    dosageSpinner.setSelection(i);
                }
            }
            for(int i = 0; (i<units.length); i++){
                if (units[i].equals(intent.getStringExtra("med_units"))){
                    unitsSpinner.setSelection(i);
                }
            }
            for(int i = 0; (i<remind.length); i++){
                if (remind[i].equals(intent.getStringExtra("moment_remind"))){
                    remindSpinner.setSelection(i);
                }
            }
            for(int i = 0; (i<days.length); i++){
                if (days[i].equals(intent.getStringExtra("day_remind"))){
                    daysSpinner.setSelection(i);
                }
            }
            firstDay.setText(String.valueOf(intent.getStringExtra("first_day")));
            lastDay.setText(String.valueOf(intent.getStringExtra("last_day")));
            time.setText(String.valueOf(intent.getStringExtra("time_remind")));
        }
    }

    private void changeMedicine(){
        Reminder reminder = new Reminder(time.getText().toString(),med_remind,med_day);
        Medicine med = new Medicine(med_id,medicineName.getText().toString(),med_dosage,med_unit,med_applying,
                firstDay.getText().toString(),lastDay.getText().toString(),reminder);
        Map<String, Object> medValues = med.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Medicine/" + uid + "/" + med_id, medValues);
        db.updateChildren(childUpdates);

        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
        startActivity(intent);
    }

}