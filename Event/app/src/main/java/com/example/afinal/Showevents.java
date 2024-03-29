package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Showevents extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showevents);
        final String event = getIntent().getStringExtra("Event");
        textView = (TextView) findViewById(R.id.id);
        final FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference ref = fb.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot t : dataSnapshot.getChildren()) {
                    for (DataSnapshot tt : t.getChildren())

                    {

                        String q = "";
                        if (tt.getValue().toString().equals(event)) {

                            String s = textView.getText().toString();
                            q = t.getValue().toString();
                            Toast.makeText(getApplicationContext(), "q is " + q, Toast.LENGTH_SHORT);
                            textView.setText(s + "\n" + t.getValue().toString());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                textView.setText("Error fetching the data.");
            }
        });
    }
}
