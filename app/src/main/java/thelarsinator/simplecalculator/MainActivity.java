package thelarsinator.simplecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import thelarsinator.simplecalculator.angles.AngleMath;
import thelarsinator.simplecalculator.complex.ComplexMath;
import thelarsinator.simplecalculator.polynomials.SolvePolynomials;
import thelarsinator.simplecalculator.simplemath.SimpleMath;
import thelarsinator.simplecalculator.time.TimeMath;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    //Function to start the simple math screen
    public void goToSimpleMath(View view)
    {
        Intent intent = new Intent(this, SimpleMath.class);
        startActivity(intent);
    }

    //Function to start the screen to solve polynomials
    public void goToPolynomials(View view)
    {
        Intent intent = new Intent(this, SolvePolynomials.class);
        startActivity(intent);
    }

    //Function to start trig screen
    public void goToAngle(View view)
    {
        Intent intent = new Intent(this, AngleMath.class);
        startActivity(intent);
    }

    //Function to start complex numbers screen
    public void goToComplex(View view)
    {
        Intent intent = new Intent(this, ComplexMath.class);
        startActivity(intent);
    }

    //Function to start time screen
    public void goToTime(View view)
    {
        Intent intent = new Intent(this, TimeMath.class);
        startActivity(intent);
    }
}
