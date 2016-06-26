package thelarsinator.simplecalculator.time;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import thelarsinator.simplecalculator.R;
import thelarsinator.simplecalculator.angles.AngleMath;
import thelarsinator.simplecalculator.complex.ComplexMath;
import thelarsinator.simplecalculator.polynomials.SolvePolynomials;
import thelarsinator.simplecalculator.simplemath.SimpleMath;

public class TimeMath extends AppCompatActivity {

    private TimePicker picker1;
    private EditText picker2;
    private TextView clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_math);

        picker1 = (TimePicker)findViewById(R.id.picker1);
        picker2 = (EditText)findViewById(R.id.picker2);
        clock = (TextView) findViewById(R.id.timeResult);
        clock.setTextSize(85);
        picker2.setTextSize(85);
        picker1.setIs24HourView(true);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_simplemath: {
                Intent intent = new Intent(this, SimpleMath.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_solvepoly: {
                Intent intent = new Intent(this, SolvePolynomials.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_anglemath: {
                Intent intent = new Intent(this, AngleMath.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_complexmath: {
                Intent intent = new Intent(this, ComplexMath.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_timemath: {
                Intent intent = new Intent(this, TimeMath.class);
                startActivity(intent);
                return true;
            }
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    //The function that is called when the calculate button is pressed
    public void solveTime(View view)
    {
        int h1 = picker1.getCurrentHour();
        int m1 = picker1.getCurrentMinute();

        String str = picker2.getText().toString();
        String time[];
        int h2;
        int m2;
        int newH = 0;
        int newM = 0;

        //Check if the input is on the form HH:MM
        if(str.contains(":"))
        {
            time = str.split(":");
            h2 = Integer.parseInt(time[0]);
            m2 = Integer.parseInt(time[1]);
            newH = (h1 + h2)%24 + (m1 + m2)/60;
            newM = (m1 + m2)%60;
            clock.setText(Integer.toString(newH) + ":" + String.format("%02d",newM));
        }
        else
        {
            clock.setText("Invalid input");
        }
    }
}
