package com.example.a341activity1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    TextView finalmsg;

    EditText IDnum, Name, coursensection, address, number;

    private String filename = "StudentInfo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button clr = findViewById(R.id.button);
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IDnum.setText("");
                Name.setText("");
                coursensection.setText("");
                address.setText("");
                number.setText("");

            }
        });
        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stid = (IDnum = findViewById(R.id.id)).getText().toString();
                String stname = (Name = findViewById(R.id.name)).getText().toString();
                String coursensec = (coursensection = findViewById(R.id.coursensection)).getText().toString();
                String add = (address = findViewById(R.id.address)).getText().toString();
                String num = (number = findViewById(R.id.contact)).getText().toString();
                String[] arrayData = {stname, stid, coursensec, add, num};
                String message = "";
                for(int i=0; i < arrayData.length; i++) {
                    switch(i) {
                        case 0:
                            message += "I am ";
                            message += (arrayData[i] + " ");
                            break;
                        case 1:
                            message += "with ";
                            message += (arrayData[i] + " ");
                            break;
                        case 2:
                            message += "taken up ";
                            message += (arrayData[i] + " ");
                            break;
                        case 3:
                            message += "with ";
                            message += (arrayData[i] + ", ");
                            break;
                        case 4:
                            message += "for any question you may contact me at ";
                            message += arrayData[i];
                            break;
                    }
                }
                writeInFile(message);
            }
        });

        Button ret = findViewById(R.id.retrieve);
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (finalmsg = findViewById(R.id.finalmsg)).setText(readFromFile());
            }
        });
    }


    private void writeInFile(String data) {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFromFile() {
        FileInputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            inputStream = openFileInput(filename);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}