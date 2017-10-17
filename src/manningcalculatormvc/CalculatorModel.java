/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manningcalculatormvc;

import static java.lang.Double.NaN;

/**
 *
 * @author amanning
 */
// The Model performs all the calculations needed
// and that is it. It doesn't know the View 
// exists

public class CalculatorModel {

	// Holds the value of the sum of the numbers
	// entered in the view
	
	private int calculationValue;
	
	public void addTwoNumbers(int firstNumber, int secondNumber){
		
		calculationValue = firstNumber + secondNumber;
		
	}
        
        public void operateTwoNumbers( int n1, int n2, String opString ) {
            
            switch( opString ) {
                case "+":
                    calculationValue = n1 + n2;
                    break;
                case "-":
                    calculationValue = n1 - n2;
                    break;
                case "*":
                    calculationValue = n1 * n2;
                    break;
                case "/":
                    calculationValue = n1 / n2;
                    break;
                case "\\":
                case "%":
                    calculationValue = n1 % n2;
                    break;
                case "^":
                    calculationValue = (int)Math.pow(n1,n2);
                    break;
                default:
                    calculationValue = (int)NaN;
            
            }
        }
	
	public int getCalculationValue(){
		
		return calculationValue;
		
	}
	
}
