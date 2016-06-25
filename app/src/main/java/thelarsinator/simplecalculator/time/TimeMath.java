package thelarsinator.simplecalculator.time;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import thelarsinator.simplecalculator.R;

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
    }

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
