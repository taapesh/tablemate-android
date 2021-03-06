package com.taapesh.tablemate.activity;

import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.taapesh.tablemate.util.ActivityCode;
import com.taapesh.tablemate.util.Endpoints;
import com.taapesh.tablemate.util.PreferencesManager;
import com.taapesh.tablemate.util.ToolbarManager;
import com.taapesh.tablemate.util.UnlockBar;
import com.taapesh.tablemate.util.UnlockBar.OnUnlockListener;
import com.pnikosis.materialishprogress.ProgressWheel;

import com.taapesh.tablemate.R;


public class FinishAndPayActivity extends AppCompatActivity {
    private static final String TAG = "FinishAndPayActivity";
    private UnlockBar unlock;
    private ProgressWheel progressWheel;
    private View sliderThumb;

    private String userId;
    private String addressTableCombo;
    private String restaurantName;
    private String restaurantAddress;
    private String serverName;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_and_pay);

        new ToolbarManager(this).setupGoBackToolbar(
                ToolbarManager.FINISH_ACTIVITY_TITLE, ActivityCode.FINISH_ACTIVITY);

        sliderThumb = findViewById(R.id.img_thumb);

        progressWheel = (ProgressWheel) findViewById(R.id.progressWheel);
        progressWheel.setVisibility(View.INVISIBLE);

        unlock = (UnlockBar) findViewById(R.id.unlock);
        unlock.setOnUnlockListener(new OnUnlockListener() {
            @Override
            public void onUnlock() {
                finishTable();
            }
        });

        // Get table details from shared prefs
        final PreferencesManager prefs = new PreferencesManager(this);

        userId = prefs.getIdAsString();
        restaurantName = prefs.getRestaurantName();
        restaurantAddress = prefs.getRestaurantAddress();
        addressTableCombo = prefs.getAddrTableCombo();
        serverName = prefs.getServerName();
        token = prefs.getToken();
    }

    private void finishTable() {
        sliderThumb.setVisibility(View.INVISIBLE);
        unlock.hideLabel();
        progressWheel.setVisibility(View.VISIBLE);
        unlock.reset();

        final OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("restaurant_name", restaurantName)
                .add("restaurant_address", restaurantAddress)
                .add("address_table_combo", addressTableCombo)
                .add("server_name", serverName)
                .add("user_id", userId)
                .build();

        Request request = new Request.Builder()
                .url(Endpoints.FINISH_ENDPOINT)
                .post(formBody)
                .addHeader("Authorization", "Token " + token)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    if (response.code() == 404) {
                        Log.d(TAG, "404 Not Found: Table does not exist");
                    } else if (response.code() == 409) {
                        Log.d(TAG, "409 Conflict: Payment failed");
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                    onPaymentFailed();
                } else {
                    Log.d(TAG, "Payment success");
                    onPaymentSuccessful();
                }
            }
        });
    }

    private void onPaymentSuccessful() {
        runOnUiThread(new DisplayPaymentSuccessful());
        // TODO: Display option to rate server, then take to user home. If press back, take home
    }

    class DisplayPaymentSuccessful implements Runnable {
        // TODO: Visual effect for payment success
        @Override
        public void run() {
            progressWheel.setVisibility(View.INVISIBLE);
        }
    }

    private void onPaymentFailed() {
        progressWheel.setVisibility(View.INVISIBLE);
        sliderThumb.setVisibility(View.VISIBLE);
        unlock.showLabel();

        Log.d(TAG, "Payment failed");
        // TODO: Display payment error
    }
}
