package com.example.asus.finalpbo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class input extends AppCompatActivity {
    private DatabaseReference mnilaipboRef;
    EditText text1,text2,text3;
    Spinner spinner1;
    Button button1;
    String[] status={"Kelompok 1","Kelompok 2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        button1=findViewById(R.id.button1);
        spinner1=findViewById(R.id.spinner1);
        ArrayAdapter<String> a= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,status);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(a);
        mnilaipboRef = FirebaseDatabase.getInstance().getReference().child("PemakaianAir");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mnilaipboRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(text1.getText().toString()).exists()){
                            Toast.makeText(input.this, "Data Sudah Ada", Toast.LENGTH_SHORT).show();
                        }else {
                            String No;
                            String gol;
                            penduduk mhs=new penduduk();
                            No=text1.getText().toString();
                            gol=spinner1.getSelectedItem().toString();
                            mhs.setNo(text1.getText().toString());
                            mhs.setGolongan(gol);
                            mhs.setPakai(Double.parseDouble(text2.getText().toString()));
                            mhs.setTotal();

                            mnilaipboRef.child(No).setValue(mhs);

                            Toast.makeText(input.this, "Data Berhasil di input", Toast.LENGTH_SHORT).show();

                            finish();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
