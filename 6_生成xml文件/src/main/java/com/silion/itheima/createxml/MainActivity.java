package com.silion.itheima.createxml;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button mCreateXmlButton;
    private List<Sms> mSmsList;

    private View.OnClickListener mCreateXmlListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            stringBuffer.append("<Messages>");
            for (Sms sms : mSmsList) {
                stringBuffer.append("<Sms>");
                stringBuffer.append("<Address>" + sms.getAddress() + "</Address>");
                stringBuffer.append("<Body>" + sms.getBody() + "</Body>");
                stringBuffer.append("<Date>" + sms.getDate() + "</Date>");
                stringBuffer.append("<Type>" + sms.getType() + "</Type>");
                stringBuffer.append("</Sms>");
            }
            stringBuffer.append("</Messages>");

            File smsFile = new File(Environment.getExternalStorageDirectory(), "sms.xml");
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(smsFile);
                out.write(stringBuffer.toString().getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCreateXmlButton = (Button) findViewById(R.id.createXmlButton);
        mCreateXmlButton.setOnClickListener(mCreateXmlListener);

        initMessage();
    }

    private void initMessage() {
        mSmsList = new ArrayList<>();
        Sms sms;
        for (int i = 0; i < 10; i++) {
            sms = new Sms("0123456789" + i, "我是短信" + i, System.currentTimeMillis(), 1);
            mSmsList.add(sms);
        }
    }
}
