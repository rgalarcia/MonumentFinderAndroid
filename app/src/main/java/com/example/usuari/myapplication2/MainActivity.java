package com.example.usuari.myapplication2;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.loginButton);
        final TextView userInput = findViewById(R.id.userInput);
        final TextView passInput = findViewById(R.id.passInput);
        final TextView ipInput = findViewById(R.id.ipInput);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if( TextUtils.isEmpty(userInput.getText()) || TextUtils.isEmpty(passInput.getText()) ){

                    Context context = getApplicationContext();
                    CharSequence text = "Error: Please fill in all fields.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }else{

                    Thread thread = new Thread (new Runnable(){
                        InputStream stream = null;
                        String str = "";
                        String result = null;
                        Handler handler = new Handler();

                        public void run(){

                            try {
                                final String uri = String.format("http://"+ipInput.getText()+":9000/Android?u="+userInput.getText()+"&p="+passInput.getText());

                                URL url = new URL(uri);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setReadTimeout(1500);
                                conn.setConnectTimeout(1500);
                                conn.setRequestMethod("GET");
                                conn.setDoInput(true);
                                conn.connect();

                                stream = conn.getInputStream();
                                BufferedReader reader = null;
                                StringBuilder sb = new StringBuilder();
                                reader = new BufferedReader(new InputStreamReader(stream));

                                String line = null;
                                while((line = reader.readLine()) != null){
                                    sb.append(line);
                                }
                                result = sb.toString();

                                handler.post(new Runnable(){
                                   public void run() {

                                       Context context = getApplicationContext();
                                       CharSequence text;
                                       int duration = Toast.LENGTH_SHORT;

                                       if (result.contentEquals("OK"))
                                       {
                                           text = "Success.";
                                       } else {
                                           text = "Error: Username or password is incorrect.";
                                       }

                                       Toast toast = Toast.makeText(context, text, duration);
                                       toast.show();
                                   }
                                });

                            } catch (ProtocolException e) {
                                e.printStackTrace();
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Exception: Protocol Exception", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Exception: Malformed URL", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Exception: IO Exception", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                        }
                    });

                    thread.start();

                    //Intent i = new Intent(getApplicationContext(), Login.class);
                    //startActivity(i);
                }
            }
        });
    }
}
