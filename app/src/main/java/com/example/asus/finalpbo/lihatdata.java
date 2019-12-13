package com.example.asus.finalpbo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class lihatdata extends AppCompatActivity {
    static FirebaseDatabase database=FirebaseDatabase.getInstance();
    static DatabaseReference databaseReference=database.getReference();

    List<penduduk> mhsData=new ArrayList<>();

    adapter adapter;
    RecyclerView view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihatdata);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mhsData.clear();
                DataSnapshot nilaipbo = dataSnapshot.child("PemakaianAir");
                for (DataSnapshot postsnapshot : nilaipbo.getChildren()) {
                    penduduk mhs = new penduduk();
                    mhs.setNo(postsnapshot.getKey());
                    mhs.setGolongan(postsnapshot.child("golongan").getValue().toString());
                    mhs.setPakai(Double.parseDouble(postsnapshot.child("pakai").getValue().toString()));
                    mhs.setTotal();
                    mhsData.add(mhs);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        view1=findViewById(R.id.view1);
        view1.setHasFixedSize(true);
        view1.setLayoutManager(new LinearLayoutManager(this));
        adapter = new adapter(mhsData, lihatdata.this);
        view1.setAdapter(adapter);

        DividerItemDecoration divider=new DividerItemDecoration(view1.getContext(),DividerItemDecoration.VERTICAL);
        view1.addItemDecoration(divider);
    }

}
