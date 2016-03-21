package com.silion.itheima.outrom;

import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText mAccountEditText;
    EditText mPasswordEditText;
    CheckBox mRememberCheckBox;
    Button mLoginButton;

    private View.OnClickListener mLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mAccountEditText != null && mPasswordEditText != null) {
                String account = mAccountEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                if (!account.isEmpty() && !password.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.login_success, Toast.LENGTH_SHORT).show();

                    if (mRememberCheckBox.isChecked()) {
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                            File userFile = new File(Environment.getExternalStorageDirectory(), "user.txt");
                            FileOutputStream fos = null;
                            try {
                                fos = new FileOutputStream(userFile);
                                fos.write((account + ":" + password).getBytes());
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (fos != null) {
                                        fos.close();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            Toast.makeText(MainActivity.this, R.string.sdcard_not_available, Toast.LENGTH_SHORT).show();
                        }
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

        String save = readAccount();
        if (save != null && !save.isEmpty()) {
            String[] user = save.split(":");
            if (user.length > 0) {
                mAccountEditText.setText(user[0]);
                mPasswordEditText.setText(user[1]);
                mRememberCheckBox.setChecked(true);
            }
        }

        sdcardState();
    }

    public String readAccount() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File userFile = new File(Environment.getExternalStorageDirectory(), "user.txt");
            if (userFile.exists()) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(userFile);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    return br.readLine();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    public void sdcardState() {
        TextView totalSizeTextView = (TextView) findViewById(R.id.totalSizeTextView);
        TextView availableTextView = (TextView) findViewById(R.id.availableSizeTextView);

        File sdcardFile = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(sdcardFile.getPath());
        long blockSize = statFs.getBlockSizeLong();
        long blockCount = statFs.getBlockCountLong();
        long freeBlock = statFs.getFreeBlocksLong();

        totalSizeTextView.setText(formatSize(blockSize * blockCount) + "MB");
        availableTextView.setText(formatSize(blockSize * freeBlock) + "MB");
    }

    public String formatSize(long size) {
        return Formatter.formatFileSize(this, size);
    }
}
