package com.example.ruladmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class EditTripActivity extends AppCompatActivity {
    private DatabaseReference myDatabase;
    private Trip trip = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);

        myDatabase = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();

        trip = createTrip(intent.getExtras());

        TextView lblName = findViewById(R.id.lblTripName);
        lblName.setText(trip.getName());
        TextView lblDate = findViewById(R.id.lblTripDate);
        lblDate.setText(trip.getDate());

        EditText txtName = findViewById(R.id.txtName);
        txtName.setHint(trip.getName());
        EditText txtDate = findViewById(R.id.txtDate);
        txtDate.setHint(trip.getDate());
        EditText txtDifficulty = findViewById(R.id.txtDifficulty);
        txtDifficulty.setHint(trip.getDifficulty());
        EditText txtLatitude = findViewById(R.id.txtLatitude);
        txtLatitude.setHint(trip.getLatitude());
        EditText txtLongitude = findViewById(R.id.txtLongitude);
        txtLongitude.setHint(trip.getLongitude());
        EditText txtTag = findViewById(R.id.txtLocationTag);
        txtTag.setHint(trip.getLocationTag());
        EditText txtAllowed = findViewById(R.id.txtAllowed);
        txtAllowed.setHint(trip.getMaxPeople());

    }

    private Trip createTrip(Bundle extras){
        String key = extras.getString("key");
        String name = extras.getString("name");
        String latitude = extras.getString("latitude");
        String longitude = extras.getString("longitude");
        String locationTag = extras.getString("locationTag");
        String date = extras.getString("date");
        String difficulty = extras.getString("difficulty");
        String available = extras.getString("available");
        String maxPeople = extras.getString("maxPeople");
        Location location = new Location(latitude, longitude, locationTag);
        return new Trip(key, name, location, difficulty, date, available, maxPeople);
    }
}
