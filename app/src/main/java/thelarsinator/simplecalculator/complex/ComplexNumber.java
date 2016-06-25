package thelarsinator.simplecalculator.complex;

/**
 * Created by Lars on 23.06.2016.
 */

//Class to represent complex numbers more easily
public class ComplexNumber
{
    private double a;
    private double b;

    private double r;
    private double theta;

    boolean isPolar;

    public ComplexNumber(double A, double B, boolean isP)
    {
        isPolar = isP;

        if(isP)
        {
            this.r = A;
            this.theta = B;
            a = findA();
            b = findB();
        }
        else
        {
            this.a = A;
            this.b = B;
            r = findRadius();
            theta = findAngle();
        }
    }

    private double findAngle()
    {
        if(isPolar)
            return theta;

        double ang;
        if(a < 0)
            ang = Math.atan(b/a) + Math.PI;
        else
            ang = Math.atan(b/a);

        return ang;
    }

    private double findRadius()
    {
        if(isPolar)
            return r;

        double radius;
        radius = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
        return radius;
    }

    private double findA()
    {
        if(!isPolar)
            return a;

        return r * Math.cos(theta);
    }

    private double findB()
    {
        if(!isPolar)
            return b;

        return r * Math.sin(theta);
    }

    public double getA()
    {
        return a;
    }

    public double getB()
    {
        return b;
    }

    public double getR()
    {
        return r;
    }

    public double getTheta()
    {
        return theta;
    }

    public void setNewValue(double newA, double newB, boolean isP)
    {
        this.isPolar = isP;
        if(isP)
        {
            this.r = newA;
            this.theta = newB;
            a = findA();
            b = findB();
        }
        else
        {
            this.a = newA;
            this.b = newB;
            r = findRadius();
            theta = findAngle();
        }
    }


}
