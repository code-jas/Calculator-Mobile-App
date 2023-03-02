package com.angelo_silvestre.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;

import android.util.Log;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;



public class MainActivity extends AppCompatActivity {
    private static final String Tag = "MainActivity";

    private TextView resultLvl1, resultLvl2;
    private MaterialButton btnClear, btnBack, btnPercent, btnPlus, btnMinus, btnMultiply, btnDivision, btnEqual,
            btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnDecimal;

    private boolean isOperatorPressed = false;
    private boolean isEqualPressed = false;
    private boolean isNumberPressed = false;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        clickListener();
    }

    // CONTROL BUTTONS METHODS
    private void formatNumberText() {
        Log.d(Tag, "FormatNumberText : Started");
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        String updateNumber = resultLvl1.getText().toString().replaceAll(",", "");
        try {
            double num = Double.parseDouble(updateNumber);
            resultLvl1.setText(decimalFormat.format(num));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private void updateNumberText(String number) {
        Log.d(Tag, "Number Clicked : " + number);
        isNumberPressed = true;
        int length = resultLvl1.getText().toString().length();
        if(length == 15) {
            Toast.makeText(this, "You reached the maximum input!", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (isEqualPressed){
//            if(number.equals(".") && length== 1){
//                resultLvl1.setText("0.");
//                return;
//            }
//            isEqualPressed = false;
//            return;
//        }
        if (!isOperatorClicked()) {

            if (number.equals(".")) {

                if(resultLvl1.getText().toString().contains(".")) {
                    resultLvl1.setText(resultLvl1.getText().toString());
                    return;
                }
                resultLvl1.append(number);
                return;
            }
            if (resultLvl1.getText().toString().contains(".")) {
                resultLvl1.append(number);
                return;
            }

            if (((length == 1) && resultLvl1.getText().toString().equals("0"))) {
//                if(number.equals(".") && length== 1){
//                    resultLvl1.setText("0.");
//                    return;
//                }
                resultLvl1.setText(number);
            } else {
                resultLvl1.append(number);
            }
            formatNumberText();
        } else {
              Log.d(Tag, "Operator Clicked : Started + " + number +" + "+length);

//            if(number.equals(".") && length== 1){
//                     Log.d(Tag, "Contains (.) and length=1 + " + number);
//                resultLvl1.setText("0.");
//                return;
//            } else{
//                Log.d(Tag, "Else + " + number);
//
//            }
            resultLvl1.setText(number);

        }

    }

    private void removeOneNumber() {
            Log.d(Tag, "removeOneNumber: Get Started");
//        String lastCharacter = resultLvl1.getText().toString().substring(resultLvl1.length() - 1);
        String input = resultLvl1.getText().toString();
        String removedLastNumber;

        if (resultLvl1.getText().toString().length() != 1) {
            if (Character.isDigit(resultLvl1.getText().toString().substring(resultLvl1.length() - 2).charAt(0))) {
                removedLastNumber = input.substring(0, input.length() - 1);
            } else {
                removedLastNumber = input.substring(0, input.length() - 2);
            }
            resultLvl1.setText(removedLastNumber);
//            Toast.makeText(this, "Last Character: " + lastCharacter, Toast.LENGTH_SHORT).show();
        } else {
            resultLvl1.setText("0");
        }

    }

    private void clearResult() {
        Log.d(Tag, "Clear Result: Implemented");
        resultLvl1.setText("0");
        resultLvl2.setText("");
    }



    //OPERATOR BUTTONS METHODS
    private boolean isNumberClicked() {
        boolean toProceed = false;
        if (isNumberPressed) {
            toProceed = isNumberPressed;
            isNumberPressed = false;
        }

        return toProceed;
    }

    private boolean isOperatorClicked() {
        boolean toProceed = false;
        if (isOperatorPressed) {
            toProceed = isOperatorPressed;
            isOperatorPressed = false;
        }

        return toProceed;
    }

    private void dispPrevInpToresLvl2(String op) {
        Log.d(Tag, "Display previous Input to result Level 2: Started ");
        String input = resultLvl1.getText().toString();


        String operator = "<font color='#F27B7A'> " + op + " </font>";
        resultLvl2.setText(Html.fromHtml(input + operator));


    }

    private void percentage(){
        Log.d(Tag, "Percentage: Started");
        String input = resultLvl1.getText().toString();
        Log.d(Tag, "Input: "+input);
        dispPrevInpToresLvl2("%");
        division(stringToDouble(input),100);

    }

    @SuppressLint("SetTextI18n")
    private void addition(double num1, double num2) {
        double result = num1 + num2;
        if(result >= 99999999999.0){
            Log.d(Tag,"You reached the integer limit!!!    " + result +" ");
            resultLvl1.setText("+99,999,999,999");
            Toast.makeText(this, "Reached the maximum number", Toast.LENGTH_SHORT).show();
        }
        else {
            resultLvl1.setText(Double.toString(result));
            formatNumberText();
        }
    }

    @SuppressLint("SetTextI18n")
    private void difference(double num1, double num2) {
        double result = num1 - num2;

        if(result >= 99999999999.0){
            Log.d(Tag,"You reached the integer limit!!!    " + result +" ");
            resultLvl1.setText("+99,999,999,999");
            Toast.makeText(this, "Reached the maximum number", Toast.LENGTH_SHORT).show();
        }
        else {
            resultLvl1.setText(Double.toString(result));
            formatNumberText();
        }
    }

    @SuppressLint("SetTextI18n")
    private void multiplication(double num1, double num2) {
        double result = num1 * num2;

        if(result >= 99999999999.0){
            Log.d(Tag,"You reached the integer limit!!!    " + result +" ");
            resultLvl1.setText("+99,999,999,999");
            Toast.makeText(this, "Reached the maximum number", Toast.LENGTH_SHORT).show();
        }
        else {
            resultLvl1.setText(Double.toString(result));
            formatNumberText();
        }
    }

    @SuppressLint("SetTextI18n")
    private void division(double num1, double num2) {
        double result = num1 / num2;

        if(result >= 999999999.0){
            Log.d(Tag,"You reached the integer limit!!!  " + result +" ");
            Toast.makeText(this, "Reached the maximum number", Toast.LENGTH_SHORT).show();
        }
        else {
            resultLvl1.setText(Double.toString(result));
            formatNumberText();
        }

    }

    private void doubleCLickEqual() {

        Log.d(Tag, "Double Click Equal: Started");
        String inp = resultLvl2.getText().toString();
        Log.d(Tag, "String:" + inp);
        String[] twoInp = inp.split("[-+×÷]");
//        Log.d(Tag, "Input1:" + twoInp[0] + " Input2:" + twoInp[1]);
//        Log.d(Tag, "double clicked length: " + twoInp[0].length() + ":" + twoInp[1].length());
        if (inp.contains("+")) {
            resultLvl2.setText(
                    Html.fromHtml(
                            resultLvl1.getText().toString() + "<font color='#F27B7A'> + </font>" + twoInp[1]
                    )
            );

            addition(stringToDouble(resultLvl1.getText().toString()), stringToDouble(twoInp[1]));
        }
        if (inp.contains("-")) {
            resultLvl2.setText(
                    Html.fromHtml(
                            resultLvl1.getText().toString() + "<font color='#F27B7A'> - </font>" + twoInp[1]
                    )
            );
            difference(stringToDouble(resultLvl1.getText().toString()), stringToDouble(twoInp[1]));

        }
        if (inp.contains("×")) {
            resultLvl2.setText(
                    Html.fromHtml(
                            resultLvl1.getText().toString() + "<font color='#F27B7A'> × </font>" + twoInp[1]
                    )
            );
            multiplication(stringToDouble(resultLvl1.getText().toString()), stringToDouble(twoInp[1]));

        }
        if (inp.contains("÷")) {
            resultLvl2.setText(
                    Html.fromHtml(
                            resultLvl1.getText().toString() + "<font color='#F27B7A'> ÷ </font>" + twoInp[1]
                    )
            );
            division(stringToDouble(resultLvl1.getText().toString()), stringToDouble(twoInp[1]));

        }


    }
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    private boolean isMultipleDecimal(){
//        String[] strList = null;
//        strList = resultLvl2.getText().toString().split("");
//        Log.d(Tag, " isMultipleDecimal: " + strList.length );
//        if (strList.length > 1) {
//            return true;
//        }
//
//        return false;
//    }
    private double stringToDouble(String input) {
        Log.d(Tag, "String to Double: Started");
        double num = 0.0;
        input = input.replaceAll("[^\\d.]", "");
        try {
            num = Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return num;
    }

    @SuppressLint("SetTextI18n")
    private void equalOperator() {
        Log.d(Tag, "Equal Operator: Started");
        // split the input 1 and input 2, also the operator that will use
        String input1 = resultLvl2.getText().toString();
        String input2 = resultLvl1.getText().toString();
        char operatorUsed = resultLvl2.getText().toString().substring(resultLvl2.length() - 2).charAt(0);

        switch (operatorUsed) {
            case '+':

                addition(stringToDouble(input1), stringToDouble(input2));
                resultLvl2.append(input2);
                break;
            case '-':
                resultLvl2.append(input2);
                difference(stringToDouble(input1), stringToDouble(input2));
                break;
            case '×':
                resultLvl2.append(input2);
                multiplication(stringToDouble(input1), stringToDouble(input2));
                break;
            case '÷':
                resultLvl2.append(input2);
                division(stringToDouble(input1), stringToDouble(input2));
                break;
            default:
                doubleCLickEqual();
                break;
        }

        if(resultLvl1.getText().toString().equals("NaN")) {
            resultLvl1.setText("Error!");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void clickListener() {
        Log.d(Tag, "Click Listener: Started");
        // CLEAR BUTTONS
        btnClear.setOnClickListener(view -> clearResult());
        btnBack.setOnClickListener(view -> removeOneNumber());
        btnPercent.setOnClickListener(view -> percentage());

        // OPERATOR BUTTONS
        btnPlus.setOnClickListener(view -> {
            isOperatorPressed = true;
            if (isEqualPressed){
                dispPrevInpToresLvl2("+");
                isEqualPressed = false;
                return;
            }
            if(!resultLvl2.getText().toString().equals("")){
                if(isNumberClicked()){
                    equalOperator();
                }

            }

            dispPrevInpToresLvl2("+");
//

        });
        btnMinus.setOnClickListener(view -> {
            isOperatorPressed = true;
            if (isEqualPressed){
                dispPrevInpToresLvl2("-");
                isEqualPressed = false;
                return;
            }
            if(!resultLvl2.getText().toString().equals("")){
                if(isNumberClicked()){
                    equalOperator();
                }
            }
            dispPrevInpToresLvl2("-");

        });
        btnMultiply.setOnClickListener(view -> {
            isOperatorPressed = true;
            if (isEqualPressed){
                dispPrevInpToresLvl2("×");
                isEqualPressed = false;
                return;
            }
            if(!resultLvl2.getText().toString().equals("")){
                if(isNumberClicked()){
                    equalOperator();
                }
            }
            dispPrevInpToresLvl2("×");

        });
        btnDivision.setOnClickListener(view -> {
            isOperatorPressed = true;
            if (isEqualPressed){
                dispPrevInpToresLvl2("÷");
                isEqualPressed = false;
                return;
            }
            if(!resultLvl2.getText().toString().equals("")){
                if(isNumberClicked()){
                    equalOperator();
                }
            }
            dispPrevInpToresLvl2("÷");

        });

        // NUMBERS BUTTONS
        btnOne.setOnClickListener(view -> updateNumberText("1"));
        btnTwo.setOnClickListener(view -> updateNumberText("2"));
        btnThree.setOnClickListener(view -> updateNumberText("3"));


        btnFour.setOnClickListener(view -> updateNumberText("4"));
        btnFive.setOnClickListener(view -> updateNumberText("5"));
        btnSix.setOnClickListener(view -> updateNumberText("6"));


        btnSeven.setOnClickListener(view -> updateNumberText("7"));
        btnEight.setOnClickListener(view -> updateNumberText("8"));
        btnNine.setOnClickListener(view -> updateNumberText("9"));

        btnZero.setOnClickListener(view -> updateNumberText("0"));
        btnDecimal.setOnClickListener(view -> {
//            if(resultLvl1.getText().toString().contains(".")){
//                    resultLvl1.setText(resultLvl1.getText().toString());
//                    return;
//            }
            updateNumberText(".");
            if(resultLvl1.getText().toString().equals(".")){
                Log.d(Tag, "btnDecimal (.) and length=1 + " +resultLvl1.getText().toString());
                resultLvl1.setText("0.");

            }

//            if(isMultipleDecimal()){
//                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//            }

        });
        btnEqual.setOnClickListener(view -> {


            if((resultLvl1.getText().toString().length() <= 1 && resultLvl2.getText().toString().length()<=1 )|| resultLvl2.getText().toString().length()<=1|| resultLvl2.getText().toString().equals("")){
                resultLvl2.setText(resultLvl1.getText().toString());
            }
            else{
                equalOperator();
            }
            isEqualPressed = true;
            isOperatorPressed = true;
        });

    }

    private void initViews() {
        Log.d(Tag, "Initial Views: Started");

        // Display Result
        resultLvl1 = findViewById(R.id.result_firstLevel);
        resultLvl2 = findViewById(R.id.result_secondLevel);

        // Control Calculator Button
        btnClear = findViewById(R.id.btnClear);
        btnBack = findViewById(R.id.btnBack);
        btnPercent = findViewById(R.id.btnPercent);

        //Operators Buttons
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiply = findViewById(R.id.btnTimes);
        btnDivision = findViewById(R.id.btnDivide);
        btnEqual = findViewById(R.id.btnEqual);


        //Numbers Buttons
        btnDecimal = findViewById(R.id.btnDecimal);
        btnZero = findViewById(R.id.btnZero);

        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);

        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);

        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);

    }
}