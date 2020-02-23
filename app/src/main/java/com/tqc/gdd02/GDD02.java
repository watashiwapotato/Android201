package com.tqc.gdd02;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.PeriodicSync;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GDD02 extends Activity {
    public static final String PREF_ExchangeRate = "Exchange_Rate";
    private Button calcbutton;
    private EditText fieldExchangeRate;
    private EditText fieldNTD;
    private TextView tvResult;
    private Double money;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // TO DO
        fieldExchangeRate = (EditText) findViewById(R.id.exchange);
        fieldNTD = (EditText) findViewById(R.id.ntd);
        tvResult = (TextView) findViewById(R.id.result);


        calcbutton = (Button) findViewById(R.id.btn);
        calcbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s;
                money = Double.parseDouble(fieldNTD.getText().toString()) / Double.parseDouble(fieldExchangeRate.getText().toString());
                s = String.format("%.2f", money);
                tvResult.setText(getString(R.string.usd_result)+s);
            }
        });
        SharedPreferences settings = getSharedPreferences(PREF_ExchangeRate, 0);
        fieldExchangeRate.setText(settings.getString(PREF_ExchangeRate, ""));


    }

    protected void onPause() {
        super.onPause();
        SharedPreferences settings = getSharedPreferences(PREF_ExchangeRate, 0);
        settings.edit().putString(PREF_ExchangeRate, fieldExchangeRate.getText().toString()).commit();
    }
}
