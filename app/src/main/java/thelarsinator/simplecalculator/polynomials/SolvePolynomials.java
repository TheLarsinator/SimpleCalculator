package thelarsinator.simplecalculator.polynomials;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import thelarsinator.simplecalculator.R;
import thelarsinator.simplecalculator.angles.AngleMath;
import thelarsinator.simplecalculator.complex.ComplexMath;
import thelarsinator.simplecalculator.simplemath.SimpleMath;
import thelarsinator.simplecalculator.time.TimeMath;

public class SolvePolynomials extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_polynomials);

        textView = (TextView) findViewById(R.id.polResult);
        textView.setText("Answer:\n");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
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
    public void solvePol(View view)
    {
        EditText second1 = (EditText) findViewById(R.id.secondOrder);
        EditText first1 = (EditText) findViewById(R.id.firstOrder);
        EditText zero1 = (EditText) findViewById(R.id.zeroOrder);
        double second, first, zero;
        //First see if the equation entered is of a second degree...
        try{
            second = Double.parseDouble(second1.getText().toString());
        }
        catch(Exception e){
        //No input could mean that this is 0, and the user want to solve an equation of 1. order
            second = 0;
        }

        try{
            first = Double.parseDouble(first1.getText().toString());
        }
        catch(Exception e){
            //No input could mean that this is 0
            first = 0;
        }

        try{
            zero = Double.parseDouble(zero1.getText().toString());
        }catch (Exception e)
        {
            zero = 0;
        }


        double x1;
        double x2;
        String output;

        //Format doubles
        DecimalFormat df = new DecimalFormat("#.###");

        //Check if the quation is of the second order, if not, easy solving
        if(second == 0)
        {
            x1 = -zero/first;
            output = "X = " + df.format(x1);
        }
        else{


            //Now solve the equation second x^2 + first x + zero = 0
            if(!isRootNegative(second, first, zero))
            {
                x1 = ((-first) + Math.sqrt((first * first) - (4*second*zero)))/(2 * second);
                x2 = ((-first) - Math.sqrt((first * first) - (4*second*zero)))/(2 * second);


                output = "Answer:\nX\u2081 = " + df.format(x1) + "\nX\u2082 = " + df.format(x2);
            }
            else
            {
                x1 = (-first)/(2*second);
                x2 = Math.sqrt(-(first * first - 4*second*zero))/(2*second);
                output ="Answer:\nZ\u2081 = " + df.format(x1) + " + " + df.format(x2) + "i" +
                        "\nZ\u2082 = " + df.format(x1) + " - " + df.format(x2) + "i";
            }
        }
            textView.setText(output);
    }

    //Check if we will get a complex solution
    private boolean isRootNegative(double second, double first, double zero)
    {
        return (first * first - 4*second*zero < 0);
    }
}
