package thelarsinator.simplecalculator.angles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.DecimalFormat;

import thelarsinator.simplecalculator.R;

public class AngleMath extends AppCompatActivity {


    private TextView switchStatus;
    private Switch mySwitch;

    private boolean useRads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angle_math);
        switchStatus = (TextView) findViewById(R.id.radStatus);
        mySwitch = (Switch) findViewById(R.id.radSwitch);

        //set the switch to ON
        mySwitch.setChecked(true);

        switchStatus.setTextSize(25);
        //attach a listener to check for changes in state
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    switchStatus.setText("Using radians");
                    useRads = true;
                }else{
                    switchStatus.setText("Using degrees");
                    useRads = false;
                }

            }
        });

        //check the current state before we display the screen
        if(mySwitch.isChecked()){
            switchStatus.setText("Using radians");
            useRads=true;
        }
        else {
            switchStatus.setText("Using degrees");
            useRads = false;
        }
    }


    public void doTrig(View view)
    {
        //Format doubles
        DecimalFormat df = new DecimalFormat("#.###");

        EditText angle = (EditText) findViewById(R.id.angle);
        String a = angle.getText().toString();
        double ang = Double.parseDouble(a);
        double result = 0;
        TextView sinusField = (TextView) findViewById(R.id.sinus);
        TextView sinusResult = (TextView) findViewById(R.id.sinusResult);

        TextView cosinField = (TextView) findViewById(R.id.cosin);
        TextView cosinResult = (TextView) findViewById(R.id.cosinResult);

        TextView tangensField = (TextView) findViewById(R.id.tangens);
        TextView tangensResult = (TextView) findViewById(R.id.tangensResult);

        TextView deg1 = (TextView) findViewById(R.id.deg1);
        TextView deg2 = (TextView) findViewById(R.id.deg2);
        TextView deg3 = (TextView) findViewById(R.id.deg3);
        TextView deg4 = (TextView) findViewById(R.id.deg4);

        sinusField.setText(Double.toString(ang));
        tangensField.setText(Double.toString(ang));
        cosinField.setText(Double.toString(ang));

        if(useRads)
        {
            result = Math.sin(ang);
            sinusResult.setText(df.format(result));
            result = Math.cos(ang);
            cosinResult.setText(df.format(result));
            result = Math.tan(ang);
            tangensResult.setText(df.format(result));

            deg1.setText(Double.toString(ang));
            deg2.setText(" radians");
            deg3.setText(df.format(radToDeg(ang)));
            deg4.setText(" degrees");

        }
        else
        {
            deg1.setText(Double.toString(ang));
            deg2.setText(" degrees");

            ang = degToRad(ang);
            result = Math.sin(ang);
            sinusResult.setText(df.format(result));
            result = Math.cos(ang);
            cosinResult.setText(df.format(result));
            result = Math.tan(ang);
            tangensResult.setText(df.format(result));


            deg3.setText(df.format(ang));
            deg4.setText(" radians");
        }
    }

    private double degToRad(double deg)
    {
        return (deg * Math.PI)/180;
    }

    private double radToDeg(double rad)
    {
        return (rad * 180)/Math.PI;
    }

}
