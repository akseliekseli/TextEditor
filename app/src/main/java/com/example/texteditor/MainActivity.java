package com.example.texteditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

     Context context = null;
     EditText update_text;
    EditText file_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        update_text = (EditText) findViewById(R.id.textInput);
        file_text = (EditText) findViewById(R.id.file_name_field);
        System.out.println("KANSION SIJAINTI: "+context.getFilesDir());
    }

    public void readFile(View v){
        try{
            System.out.println(String.valueOf(file_text.getText()));
            InputStream ins=context.openFileInput(String.valueOf(file_text.getText()));
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s="";
            String output="";
            while((s=br.readLine()) != null){
                System.out.println(s);
                output = output.concat(s);
            }
            update_text.setText(output, TextView.BufferType.EDITABLE);
        ins.close();
        } catch (IOException e){
            Log.e("IOException", "Virhe syötteessä");
        }   finally {
            System.out.println("LUETTU");
        }
    }
    public void writeFile(View v){
        try{
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(String.valueOf(file_text.getText()), Context.MODE_PRIVATE));

            osw.write(String.valueOf(update_text.getText()));
            osw.close();
        } catch (IOException e){
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("KIRJOITETTU");
        }
    }
}