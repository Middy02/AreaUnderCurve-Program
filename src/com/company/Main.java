package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Variables
        double theData;
        double lowRange;
        double highRange;
        boolean EXIT = false;

        //Linear
        double m = 0;
        double b = 0;

        //Quadratic & Sinusoidal
        double a = 0;
        double h = 0;
        double c = 0;
        double k = 0;
        double d = 0;
        double area = 0;
        boolean degrees = false;

        ABFunctions functionType;
        AreaBtwn calcArea = new AreaBtwn();

        while (!EXIT) {
            System.out.println("What type of function is f(x)?" + "\n1) Linear" + "    2) Quadratic" + "    3) Sinusoidal" + "\n4) Exit");
            Scanner scanner = new Scanner(System.in);
            theData = scanner.nextInt();

            switch ((int) theData) {
                case 1:
                    functionType = ABFunctions.LINEAR;
                    break;
                case 2:
                    functionType = ABFunctions.QUADRATIC;
                    break;
                case 3:
                    functionType = ABFunctions.SINE;
                    break;
                case 4:
                    EXIT = true;
                    System.out.println("Exiting Program");
                    functionType = ABFunctions.INVALID;
                    break;
                default:
                    functionType = ABFunctions.INVALID;
                    System.out.println(functionType);
                    break;
            }//end switch
            //Data for Linear Function
            if (functionType.equals(ABFunctions.LINEAR)) {
                System.out.println("For the function f(x) = mx + b: ");
                System.out.println("What is the slope?");
                m = scanner.nextDouble();

                System.out.println("What is the y-intercept?");
                b = scanner.nextDouble();
            }//end if
            //Data for Quadratic Function
            else if (functionType.equals(ABFunctions.QUADRATIC)) {
                System.out.println("For the function f(x) = a(x-h)^2 + c: ");
                System.out.println("What is the a value?");
                a = scanner.nextDouble();

                System.out.println("What is the h value?");
                h = scanner.nextDouble();

                System.out.println("What is the c value?");
                c = scanner.nextDouble();
            }//end if
            //Data for Sinusoidal Function
            else if (functionType.equals(ABFunctions.SINE)) {
                System.out.println("Select Function Type: \n1) Sine \n2) Cosine");
                theData = scanner.nextInt();

                switch ((int) theData) {
                    case 1:
                        functionType = ABFunctions.SINE;
                        break;
                    case 2:
                        functionType = ABFunctions.COSINE;
                        break;
                    default:
                        functionType = ABFunctions.INVALID;
                        System.out.println(functionType);
                        break;
                }//end switch

                System.out.println("Select Axis Type: \n1) Degrees \n2) Radians");
                theData = scanner.nextInt();

                switch ((int) theData) {
                    case 1:
                        degrees = true;
                        break;
                    case 2:
                        degrees = false;
                        break;
                    default:
                        degrees = false;
                        System.out.println("None Selected: Default is Radians");
                        break;
                }//end switch

                if (functionType.equals(ABFunctions.SINE)) {
                    System.out.println("For the function f(x) = a(sin(k(x-c))) + d: ");
                } else if (functionType.equals(ABFunctions.COSINE)) {
                    System.out.println("For the function f(x) = a(cos(k(x-c))) + d: ");
                }

                System.out.println("What is the a value?");
                a = scanner.nextDouble();

                System.out.println("What is the k value?");
                k = scanner.nextDouble();

                System.out.println("What is the c value?");
                c = scanner.nextDouble();

                System.out.println("What is the d value?");
                d = scanner.nextDouble();

            }//end if

            //General Data Collection
            if (!EXIT && !functionType.equals(ABFunctions.INVALID)) {
                System.out.println("What is the value for G(x)?");
                theData = scanner.nextDouble();
                calcArea.set_Gx(theData);

                System.out.println("What is the lower bound for the domain?");
                lowRange = scanner.nextDouble();

                System.out.println("What is the upper bound for the domain?");
                highRange = scanner.nextDouble();
                calcArea.setDomain(lowRange, highRange);

                System.out.println("How many measurements over this interval: " + lowRange + " <= x <= " + highRange + " would you like to perform?");
                theData = scanner.nextInt();
                calcArea.setIterations((int) theData);
            }//end if

            //Calculate Area
            if (functionType.equals(ABFunctions.LINEAR)) {
                area = calcArea.CalcAreaBetween(m, b);
            }
            else if (functionType.equals(ABFunctions.QUADRATIC)) {
                area = calcArea.CalcAreaBetween(a, h, c);
            }
            else if (functionType.equals((ABFunctions.SINE)) || functionType.equals(ABFunctions.COSINE)) {
                area = calcArea.CalcAreaBetween(functionType, a, k, c, d, degrees);
            }

            //Print out area
            if (calcArea.isDisplayArea() && !EXIT) {
                System.out.println("\nArea: " + area + "\n");
                calcArea.resetData();
            }

        }//end while loop

    }//end main method
}//end main class
