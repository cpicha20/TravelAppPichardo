package com.example.cpicha.truetravellite;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.Dialog;
import android.app.DatePickerDialog;
import android.net.Uri;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Adapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Context;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import  java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DisplayMessageActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    TripList alltrips;
    Trip newt;

    Button dep;
    TextView deptext;
    boolean button1;

    Button arr;
    TextView arrtext;
    boolean button2;



    int day, month, year, day2, year2, month2;
    int dayFinal, monthFinal, yearFinal, dayFinal2, monthFinal2, yearFinal2;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);


        AutoCompleteTextView afill = (AutoCompleteTextView) findViewById(R.id.autocomplete_country);

        String[] accomm = getResources().getStringArray(R.array.Accommodations_array);
        ArrayAdapter<String> adapt =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, accomm);
        afill.setAdapter(adapt);


        deptext = (TextView) findViewById(R.id.deptext);
        arrtext = (TextView) findViewById(R.id.arrtext);

        dep = (Button) findViewById(R.id.setDeparture);
        dep.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                button1 = true;
                button2 = false;
                Calendar C = Calendar.getInstance();
                year = C.get(Calendar.YEAR);
                month = C.get(Calendar.MONTH);
                day = C.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(DisplayMessageActivity.this,
                        DisplayMessageActivity.this, year, month, day);
                datePickerDialog.show();
            }


        });
        arr = (Button) findViewById(R.id.setArrival);
        arr.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                button2 = true;
                button1 = false;
                Calendar C = Calendar.getInstance();
                year = C.get(Calendar.YEAR);
                month = C.get(Calendar.MONTH);
                day = C.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(DisplayMessageActivity.this,
                        DisplayMessageActivity.this, year, month, day);

                datePickerDialog.show();
            }


        });


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.travel_type, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1 + 1;
        dayFinal = i2;

        if (button1)
            deptext.setText(dayFinal + "/" + monthFinal + "/" +  yearFinal);
        else
            arrtext.setText(dayFinal + "/" + monthFinal + "/" +  yearFinal);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("DisplayMessage Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    public void createTrip(View view){

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        EditText location = (EditText) findViewById(R.id.edit_location);
        String loc= location.getText().toString();

        Spinner spin= (Spinner) findViewById(R.id.spinner);
        String spi= spin.getSelectedItem().toString();


        newt=new Trip(name,loc,spi);

        ArrayList<Trip> lol = new ArrayList<Trip>();

        lol.add(newt);

        lol.get(0).getName();

        Context context = getApplicationContext();
        CharSequence text ="Trip Name: "+lol.get(0).getName()+ "\nLocation: " +newt.getLocation()+"\nTravel Method: "+newt.getTravel();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();



       }
}
