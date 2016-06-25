package thelarsinator.simplecalculator.complex;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import thelarsinator.simplecalculator.R;

public class ComplexMath extends AppCompatActivity {

    private Spinner spinner;
    private static final String[]paths = {"+", "-", "*", "/"};

    private TextView switchStatus;
    private Switch mySwitch;

    private TextView prefix1;
    private TextView between1;
    private TextView suffix1;

    private TextView prefix2;
    private TextView between2;
    private TextView suffix2;


    private TextView complexResult;
    private boolean usePolar;

    GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_math);

        graph = (GraphView) findViewById(R.id.graph);


        complexResult = (TextView) findViewById(R.id.complexAnswer);
        complexResult.setTextSize(25);
        complexResult.setText("Answer: ");

        prefix1 = (TextView) findViewById(R.id.complexprefix1);
        between1 = (TextView) findViewById(R.id.complexbetween1);
        suffix1 = (TextView) findViewById(R.id.complexsufix1);

        prefix2 = (TextView) findViewById(R.id.complexprefix2);
        between2 = (TextView) findViewById(R.id.complexbetween2);
        suffix2 = (TextView) findViewById(R.id.complexsufix2);

/*        prefix1.setTextSize(25);
        between1.setTextSize(25);
        suffix1.setTextSize(25);
        prefix2.setTextSize(25);
        between2.setTextSize(25);
        suffix2.setTextSize(25);*/

        switchStatus = (TextView) findViewById(R.id.polarStatus);
        mySwitch = (Switch) findViewById(R.id.polarSwitch);

        //set the switch to ON
        mySwitch.setChecked(true);

        switchStatus.setTextSize(25);
        //attach a listener to check for changes in state
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    switchStatus.setText("Using polar form");
                    prefix1.setText("Radius: ");
                    between1.setText(", Angle: ");
                    suffix1.setText("");
                    prefix2.setText("Radius: ");
                    between2.setText(", Angle: ");
                    suffix2.setText("");
                    usePolar = true;
                }else{
                    switchStatus.setText("Using standard form");
                    prefix1.setText("");
                    between1.setText(" + ");
                    suffix1.setText("i");
                    prefix2.setText("");
                    between2.setText(" + ");
                    suffix2.setText("i");
                    usePolar = false;
                }

            }
        });

        //check the current state before we display the screen
        if(mySwitch.isChecked()){
            switchStatus.setText("Using polar form");
            prefix1.setText("Radius: ");
            between1.setText(", Angle: ");
            suffix1.setText("");
            prefix2.setText("Radius: ");
            between2.setText(", Angle: ");
            suffix2.setText("");
            usePolar=true;
        }
        else {
            switchStatus.setText("Using standard form");
            prefix1.setText("");
            between1.setText(" + ");
            suffix1.setText("i");
            prefix2.setText("");
            between2.setText(" + ");
            suffix2.setText("i");
            usePolar = false;
        }

        spinner = (Spinner)findViewById(R.id.z1spin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ComplexMath.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void solveComplex(View view)
    {
        EditText a1 = (EditText) findViewById(R.id.a1);
        EditText b1 = (EditText) findViewById(R.id.b1);
        EditText a2 = (EditText) findViewById(R.id.a2);
        EditText b2 = (EditText) findViewById(R.id.b2);

        double z1_a = Double.parseDouble(a1.getText().toString());
        double z1_b = Double.parseDouble(b1.getText().toString());
        double z2_a = Double.parseDouble(a2.getText().toString());
        double z2_b = Double.parseDouble(b2.getText().toString());

        ComplexNumber z1 = new ComplexNumber(z1_a, z1_b, usePolar);
        ComplexNumber z2 = new ComplexNumber(z2_a, z2_b, usePolar);

        Spinner mySpinner=(Spinner) findViewById(R.id.z1spin);
        String operator = mySpinner.getSelectedItem().toString();

        ComplexNumber result = new ComplexNumber(0, 0, true);
        String output = "";

        if(operator.equalsIgnoreCase("+")) {
            result = ComplexUtils.add(z1, z2);
        }
        else if(operator.equalsIgnoreCase("-")){
            result = ComplexUtils.subtract(z1, z2);
        }
        else if(operator.equalsIgnoreCase("*"))
        {
            result = ComplexUtils.multiply(z1, z2);
        }
        else if(operator.equalsIgnoreCase("/"))
        {
            if(z2.getA() == 0 && z2.getB() == 0)
                output = "Invalid";
            else
                result = ComplexUtils.divide(z1, z2);
        }
        else
        {
            output = "Invalid";
        }

        //textView = (TextView) findViewById(R.id.simpleMathResult);
        if(output != "Invalid")
        {
            complexResult.setText("Answer:\nz = " + ComplexUtils.toFullString(result));
        }
        else
            complexResult.setText("Answer: " + output);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(result.getA(), result.getB())
        });
        LineGraphSeries<DataPoint> conseries = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(result.getA(), -result.getB())
        });

        LineGraphSeries<DataPoint> seriesXMin;
        LineGraphSeries<DataPoint> seriesXMax;
        LineGraphSeries<DataPoint> seriesYMin;
        LineGraphSeries<DataPoint> seriesYMax;

        if(result.getA() >= 0) {
            seriesXMin = new LineGraphSeries<DataPoint>(new DataPoint[]{
                    new DataPoint(-result.getA() - 2, 0)
            });

            seriesXMax = new LineGraphSeries<DataPoint>(new DataPoint[]{
                    new DataPoint(result.getA() + 2, 0)
            });
        }
        else{
            seriesXMin = new LineGraphSeries<DataPoint>(new DataPoint[]{
                    new DataPoint(-result.getA() + 2, 0)
            });

            seriesXMax= new LineGraphSeries<DataPoint>(new DataPoint[]{
                    new DataPoint(result.getA() - 2, 0)
            });
        }
        if(result.getB() >= 0)
        {
            seriesYMin = new LineGraphSeries<DataPoint>(new DataPoint[] {
                    new DataPoint(0, -result.getB() -2)
            });

            seriesYMax = new LineGraphSeries<DataPoint>(new DataPoint[] {
                    new DataPoint(0, result.getB() + 2)
            });
        }
        else
        {
            seriesYMin = new LineGraphSeries<DataPoint>(new DataPoint[] {
                    new DataPoint(0, -result.getB() +2)
            });

            seriesYMax = new LineGraphSeries<DataPoint>(new DataPoint[] {
                    new DataPoint(0, result.getB() - 2)
            });
        }


       // series.setColor(000000);
        graph.removeAllSeries();
        series.setColor(Color.argb(255, 255, 125, 000));
        conseries.setColor(Color.BLUE);
        graph.addSeries(conseries);
        graph.addSeries(series);
        graph.addSeries(seriesXMin);
        graph.addSeries(seriesXMax);
        graph.addSeries(seriesYMin);
        graph.addSeries(seriesYMax);
    }
}
