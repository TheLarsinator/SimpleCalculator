package thelarsinator.simplecalculator.complex;

import java.text.DecimalFormat;

/**
 * Created by Lars on 23.06.2016.
 */
public class ComplexUtils
{
    public static ComplexNumber add(ComplexNumber a, ComplexNumber b)
    {
        ComplexNumber out = new ComplexNumber(0, 0, true);
        out.setNewValue(a.getA() + b.getA(), a.getB() + b.getB(), false);
        return out;
    }

    public static ComplexNumber subtract(ComplexNumber a, ComplexNumber b)
    {
        ComplexNumber out = new ComplexNumber(0, 0, true);
        out.setNewValue(a.getA() - b.getA(), a.getB() - b.getB(), false);
        return out;
    }

    public static ComplexNumber multiply(ComplexNumber a, ComplexNumber b)
    {
        ComplexNumber out = new ComplexNumber(0, 0, true);
        if(a.isPolar == b.isPolar == true)
        {
            out.setNewValue(a.getR() * b.getR(), a.getTheta() + b.getTheta(), true);
        }
        else
        {
            out.setNewValue((a.getA()*b.getA())-(a.getB() * b.getB()), (a.getA() * b.getB()) + (b.getA() * b.getA()), false);
        }

        return out;
    }

    public static ComplexNumber divide(ComplexNumber a, ComplexNumber b)
    {
        ComplexNumber out = new ComplexNumber(0, 0, true);

        if(a.isPolar == b.isPolar == true)
        {
            out.setNewValue(a.getR() / b.getR(), a.getTheta() - b.getTheta(), true);
        }
        else
        {
            double div = b.getA() * b.getA() + b.getB() * b.getB();
            ComplexNumber bCon = conjugate(b);
            out.setNewValue((multiply(a, bCon).getA())/div, (multiply(a, bCon).getB())/div, false);
        }

        return out;
    }

    public static ComplexNumber conjugate(ComplexNumber out)
    {
        ComplexNumber con = new ComplexNumber(out.getA(), -out.getB(), false);
        return con;
    }

    public static String toString(ComplexNumber out)
    {
        //Format doubles
        DecimalFormat df = new DecimalFormat("#.###");
        String output = "";
        if(out.getA() != 0)
        {
            output = df.format(out.getA());
            if(out.getB() < 0)
                output += " - " + df.format(-1*out.getB()) + "i";
            else if(out.getB() > 0)
                output += " + " + df.format(out.getB()) + "i";
        }
        else
        {
            if(out.getB() < 0)
                output += " - " + df.format(-1*out.getB()) + "i";
            else if(out.getB() > 0)
                output += df.format(out.getB()) + "i";
            else
                output += 0;
        }



        return output;
    }

    public static String toFullString(ComplexNumber out)
    {
        //Format doubles
        DecimalFormat df = new DecimalFormat("#.###");

        String output = toString(out);
        output += ", \u017E = ";
        output += toString(conjugate(out));

        output += "\n";
        output += "Radius: ";
        output += df.format(out.getR());
        output += ", Angle: ";
        output += df.format(out.getTheta());

        return output;
    }
}
