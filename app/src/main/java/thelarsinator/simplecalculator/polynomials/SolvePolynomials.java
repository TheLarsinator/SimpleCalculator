package thelarsinator.simplecalculator.polynomials;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import thelarsinator.simplecalculator.R;

public class SolvePolynomials extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_polynomials);

        textView = (TextView) findViewById(R.id.polResult);
        textView.setText("Answer:\n");
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
            //No input could mean that this is 0, and the user want to solve an equation of 1. order
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
