package com.silion.itheima.sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText mAccountEditText;
    private EditText mPasswordEditText;
    private CheckBox mRememberCheckBox;
    private Button mLoginButton;

    private View.OnClickListener mLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mAccountEditText != null && mPasswordEditText != null) {
                String account = mAccountEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                if (!account.isEmpty() && !password.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.login_success, Toast.LENGTH_SHORT).show();

                    if (mRememberCheckBox.isChecked()) {
                        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("user", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("account", account);
                        editor.putString("password", password);
                        editor.commit();
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAccountEditText = (EditText) findViewById(R.id.accountEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);
        mRememberCheckBox = (CheckBox) findViewById(R.id.rememberCheckBox);

        mLoginButton = (Button) findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(mLoginListener);

        readAccount();
    }

    public void readAccount() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", 0);
        String account = sharedPreferences.getString("account", "");
        String password = sharedPreferences.getString("password", "");
        if (!account.isEmpty() && !password.isEmpty()) {
            mAccountEditText.setText(account);
            mPasswordEditText.setText(password);
            mRememberCheckBox.setChecked(true);
        }
    }
}
