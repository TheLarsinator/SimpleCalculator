package thelarsinator.simplecalculator.simplemath;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import thelarsinator.simplecalculator.R;
import thelarsinator.simplecalculator.angles.AngleMath;
import thelarsinator.simplecalculator.complex.ComplexMath;
import thelarsinator.simplecalculator.polynomials.SolvePolynomials;
import thelarsinator.simplecalculator.time.TimeMath;

public class SimpleMath extends AppCompatActivity {

    private TextView textView;
    private Spinner spinner;
    private static final String[]paths = {"+", "-", "*", "/"};
    //TextView textView = new TextView(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_math);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SimpleMath.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        textView = (TextView) findViewById(R.id.simpleMathResult);
        textView.setTextSize(25);
        textView.setText("Answer: ");

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
    public void calculate(View view){

        EditText number1 = (EditText) findViewById(R.id.number1);
        EditText number2 = (EditText) findViewById(R.id.number2);
        double n1;
        double n2;
        boolean invalidInput = false;

        try
        {
            n1 = Double.parseDouble(number1.getText().toString());
            n2 = Double.parseDouble(number2.getText().toString());
        }catch (Exception e)
        {
            System.out.println("Exception in SimpleMath.java: " + e.getLocalizedMessage());
            invalidInput = true;
            n1 = n2 = -1; // Dont set this to zero, because of the division... Lol<
        }
        double result = 0;

        Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
        String operator = mySpinner.getSelectedItem().toString();

        String output;

        if(operator.equalsIgnoreCase("+")) {
            result = n1 + n2;
            output = Double.toString(result);
        }
        else if(operator.equalsIgnoreCase("-")){
            result = n1 - n2;
            output = Double.toString(result);
        }
        else if(operator.equalsIgnoreCase("*"))
        {
            result = n1 * n2;
            output = Double.toString(result);
        }
        else if(operator.equalsIgnoreCase("/"))
        {
            if(n2 != 0) {
                result = n1 / n2;
                output = Double.toString(result);
            }
            else{
                output = "Invalid";
            }
        }
        else
        {
            output = "Invalid";
        }

        //textView = (TextView) findViewById(R.id.simpleMathResult);
        if(output != "Invalid" && !invalidInput)
        {
            textView.setText("Answer: " + Double.toString(result));
        }
        else
            textView.setText("Answer: " + output);

    }
}
