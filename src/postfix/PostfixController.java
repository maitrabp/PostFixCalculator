/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postfix;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.*; 
import java.util.*;

/**
 * FXML Controller class
 *
 * @author Maitra
 */
public class PostfixController implements Initializable {

    @FXML
    private Label resultWindow;
    @FXML
    private Button one;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button add;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button six;
    @FXML
    private Button subtract;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button multiplication;
    @FXML
    private Button zero;
    @FXML
    private Button clear;
    @FXML
    private Button divide;
    @FXML
    private Button calculate;
    @FXML
    private Button space;
    @FXML
    private Label instructions;
    private Stack<Double> stack = new Stack<Double>(); 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instructions.setText("USE SPACE AS DELIMETER BETWEEN NUMS & OPERATORS!");
        resultWindow.setText(" ");
        // TODO
    }    

    @FXML
    //WRITE 1 TO resultWindow
    private void btnClick1(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + "1");
    }

    @FXML
    //WRITE 2 TO resultWindow
    private void btnClick2(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + "2");
    }

    @FXML
    //WRITE 3 TO resultWindow
    private void btnClick3(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + "3");
    }

    @FXML
    //WRITE + TO resultWindow
    private void btnClickPlus(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + "+");
    }

    @FXML
    //WRITE 4 TO resultWindow
    private void btnClick4(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + "4");
    }

    @FXML
    //WRITE 5 TO resultWindow
    private void btnClick5(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + "5");
    }

    @FXML
    //WRITE 6 TO resultWindow
    private void btnClick6(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + "6");
    }

    @FXML
    //WRITE - TO resultWindow
    private void btnClickMinus(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + "-");
    }

    @FXML
    //WRITE 7 TO resultWindow
    private void btnClick7(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + "7");
    }

    @FXML
    //WRITE 8 TO resultWindow
    private void btnClick8(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + "8");
    }

    @FXML
    //WRITE 9 TO resultWindow
    private void btnClick9(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + "9");
    }

    @FXML
    //WRITE * TO resultWindow
    private void btnClickMultiply(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + "*");
    }

    @FXML
    //WRITE 0 TO resultWindow
    private void btnClick0(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + "0");
    }

    @FXML
    //Clear the resultWindow
    private void btnClickClear(ActionEvent event) {
        resultWindow.setText(" ");
    }

    @FXML
    //WRITE / TO resultWindow
    private void btnClickDivide(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + "/");
    }

    @FXML
    //WRITE 1 TO resultWindow
    private void btnClickCalculate(ActionEvent event) {
        String operation = resultWindow.getText();
        //COMPUTE POSTFIX WHICH IS RETURNED AS A FLOAT
        double solution = computePostfix(operation);
        
        /*Convert to int if there is no tenth place, else to be precise display as double*/
        if(Math.floor(solution) == solution)
        {
            int convertToInt = (int)solution;
            //PRINT TO SCREEN
            resultWindow.setText(String.valueOf(convertToInt));
        }
        else
        {
            //PRINT TO SCREEN
            resultWindow.setText(String.valueOf(solution));
        }
        
    }

    @FXML
    private void btnClickSpace(ActionEvent event) {
        resultWindow.setText(resultWindow.getText() + " ");
    }
    
    //CALCULATE POSTFIX
    double computePostfix(String operation)
    {
        double solution = 0;
        int arrayIterator = 0;
        operation += " ";
        //APPEND EQUALS TO CHECK FOR END & PERFORM FINAL OPERATIONS
        operation += "=";
        String[] tempArray = operation.split(" ");
        //FILL STACK
        while(arrayIterator < tempArray.length)
        {
            //IT's A PLUS OPERATOR SO SUM
            if(tempArray[arrayIterator].equals("+"))
            {
                if(!stack.empty())
                {
                    double num1 = stack.peek();
                    stack.pop();
                    double result = stack.peek() + num1;
                    stack.pop();
                    stack.push(result);
                }
            }
            //IT's A MINUS OPERATOR SO DIFF
            else if (tempArray[arrayIterator].equals("-"))
            {
                double num1 = stack.peek();
                    stack.pop();
                    double result = stack.peek() - num1;
                    stack.pop();
                    stack.push(result);
            }
            //IT's A MULT OPERATOR SO MULTIPLY
            else if (tempArray[arrayIterator].equals("*"))
            {
                double num1 = stack.peek();
                    stack.pop();
                    double result = stack.peek() * num1;
                    stack.pop();
                    stack.push(result);
            }
            //IT's A DIVISION OPERATOR SO DIVIDE
            else if (tempArray[arrayIterator].equals("/"))
            {
                double num1 = stack.peek();
                    stack.pop();
                    double result = stack.peek() / num1;
                    stack.pop();
                    stack.push(result);
            }
            //THIS INDICATES TO STORE STACKTOP TO RESULT & CLEAR STACK
            else if (tempArray[arrayIterator].equals("="))
            {
                if(!stack.empty())
                {
                    solution = stack.peek();
                }
                //EMPTY THE STACK TO CLEAR STORAGE/RAM
                while(!stack.empty())
                {
                    stack.pop();
                }
            }
            //LAST SELECTION STATEMENT -> HAS TO BE NUMBER SO CONVERT TO FLOAT & PUSH TO STACK
            else
            {
                //JUST TO MAKE SURE, CHECK IT'S NOT A EMPTY STRING
                if(!tempArray[arrayIterator].equals(""))
                {
                    double num = Float.parseFloat(tempArray[arrayIterator]);
                    stack.push(num);
                }
            }
            arrayIterator++;
        }
        return solution;
    }
}
