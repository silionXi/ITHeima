package com.silion.itheima.smssender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mRecipientsEditText;
    private EditText mBodyEditText;
    private Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecipientsEditText = (EditText) findViewById(R.id.recipientsEditText);
        mBodyEditText = (EditText) findViewById(R.id.bodyEditText);
        mSendButton = (Button) findViewById(R.id.sendButton);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipients = mRecipientsEditText.getText().toString();
                String body = mBodyEditText.getText().toString();

                SmsManager sm = SmsManager.getDefault();
                sm.sendTextMessage(recipients, null, body, null, null);
            }
        });
    }
}
