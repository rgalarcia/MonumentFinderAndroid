package com.example.usuari.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class Profile extends AppCompatActivity {

    public static String message;
    public static String message2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.USERNAME);
        message2 = intent.getStringExtra(MainActivity.IPADDRESS);

        // Capture the layout's TextView and set the string as its text
        TextView Welcome = findViewById(R.id.WelcomeText);
        Welcome.setText("Hello, "+message+", this is your profile");



        Thread thread = new Thread (new Runnable(){
            InputStream stream = null;
            String str = "";
            String result = null;
            Handler handler = new Handler();

            public void run(){

                try {
                    final String uri = String.format("http://"+message2+":9000/AndroidProfile");

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
                                text = "Log in successful.";


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


    }

}