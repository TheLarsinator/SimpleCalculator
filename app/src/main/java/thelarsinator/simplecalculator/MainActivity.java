package thelarsinator.simplecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }

    public void goToSimpleMath(View view)
    {
        Intent intent = new Intent(this, SimpleMath.class);
        startActivity(intent);
    }

    public void goToPolynomials(View view)
    {
        Intent intent = new Intent(this, SolvePolynomials.class);
        startActivity(intent);
    }

    public void goToAngle(View view)
    {
        Intent intent = new Intent(this, AngleMath.class);
        startActivity(intent);
    }

    public void goToComplex(View view)
    {
        Intent intent = new Intent(this, ComplexMath.class);
        startActivity(intent);
    }

    public void goToTime(View view)
    {
        Intent intent = new Intent(this, TimeMath.class);
        startActivity(intent);
    }
}
