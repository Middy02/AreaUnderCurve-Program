package com.company;

public class AreaBtwn {

    //Private Variables
    private double Gx = 0;
    private double lowRange;
    private double highRange;
    private int iterations = 0;

    private double M = 0;
    private double B = 0;
    private double area = 0;
    private boolean Degrees = false;
    private ABFunctions functionType = ABFunctions.INVALID;

    private double A = 0;
    private double H = 0;
    private double C = 0;
    private double K = 0;
    private double D = 0;

    private boolean displayArea = false;
    public boolean isDisplayArea() { return displayArea; }

    //PUBLIC Methods

    //resetData method to reset all data
    public void resetData(){
        Gx = 0;
        lowRange = 0;
        highRange = 0;
        iterations = 0;
        M = 0;
        B = 0;
        area = 0;
        Degrees = false;
        functionType = ABFunctions.INVALID;
        A = 0;
        H = 0;
        C = 0;
        K = 0;
        D = 0;
        displayArea = false;
    }//end resetData

    //set_Gx method to set the value for the g(x) constant
    public void set_Gx (double b){
        Gx = b;
    }

    //setDomain method to set the low and high ranges of the domain
    public void setDomain(double low, double high){
        lowRange = low;
        highRange = high;
    }//end setDomain

    //setIterations method to set the number of slices
    public void setIterations(int n){
        iterations = n;
    }

    //CalcAreaBetween method to call setLineData and calcAreaLinear
    public double CalcAreaBetween(double m, double b){
        setLineData(m, b);
        area = calcAreaLinear();
        return area;
    }//end CalcAreaBetween

    //CalcAreaBetween method to call setQuadraticData and calcAreaQuadratic
    public double CalcAreaBetween(double a, double h, double c){
        setQuadraticData(a, h, c);
        area = calcAreaQuadratic();
        return area;
    }//end CalcAreaBetween

    //CalcAreaBetween method to call setSinusoidalData and calcAreaSine or calcAreaCosine
    public double CalcAreaBetween(ABFunctions f, double a, double k, double c, double d, boolean degrees){
        setSinusoidalData(f, a, k, c, d, degrees);
        if (functionType.equals(ABFunctions.SINE)){
            area = calcAreaSine();
        }
        else {
            area = calcAreaCosine();
        }
        return area;
    }//end CalcAreaBetween


    //PRIVATE Methods

    //setLineData method to set linear variables
    private void setLineData(double m, double b){
        M = m;
        B = b;
    }//end setLineData

    //calcAreaLinear method to calculate the area between the line and constant
    private double calcAreaLinear(){
        double i = (highRange - lowRange) / iterations;
        double y;

        for (double x = lowRange; x < highRange; x = x + i){
            y = (M * x) + B;
            if (y >= Gx && M > 0){
                y = y + (M * i);
            }
            else if (y <= Gx && M < 0){
                y = y + (M * i);
            }
            y = y - Gx;
            area  = area + (i * y);
        }//end for

        displayArea();

        return area;
    }//end calcAreaLinear

    //setQuadraticData method to set quadratic variables
    private void setQuadraticData(double a, double h, double c){
        A = a;
        H = h;
        C = c;
    }//end setQuadraticData

    //calcAreaQuadratic method to calculate the area between the curve and constant
    private double calcAreaQuadratic(){
        double i = (highRange - lowRange) / iterations;
        double y;
        double y2;

        for (double x = lowRange; x < highRange; x = x + i){
            y = A * (Math.pow((x - H), 2)) + C;
            y2 = A * (Math.pow((x + i - H), 2)) + C;

            if (y <= Gx && y > y2){
                y = y2;
            }
            else if (y >= Gx && y < y2){
                y = y2;
            }

            y = y - Gx;
            area = area + (i * y);
        }//end for

        displayArea();

        return area;
    }//end calcAreaQuadratic

    //setSinusoidalData method to set sinusoidal variables
    private void setSinusoidalData(ABFunctions f, double a, double k, double c, double d, boolean degrees){
        functionType = f;
        A = a;
        K = k;
        C = c;
        D = d;
        Degrees = degrees;
    }//end setSinusoidalData

    //calcAreaSine method to calculate the area between the curve and constant
    private double calcAreaSine(){

        if (Degrees){
            //Convert x-axis variables from degrees to radians
            K = Math.toRadians(K);
            C = Math.toRadians(C);
            highRange = Math.toRadians(highRange);
            lowRange = Math.toRadians(lowRange);
        }//end if

        double i = (highRange - lowRange) / iterations;
        double y;
        double y2;

        for (double x = lowRange; x < highRange; x = x + i){
            y = A * (Math.sin(K * (x - C))) + D;
            y2 = A * (Math.sin(K * (x + i - C))) + D;

            if (y <= Gx && y > y2){
                y = y2;
            }
            else if (y >= Gx && y < y2){
                y = y2;
            }

            y = y - Gx;
            area = area + (i * y);
        }//end for

        displayArea();

        return area;
    }//end calcAreaSine

    //calcAreaCosine method to calculate the area between the curve and constant
    private double calcAreaCosine(){

        if (Degrees){
            //Convert x-axis variables from degrees to radians
            K = Math.toRadians(K);
            C = Math.toRadians(C);
            highRange = Math.toRadians(highRange);
            lowRange = Math.toRadians(lowRange);
        }//end if

        double i = (highRange - lowRange) / iterations;
        double y;
        double y2;

        for (double x = lowRange; x < highRange; x = x + i){
            y = A * (Math.cos(K * (x - C))) + D;
            y2 = A * (Math.cos(K * (x + i - C))) + D;

            if (y <= Gx && y > y2){
                y = y2;
            }
            else if (y >= Gx && y < y2){
                y = y2;
            }

            y = y - Gx;
            area = area + (i * y);
        }//end for

        displayArea();

        return area;
    }//end calcAreaCosine


    //displayArea method to display the resulting area
    private void displayArea(){
       displayArea = true;
    }//end displayArea

}//end AreaBtwn class