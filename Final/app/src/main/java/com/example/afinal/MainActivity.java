package com.example.afinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ValueEventListener {

    private RecyclerView recyclerView;
    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.event_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("events");
        databaseReference.addValueEventListener(this);

        setData();
    }

    private void setData() {
        List<EventInfo> eventList = new ArrayList<>();

        adapter = new EventAdapter(eventList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Log.e(getClass().getName(), "Firebase data change");
        adapter.clear();
        Log.e(getClass().getSimpleName(), "EventAdapter clear");
        for (DataSnapshot ds : dataSnapshot.getChildren() ){
            EventInfo eventInfo = new EventInfo(
                    ds.child("image").getValue().toString(),
                    ds.child("title").getValue().toString(),
                    ds.child("start_date").getValue().toString(),
                    ds.child("end_date").getValue().toString(),
                    ds.child("location").getValue().toString(),
                    ds.child("host").getValue().toString(),
                    ds.child("content").getValue().toString(),
                    ds.child("url").getValue().toString());
            adapter.add(eventInfo);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
