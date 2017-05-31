package tarotato.tracker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;


public class NewItemActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    Calendar dateSelected = Calendar.getInstance();
    DatePickerDialog datePickerDialog;
    String d = "";
    String m = "";
    String y = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        //Enable back arrow button
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.tags);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.itemTags, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        ImageButton datePurchased = (ImageButton)findViewById(R.id.purchaseButton);
        datePurchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = (EditText)findViewById(R.id.purchaseDate);
                setPurchasedDateField(editText);
            }
        });

        ImageButton dateExpired = (ImageButton)findViewById(R.id.expireButton);
        dateExpired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = (EditText)findViewById(R.id.expireDate);
                setPurchasedDateField(editText);
//                Snackbar.make(view, "Time "+d+m+y+"WHAT"+str, Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    private void setPurchasedDateField(android.widget.EditText editText) {
        final EditText dateField = editText;
        Calendar newCalendar = dateSelected;
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateSelected.set(year, monthOfYear, dayOfMonth, 0, 0);
                d = Integer.toString(dayOfMonth);
                m = Integer.toString(monthOfYear+1);
                y = Integer.toString(year);
                //Toast.makeText(NewItemActivity.this, d, Toast.LENGTH_LONG).show();
                dateField.setText(d+"/"+m+"/"+y);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Create "Save" button
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_new_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.save_action:
                Toast.makeText(this, "Save selected", Toast.LENGTH_SHORT)
                        .show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
