/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manningcalculatormvc;

/**
 *
 * @author amanning
 */
// This is the View
// Its only job is to display what the user sees
// It performs no calculations, but instead passes
// information entered by the user to whomever needs
// it. 
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;

import javax.swing.*;
import javax.swing.border.Border;

public class CalculatorView extends JFrame {

    private JTextField firstNumber = new JTextField(10);
    private JLabel opLabel = new JLabel("+");
    private JTextField secondNumber = new JTextField(10);
    private JButton calculateButton = new JButton("=");
    private String[] opStrings = {"+", "-", "*", "/", "\\", "^" };
    private JButton opButtons[] ;
            // add additional operators here - buttons will be 
            // created with the given text displayed
    private String opString = "+"; // specifies operation to perform
    private JTextField calcSolution = new JTextField(10);
    private JTextField txtActive = firstNumber;

    CalculatorView() {

        // Sets up the view and adds the components
        JPanel calcPanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);

        // active textbox will be changed by calculator pad
        FocusAdapter textFieldFocusListener = new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JTextField me = (JTextField)evt.getSource();
            txtActive.setBackground(Color.white);
            txtActive = me;
            txtActive.setBackground(Color.LIGHT_GRAY);                
            }
        };
        
        firstNumber.addFocusListener(textFieldFocusListener);
        secondNumber.addFocusListener(textFieldFocusListener);
        firstNumber.setHorizontalAlignment(JTextField.RIGHT);
        secondNumber.setHorizontalAlignment(JTextField.RIGHT);
        calcSolution.setHorizontalAlignment(JTextField.RIGHT);
        
        calcPanel.add(firstNumber);
        
        calcPanel.add(opLabel);


        // option - different button for each operation
        JPanel opPanel = new JPanel();
        opPanel.setLayout(new GridLayout(opStrings.length, 0));
        opButtons = new JButton[opStrings.length];
        JButton btn;
        ActionListener btnEventHandler = new opButtonListener();
        for ( String opStr : opStrings ) {
            btn = new JButton( opStr );
            btn.addActionListener( btnEventHandler );
            opPanel.add(btn);
        }
        calcPanel.add(opPanel);
        
        
        // how about radio buttons to determine the operation?
        JPanel opRadioPanel = new JPanel();
        // have them appear in a vertical column 
        opRadioPanel.setLayout(new GridLayout(opStrings.length,0));
        JRadioButton rbtn;
        ButtonGroup bgrp = new ButtonGroup(); // used to group these buttons
        ActionListener rbtnEventHandler = new opRButtonListener();
        for ( String opStr : opStrings ) {
            rbtn = new JRadioButton( opStr );
            rbtn.addActionListener(rbtnEventHandler);
            bgrp.add(rbtn);
            opRadioPanel.add(rbtn);
        }
        // produce nice titled border - example found by internet search
        opRadioPanel.setBorder(BorderFactory.createTitledBorder("Op"));
       
        calcPanel.add( opRadioPanel );
 
        calcPanel.add(secondNumber);
        calcPanel.add(calculateButton);        
        
        calcPanel.add(calcSolution); // the calculate! button
        
        
        
        // add a grid of numbers for std number pad  4 x 3
        String[] btnTxt = { "7", "8", "9", "4", "5", "6", "1", "2", "3", 
            "c", "0", "." };
        JPanel panelPad = new JPanel();
        panelPad.setLayout(new GridLayout(4,3));
        panelPad.setBorder(BorderFactory.createTitledBorder("Number Pad"));
        numpadListener padListener = new numpadListener();
        // JButton btn; already defined above
        for( String op : btnTxt ) {
            btn = new JButton( op );
            btn.addActionListener( padListener );
            panelPad.add(btn);
        }
        calcPanel.add( panelPad );
        

        this.add(calcPanel);

        // End of setting up the components --------
    }

    public int getFirstNumber() {

        return Integer.parseInt(firstNumber.getText());

    }

    public int getSecondNumber() {

        return Integer.parseInt(secondNumber.getText());

    }

    public int getCalcSolution() {

        return Integer.parseInt(calcSolution.getText());

    }

    public void setCalcSolution(int solution) {

        calcSolution.setText(Integer.toString(solution));

    }
    
    // used by the controller to get the calculation
    public String getCalcOperator() {
        return opString;
    }

    
    // If the calculateButton is clicked execute a method
    // in the Controller named actionPerformed
    // it the responsibility of the Controller to set this
    void addCalculateListener(ActionListener listenForCalcButton) {

        calculateButton.addActionListener(listenForCalcButton);

    }

    // Open a popup that contains the error message passed
    void displayErrorMessage(String errorMessage) {

        JOptionPane.showMessageDialog(this, errorMessage);

    }

    // private listeners (controller not needed if within View behavior )
    private class opButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            // update our specified operation
            JButton me = (JButton)e.getSource();
            // opString = e.toString();  // returns long java details
            opString = me.getText();  // string on the button pressed
            opLabel.setText(opString);
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private class opRButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton me = (JRadioButton)e.getSource();
            opString = me.getText();
            opLabel.setText(opString);
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    private class numpadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton me = (JButton)e.getSource();
            if( "c" != me.getText() )
                txtActive.setText( txtActive.getText() + me.getText() );
            else {
                firstNumber.setText("0");
                secondNumber.setText("0");
                calcSolution.setText("0");
            }
                
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }       
    }
    
 /*   
            jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtfFocusHandler(evt);
            }
        });
*/



 
}
