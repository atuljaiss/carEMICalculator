package com.example.login;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;  
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EmiCalculator extends AppCompatActivity  {



    IMyAidlInterface mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_emi);
        Intent intentService = new Intent(this, EMIService.class);
        bindService(intentService, mConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void CalculateEMI(View view){
        TextView textView = (TextView) findViewById(R.id.textView5);
        TextView error = (TextView) findViewById(R.id.textViewError);
        EditText text1 = (EditText) findViewById(R.id.editTextNumberSigned);
        EditText text2 = (EditText) findViewById(R.id.editTextNumberSigned2);
        EditText text3 = (EditText) findViewById(R.id.editTextNumberSigned3);
        EditText text4 = (EditText) findViewById(R.id.editTextNumberSigned4);
        try {
            double principal_amount = Double.parseDouble(text1.getText().toString());
            double rate_of_interest = Double.parseDouble(text2.getText().toString());
            int tenure_month = Integer.parseInt(text3.getText().toString());
            double down_payment = Double.parseDouble(text4.getText().toString());


            text1.onEditorAction(EditorInfo.IME_ACTION_DONE);
            text2.onEditorAction(EditorInfo.IME_ACTION_DONE);
            text3.onEditorAction(EditorInfo.IME_ACTION_DONE);
            text4.onEditorAction(EditorInfo.IME_ACTION_DONE);

            if(rate_of_interest==0 || principal_amount==0){
                error.setText("Error");
                textView.setText("");
            }else {
                error.setText("");


                double result = 0;
                try {
                    principal_amount = principal_amount - down_payment;
                    result =  mService.CalculateEMI(principal_amount,rate_of_interest,tenure_month);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if(result==0 || result>1000000000 || rate_of_interest==0 || tenure_month==0) {
                    error.setText("error");
                    textView.setText("");
                }
                else textView.setText(String.format("EMI: Only %s Rs/- ",result));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    public void resetValue(View view) {

        EditText text1 = (EditText) findViewById(R.id.editTextNumberSigned);
        EditText text2 = (EditText) findViewById(R.id.editTextNumberSigned2);
        EditText text3 = (EditText) findViewById(R.id.editTextNumberSigned3);
        EditText text4 = (EditText) findViewById(R.id.editTextNumberSigned4);
        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
    }

}
