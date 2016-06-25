package thelarsinator.simplecalculator.simplemath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import thelarsinator.simplecalculator.R;

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
    }

    public void calculate(View view){

        EditText number1 = (EditText) findViewById(R.id.number1);
        EditText number2 = (EditText) findViewById(R.id.number2);
        double n1 = Double.parseDouble(number1.getText().toString());
        double n2 = Double.parseDouble(number2.getText().toString());

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
        if(output != "Invalid")
        {
            textView.setText("Answer: " + Double.toString(result));
        }
        else
            textView.setText("Answer: " + output);

    }
}
