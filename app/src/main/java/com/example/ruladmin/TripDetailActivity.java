package com.example.ruladmin;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TripDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private DatabaseReference myDatabase;
    private GoogleMap mMap;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private Trip trip = null;

    private Bundle stuff = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);

        myDatabase = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();

        trip = createTrip(intent.getExtras());
        ((MapFragment) getFragmentManager().findFragmentById(R.id.myMap)).getMapAsync(this);


        setDifficultyIcons(trip.getDifficulty());
        TextView txtName = findViewById(R.id.lblTripName);
        txtName.setText(trip.getName());
        TextView txtDate = findViewById(R.id.lblTripDate);
        txtDate.setText(trip.getDate());
        TextView txtLocation = findViewById(R.id.txtLocation);
        txtLocation.setText(trip.getLocationTag());
        TextView txtAvailable = findViewById(R.id.txtAvailable);
        txtAvailable.setText(trip.getAvailable());
        TextView txtAllowed = findViewById(R.id.txtAllowed);
        txtAllowed.setText(trip.getMaxPeople());
        TextView txtLatitude = findViewById(R.id.txtLatitude);
        txtLatitude.setText(trip.getLatitude());
        TextView txtLongitude = findViewById(R.id.txtLongitude);
        txtLongitude.setText(trip.getLongitude());

    }

    private void setDifficultyIcons(String difficulty){
        ImageView img = null;
        switch(difficulty){
            case "0":
                img = findViewById(R.id.dif1);
                img.setImageResource(android.R.drawable.presence_invisible);
                img = findViewById(R.id.dif2);
                img.setImageResource(android.R.drawable.presence_invisible);
                img = findViewById(R.id.dif3);
                img.setImageResource(android.R.drawable.presence_invisible);
                img = findViewById(R.id.dif4);
                img.setImageResource(android.R.drawable.presence_invisible);
                img = findViewById(R.id.dif5);
                img.setImageResource(android.R.drawable.presence_invisible);
                break;
            case "1":
                img = findViewById(R.id.dif1);
                img.setImageResource(android.R.drawable.presence_online);
                img = findViewById(R.id.dif2);
                img.setImageResource(android.R.drawable.presence_invisible);
                img = findViewById(R.id.dif3);
                img.setImageResource(android.R.drawable.presence_invisible);
                img = findViewById(R.id.dif4);
                img.setImageResource(android.R.drawable.presence_invisible);
                img = findViewById(R.id.dif5);
                img.setImageResource(android.R.drawable.presence_invisible);
                break;
            case "2":
                img = findViewById(R.id.dif1);
                img.setImageResource(android.R.drawable.presence_online);
                img = findViewById(R.id.dif2);
                img.setImageResource(android.R.drawable.presence_online);
                img = findViewById(R.id.dif3);
                img.setImageResource(android.R.drawable.presence_invisible);
                img = findViewById(R.id.dif4);
                img.setImageResource(android.R.drawable.presence_invisible);
                img = findViewById(R.id.dif5);
                img.setImageResource(android.R.drawable.presence_invisible);
                break;
            case "3":
                img = findViewById(R.id.dif1);
                img.setImageResource(android.R.drawable.presence_online);
                img = findViewById(R.id.dif2);
                img.setImageResource(android.R.drawable.presence_online);
                img = findViewById(R.id.dif3);
                img.setImageResource(android.R.drawable.presence_online);
                img = findViewById(R.id.dif4);
                img.setImageResource(android.R.drawable.presence_invisible);
                img = findViewById(R.id.dif5);
                img.setImageResource(android.R.drawable.presence_invisible);
                break;
            case "4":
                img = findViewById(R.id.dif1);
                img.setImageResource(android.R.drawable.presence_online);
                img = findViewById(R.id.dif2);
                img.setImageResource(android.R.drawable.presence_online);
                img = findViewById(R.id.dif3);
                img.setImageResource(android.R.drawable.presence_online);
                img = findViewById(R.id.dif4);
                img.setImageResource(android.R.drawable.presence_online);
                img = findViewById(R.id.dif5);
                img.setImageResource(android.R.drawable.presence_invisible);
                break;
            case "5":
                img = findViewById(R.id.dif1);
                img.setImageResource(android.R.drawable.presence_online);
                img = findViewById(R.id.dif2);
                img.setImageResource(android.R.drawable.presence_online);
                img = findViewById(R.id.dif3);
                img.setImageResource(android.R.drawable.presence_online);
                img = findViewById(R.id.dif4);
                img.setImageResource(android.R.drawable.presence_online);
                img = findViewById(R.id.dif5);
                img.setImageResource(android.R.drawable.presence_online);
                break;
        }
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Double lat = Double.parseDouble(this.trip.getLatitude());
        Double lon = Double.parseDouble(this.trip.getLongitude());
        LatLng place = new LatLng(lat, lon);
        Marker marker = mMap.addMarker(new MarkerOptions().position(place).title("Meeting point"));
        marker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(100);
    }

    public void onBtnDeleteClick(View v){
        DatabaseReference selected = FirebaseDatabase.getInstance().getReference().child(trip.getKey());
        selected.removeValue();
        Toast.makeText(getBaseContext(), "Trip removed successfully!", Toast.LENGTH_LONG).show();
        Intent switchActivity = new Intent(getBaseContext(), MainActivity.class);
        startActivity(switchActivity);
    }

    public void onBtnEditClick(View v){
        Intent intent = new Intent(v.getContext(), EditTripActivity.class);

        Bundle extras = new Bundle();
        extras.putString("key", trip.getKey());
        extras.putString("name", trip.getName());
        extras.putString("latitude", trip.getLatitude());
        extras.putString("longitude", trip.getLongitude());
        extras.putString("date", trip.getDate());
        extras.putString("difficulty", trip.getDifficulty());
        extras.putString("available", trip.getAvailable());
        extras.putString("locationTag", trip.getLocationTag());
        extras.putString("maxPeople", trip.getMaxPeople());
        intent.putExtras(extras);

        startActivity(intent);

    }
}


