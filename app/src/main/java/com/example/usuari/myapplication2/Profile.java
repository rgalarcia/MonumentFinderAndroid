package com.example.usuari.myapplication2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
    static JSONArray jsonarray;
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
                         jsonarray = new JSONArray(result);

                        handler.post(new Runnable() {
                            public void run() {

                                for (int i = 0; i < 22; i++) {

                                    TextView Name;
                                    ImageView IMAGE;

                                    Name = findViewById(R.id.Monument_name0);
                                    IMAGE = findViewById(R.id.Monument_image0);

                                    if (i == 1) {
                                        Name = findViewById(R.id.Monument_name1);
                                        IMAGE = findViewById(R.id.Monument_image1);
                                    } else if (i == 2) {
                                        Name = findViewById(R.id.Monument_name2);
                                        IMAGE = findViewById(R.id.Monument_image2);
                                    } else if (i == 3) {
                                        Name = findViewById(R.id.Monument_name3);
                                        IMAGE = findViewById(R.id.Monument_image3);
                                    } else if (i == 4) {
                                        Name = findViewById(R.id.Monument_name4);
                                        IMAGE = findViewById(R.id.Monument_image4);
                                    } else if (i == 5) {
                                        Name = findViewById(R.id.Monument_name5);
                                        IMAGE = findViewById(R.id.Monument_image5);
                                    } else if (i == 6) {
                                        Name = findViewById(R.id.Monument_name6);
                                        IMAGE = findViewById(R.id.Monument_image6);
                                    } else if (i == 7) {
                                        Name = findViewById(R.id.Monument_name7);
                                        IMAGE = findViewById(R.id.Monument_image7);
                                    } else if (i == 8) {
                                        Name = findViewById(R.id.Monument_name8);
                                        IMAGE = findViewById(R.id.Monument_image8);
                                    } else if (i == 9) {
                                        Name = findViewById(R.id.Monument_name9);
                                        IMAGE = findViewById(R.id.Monument_image9);
                                    } else if (i == 10) {
                                        Name = findViewById(R.id.Monument_name10);
                                        IMAGE = findViewById(R.id.Monument_image10);
                                    } else if (i == 11) {
                                        Name = findViewById(R.id.Monument_name11);
                                        IMAGE = findViewById(R.id.Monument_image11);
                                    } else if (i == 12) {
                                        Name = findViewById(R.id.Monument_name12);
                                        IMAGE = findViewById(R.id.Monument_image12);
                                    } else if (i == 13) {
                                        Name = findViewById(R.id.Monument_name13);
                                        IMAGE = findViewById(R.id.Monument_image13);
                                    } else if (i == 14) {
                                        Name = findViewById(R.id.Monument_name14);
                                        IMAGE = findViewById(R.id.Monument_image14);
                                    } else if (i == 15) {
                                        Name = findViewById(R.id.Monument_name15);
                                        IMAGE = findViewById(R.id.Monument_image15);
                                    } else if (i == 16) {
                                        Name = findViewById(R.id.Monument_name16);
                                        IMAGE = findViewById(R.id.Monument_image16);;
                                    } else if (i == 17) {
                                        Name = findViewById(R.id.Monument_name17);
                                        IMAGE = findViewById(R.id.Monument_image17);
                                    } else if (i == 18) {
                                        Name = findViewById(R.id.Monument_name18);
                                        IMAGE = findViewById(R.id.Monument_image18);
                                    } else if (i == 19) {
                                        Name = findViewById(R.id.Monument_name19);
                                        IMAGE = findViewById(R.id.Monument_image19);
                                    } else if (i == 20) {
                                        Name = findViewById(R.id.Monument_name20);
                                        IMAGE = findViewById(R.id.Monument_image20);
                                    } else if (i == 21) {
                                        Name = findViewById(R.id.Monument_name21);
                                        IMAGE = findViewById(R.id.Monument_image21);
                                    }


                                    if(i<jsonarray.length()) {
                                        try {
                                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                                            final String name = jsonobject.getString("name");
                                            String imageURL = jsonobject.getString("imageurl");

                                            Context context = getApplicationContext();

                                            Name.setText(name);
                                            Picasso.with(context)
                                                    .load(imageURL)
                                                    .resize(500, 500)
                                                    .into(IMAGE);

                                        } catch (final JSONException e) {
                                            e.printStackTrace();

                                        }
                                    } else{
                                        Name.setVisibility(View.GONE);
                                        IMAGE.setVisibility(View.GONE);
                                    }
                                }
                            }
                        });
                    } catch (final JSONException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Exception: JSON Exception: "+ e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }


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

    public void OpenMaps(View view) {
        int i = 0;
        switch (view.getId()) {
            case R.id.Monument_image0:
                i = 0;
                break;
            case R.id.Monument_image1:
                i = 1;
                break;
            case R.id.Monument_image2:
                i = 2;
                break;
            case R.id.Monument_image3:
                i = 3;
                break;
            case R.id.Monument_image4:
                i = 4;
                break;
            case R.id.Monument_image5:
                i = 5;
                break;
            case R.id.Monument_image6:
                i = 6;
                break;
            case R.id.Monument_image7:
                i = 7;
                break;
            case R.id.Monument_image8:
                i = 8;
                break;
            case R.id.Monument_image9:
                i = 9;
                break;
            case R.id.Monument_image10:
                i = 10;
                break;
            case R.id.Monument_image11:
                i = 11;
                break;
            case R.id.Monument_image12:
                i = 12;
                break;
            case R.id.Monument_image13:
                i = 13;
                break;
            case R.id.Monument_image14:
                i = 14;
                break;
            case R.id.Monument_image15:
                i = 15;
                break;
            case R.id.Monument_image16:
                i = 16;
                break;
            case R.id.Monument_image17:
                i = 17;
                break;
            case R.id.Monument_image18:
                i = 18;
                break;
            case R.id.Monument_image19:
                i = 19;
                break;
            case R.id.Monument_image20:
                i = 20;
                break;
            case R.id.Monument_image21:
                i = 21;
                break;
        }

        try {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            String name = jsonobject.getString("name");
            String lat = jsonobject.getString("lat");
            String lon = jsonobject.getString("lon");
            String uri = "geo:" + lat + "," + lon +"?q="+lat+","+lon+"("+name+")";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        }
        catch (final JSONException e) {
            e.printStackTrace();
        }
    }

    public void ShowMore(View view) {

        int i = 0;
        switch (view.getId()) {
            case R.id.Monument_name0:
                i = 0;
                break;
            case R.id.Monument_name1:
                i = 1;
                break;
            case R.id.Monument_name2:
                i = 2;
                break;
            case R.id.Monument_name3:
                i = 3;
                break;
            case R.id.Monument_name4:
                i = 4;
                break;
            case R.id.Monument_name5:
                i = 5;
                break;
            case R.id.Monument_name6:
                i = 6;
                break;
            case R.id.Monument_name7:
                i = 7;
                break;
            case R.id.Monument_name8:
                i = 8;
                break;
            case R.id.Monument_name9:
                i = 9;
                break;
            case R.id.Monument_name10:
                i = 10;
                break;
            case R.id.Monument_name11:
                i = 11;
                break;
            case R.id.Monument_name12:
                i = 12;
                break;
            case R.id.Monument_name13:
                i = 13;
                break;
            case R.id.Monument_name14:
                i = 14;
                break;
            case R.id.Monument_name15:
                i = 15;
                break;
            case R.id.Monument_name16:
                i = 16;
                break;
            case R.id.Monument_name17:
                i = 17;
                break;
            case R.id.Monument_name18:
                i = 18;
                break;
            case R.id.Monument_name19:
                i = 19;
                break;
            case R.id.Monument_name20:
                i = 20;
                break;
            case R.id.Monument_name21:
                i = 21;
                break;
        }

        try {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            description = jsonobject.getString("description");
        }
        catch (final JSONException e) {
            e.printStackTrace();
        }

        Context context = getApplicationContext();

        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, description, duration);
        toast.show();

    }
}