package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.Models.Medicine;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    ListView listView;
    Button addMedicine;

    ArrayAdapter<String> adapter;
    List<String> listMedicine;
    List<Medicine> listTemp;

    FirebaseDatabase db;
    DatabaseReference medicine;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        listView = findViewById(R.id.list_medicines);
        addMedicine = findViewById(R.id.add_med_btn);

        listMedicine = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listMedicine);
        listView.setAdapter(adapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
        }

        db = FirebaseDatabase.getInstance();
        medicine = db.getReference("Medicine/"+uid);

        getMedicine();
        setOnClickItem();

        addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalendarActivity.this,AddMedicineActivity.class));
                finish();
            }
        });
    }

    private void getMedicine(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(listMedicine.size()>0)listMedicine.clear();
                if(listTemp.size()>0)listTemp.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Medicine med = ds.getValue(Medicine.class);
                    med.setFirstDay(null);
                    med.setLastDay(null);
                    assert med != null;
                    listMedicine.add(med.getName());
                    listTemp.add(med);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        medicine.addValueEventListener(valueEventListener);
    }

    private void setOnClickItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Medicine medicine = listTemp.get(i);

                Intent intent = new Intent(CalendarActivity.this,ChangeMedicineActivity.class);
                intent.putExtra("med_name",medicine.getName());
                intent.putExtra("med_dosage",medicine.getDosage());
                intent.putExtra("med_units",medicine.getUnits());
                intent.putExtra("med_applying",medicine.getApplying());
                startActivity(intent);
            }
        });
    }
}