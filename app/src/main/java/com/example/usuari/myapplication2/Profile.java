package com.example.usuari.myapplication2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class Profile extends AppCompatActivity {

    public static String username;
    public static String ipaddress;
    public static String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        // Get the Intent that started this activity and extract the strings
        Intent intent = getIntent();
        username = intent.getStringExtra(MainActivity.USERNAME);
        ipaddress = intent.getStringExtra(MainActivity.IPADDRESS);


        // Capture the layout's TextView and set the string as its text
        TextView Welcome = findViewById(R.id.WelcomeText);
        Welcome.setText("Hello, " + username + ", this is your profile");


        // Obtain the profile information from the server
        Thread thread = new Thread(new Runnable() {
            InputStream stream = null;
            String str = "";
            String result = null;
            String param = null;
            Handler handler = new Handler();

            public void run() {

                try {

                    final String uri = String.format("http://" + ipaddress + ":9000/AndroidProfile?u=" + username);

                    URL url = new URL(uri);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(1000);
                    conn.setConnectTimeout(1000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();

                    stream = conn.getInputStream();
                    BufferedReader reader = null;
                    StringBuilder sb = new StringBuilder();
                    reader = new BufferedReader(new InputStreamReader(stream));

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }


                    param = sb.toString();

                    handler.post(new Runnable() {
                        public void run() {
                            Context context = getApplicationContext();
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, "He conseguido el parametro:" + param, duration);
                            toast.show();

                        }
                    });

                    final String uri2 = String.format("http://" + ipaddress + ":9000/AndroidProfileLoad?param=" + param);

                    URL url2 = new URL(uri2);
                    HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
                    conn2.setReadTimeout(1000);
                    conn2.setConnectTimeout(1000);
                    conn2.setRequestMethod("GET");
                    conn2.setDoInput(true);
                    conn2.connect();

                    stream = conn2.getInputStream();
                    BufferedReader reader2 = null;
                    StringBuilder sb2 = new StringBuilder();
                    reader2 = new BufferedReader(new InputStreamReader(stream));

                    String line2 = null;
                    while ((line2 = reader2.readLine()) != null) {
                        sb2.append(line2);
                    }

                    result = sb2.toString();


                    try {
                        JSONArray jsonarray = new JSONArray(result);
                        JSONObject jsonobject = jsonarray.getJSONObject(0);
                        final String name = jsonobject.getString("name");
                        final String imageurl = jsonobject.getString("imageurl");
                        description = jsonobject.getString("description");

                        handler.post(new Runnable() {
                            public void run() {

                                Context context = getApplicationContext();
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, name, duration);
                                toast.show();
                                TextView Name = findViewById(R.id.Monument1_name);
                                Name.setText(name);
                                ImageView IMAGE = findViewById(R.id.Monument1_image);
                                Picasso.with(context)
                                        .load(imageurl)
                                        .resize(500, 500)
                                        .into(IMAGE);


                            }
                        });
                    } catch (final JSONException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                TextView Monument1 = findViewById(R.id.Monument1_name);
                                Monument1.setText(e.getMessage());
                                //Toast.makeText(getApplicationContext(), "Exception: JSON Exception: "+ e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    /*

                    final ArrayList<Monument> lm = new ArrayList<>();
                    try {
                        JSONObject obj = new JSONObject(result);
                        JSONArray arr = obj.getJSONArray(result);
                        for (int i = 0; i < arr.length(); i++) {
                            String name = arr.getJSONObject(i).getString("name");
                            String lat = arr.getJSONObject(i).getString("lat");
                            String lon = arr.getJSONObject(i).getString("lon");
                            String cat = arr.getJSONObject(i).getString("cat");
                            String param = arr.getJSONObject(i).getString("param");
                            String description = arr.getJSONObject(i).getString("description");
                            String imageURL = arr.getJSONObject(i).getString("imageURL");
                            Monument m = new Monument(name,Double.parseDouble(lat),Double.parseDouble(lon),cat,param,description,imageURL);
                            lm.add(m);
                        }
                    }
                    catch(JSONException e){
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Exception: JSON Exception", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }


                    handler.post(new Runnable(){
                        public void run() {

                             Context context = getApplicationContext();
                            CharSequence text;
                            int duration = Toast.LENGTH_SHORT;
                            text = lm.get(1).toString();
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                            TextView Monument1 = findViewById(R.id.Monument1_name);
                            Monument1.setText(lm.get(1).name);

/*
                            ImageButton monument1 = convertView.findViewById(R.id.Monument1);
                            Picasso.with(context).load(lm.get(1).imageurl).into(monument1);

                            ImageButton monument2 = convertView.findViewById(R.id.Monument2);
                            loadImageFromURL(lm[2].imageURL);
                            ImageButton monument3 = convertView.findViewById(R.id.Monument3);
                            loadImageFromURL(lm[3].imageURL);
                            ImageButton monument4 = convertView.findViewById(R.id.Monument4);
                            loadImageFromURL(lm[4].imageURL);

                        }
                    });
        */

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

    public void ShowMore(View view) {


        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, description, duration);
        toast.show();
/*
        Context context = getApplicationContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(context); //Read Update
        builder.setTitle("hi");
        builder.setMessage(description);

        }

*/
    }
}


