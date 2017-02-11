package com.aurensanx.runtempocalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI(findViewById(R.id.parent));

        final Button button = (Button) findViewById(R.id.calculateRythm);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double[] inputValues = readInputValues(new int[]{R.id.hoursId, R.id.minutesId, R.id.secondsId, R.id.kilometersId});
                long[] result = calculateResult(inputValues);
                toggleResultView(result);
            }
        });
    }

    private double[] readInputValues(int[] ids) {
        double[] inputValues = new double[ids.length];
        EditText editText;
        String inputValueString;

        for (int i = 0; i < ids.length; i++) {
            editText = (EditText) findViewById(ids[i]);
            inputValueString = editText.getText().toString();
            inputValues[i] = inputValueString.isEmpty() ? 0 : Double.parseDouble(inputValueString);
        }
        return inputValues;
    }

    protected long[] calculateResult(double[] inputValues) {
        long[] result = new long[]{0, 0};
        if (inputValues.length == 4 && inputValues[3] != 0) {
            double rythm = ((inputValues[0] * 60) + inputValues[1] + (inputValues[2] / 60)) / inputValues[3];
            result[0] = (long) rythm;
            result[1] = Math.round((rythm - result[0]) * 60);
        }

        return result;
    }

    private void toggleResultView(long[] result) {
        final TextView resultTextView = (TextView) findViewById(R.id.result);
        String resultText = getString(R.string.rythm, result[0], result[1]);
        resultTextView.setText(resultText);
        resultTextView.setVisibility(View.VISIBLE);
    }

    private void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    Utils.hideSoftKeyboard(MainActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

}
