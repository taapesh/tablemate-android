package com.taapesh.tablemate.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.taapesh.tablemate.util.ActivityCode;
import com.taapesh.tablemate.util.ToolbarManager;

import com.taapesh.tablemate.R;


public class AddPaymentActivity extends AppCompatActivity {
    private static final String TAG = "AddPaymentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        new ToolbarManager(this).setupGoBackToolbar(
                ToolbarManager.ADD_PAYMENT_ACTIVITY_TITLE, ActivityCode.ADD_PAYMENT_ACTIVITY);
    }
}
