package com.app.nfrealtime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    Button btnSubmit;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.et_input);
        btnSubmit = findViewById(R.id.btn_submit);
        tvResult = findViewById(R.id.tv_result);

        etInput.addTextChangedListener(onTextChangedListener());
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = etInput.getText().toString();
                String result2 = etInput.getText().toString().replaceAll(",", "");

                tvResult.setText("Result: " + result + " | origin : " + result2);
            }
        });
    }

    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etInput.removeTextChangedListener(this);

                try {
                    String originalEditable = editable.toString();

                    Long longval;
                    if (originalEditable.contains(",")){
                        originalEditable = originalEditable.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalEditable);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formatterString = formatter.format(longval);

                    // setting text after format to EditText
                    etInput.setText(formatterString);
                    etInput.setSelection(etInput.getText().length());

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                etInput.addTextChangedListener(this);
            }
        };
    }
}
