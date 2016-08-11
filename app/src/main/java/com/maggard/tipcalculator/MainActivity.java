package com.maggard.tipcalculator;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.view.View.OnClickListener;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements OnEditorActionListener,
OnClickListener{


    //define variables for widgets

    private EditText billAmountEditText;
    private TextView percentTextView;
    private TextView tipTotalTextView;
    private TextView totalTextView;
    private Button minusPercentButton;
    private Button plusPercentButton;

    //define instance variables

    private String billAmountString = "";
    private float tipPercent = .15f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmountEditText = (EditText) findViewById(R.id.BillAmountEditText);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        tipTotalTextView =(TextView) findViewById(R.id.tipTotalTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        minusPercentButton = (Button) findViewById(R.id.minusPercentButton);
        plusPercentButton = (Button) findViewById(R.id.plusPercentButton);

        //set listeners
        billAmountEditText.setOnEditorActionListener(this);
        minusPercentButton.setOnClickListener(this);
        plusPercentButton.setOnClickListener(this);

    }

    // controls the soft keyboard
    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if(actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED)

            calculateAndDisplay();
        return false;
    }

    //calculate and display numbers

    private void calculateAndDisplay() {

        //get bill amount from the user

        billAmountString = billAmountEditText.getText().toString(); //gets the info from the user
        float billAmount;
        if(billAmountString.equals("")){
            billAmount=0;

        }else{
            billAmount = Float.parseFloat(billAmountString);
        }

        //calculate tip and total

        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        //display the other results with formatting

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTotalTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));
    }

    //controls for percent buttons
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.minusPercentButton:
                tipPercent = tipPercent - .01f;
                calculateAndDisplay();
                break;
            case R.id.plusPercentButton:
                tipPercent = tipPercent + .01f;
                calculateAndDisplay();
                break;
        }
    }
}
