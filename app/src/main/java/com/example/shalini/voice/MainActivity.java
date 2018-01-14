package com.example.shalini.voice;

import android.app.SearchManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.b11);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displaySpeechRecognizer();

            }
        });

    }

    /*public void onButtonClick(View v)
    {
        if(v.getId()==R.id.b11)
        {
            displaySpeechRecognizer();
        }
    }*/
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "say it");
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(MainActivity.this, "no way", Toast.LENGTH_LONG).show();
        }

    }

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      if(requestCode==SPEECH_REQUEST_CODE&& resultCode == RESULT_OK) {
          List<String> results= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
          String spokenText=results.get(0);
          if(spokenText=="camera")
          {
              Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_LONG).show();
          }


      }
        super.onActivityResult(requestCode, resultCode, data);
    switch(requestCode)
    {
        case 100: if(resultCode== RESULT_OK && data != null)
        {
            ArrayList<String>result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if(result.toString()== "camera")
            {
                Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_LONG).show();
            }
        }
        break;
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);

            //  Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_LONG).show();
            if (spokenText.equals("camera")) {
                //Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_LONG).show();

                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  //intent is also used to take start any service, it is also used start any hardware services like camera,audio
                startActivityForResult(i, 0);//
            } else if (spokenText.equals("Instagram")) {
              /*  Uri uri = Uri.parse("https://www.instagram.com"); // missing 'http://' will cause crashed
                Intent i40 = new Intent(Intent.ACTION_VIEW, uri);
                i40.setPackage("com.instagram.android");
                try {
                    startActivity(i40);
                }
                catch(ActivityNotFoundException e)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.instagram.com")));

                }
            }*/

                //  Intent likeIng=new Intent(Intent.ACTION_VIEW);
                //  likeIng.setPackage("com.instagram.android");
                Intent webIntent=new Intent(Intent.ACTION_VIEW,Uri.parse("http//www.instagram.com"));
                try {
                    startActivity(getPackageManager().getLaunchIntentForPackage("com.instagram.android"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this,"you dont have this app",Toast.LENGTH_LONG).show();
                }
            }
            else if(spokenText.equals("Facebook"))
            {
                Uri uri = Uri.parse("https://www.facebook.com"); // missing 'http://' will cause crashed
                Intent i30 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i30);
            }
            else if (spokenText.equals("YouTube"))
            {
                //Intent appIntent= new Intent(Intent.ACTION_VIEW,Uri.parse("vnd.youtube"));
                Intent webIntent=new Intent(Intent.ACTION_VIEW,Uri.parse("http//www.youtube.com"));
                try{
                    startActivity(getPackageManager().getLaunchIntentForPackage("com.google.android.youtube"));
                }
                catch(ActivityNotFoundException e)
                {
                    startActivity(webIntent);
                }
            }
            else if(spokenText.equals("Google Map"))
            {
                startActivity(getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps"));
            }
            else if(spokenText.equals("gallery"))
            {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("content://media/internal/images/media"));
                startActivity(intent);

            }
            else if(spokenText.equals("Wi-Fi"))
            {
                WifiManager wifi=(WifiManager) getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(false);

            }
            else if(spokenText.equals("Bluetooth"))
            {
                BluetoothAdapter mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
                if(mBluetoothAdapter.isEnabled())
                {
                  mBluetoothAdapter.disable();
                }
                else
                {
                    mBluetoothAdapter.enable();
                }
            }
            else if(spokenText.equals("WhatsApp"))
            {
                Intent i=getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                startActivity(i);
            }
            else if(spokenText.equals("settings"))
            {
                startActivityForResult(new Intent(Settings.ACTION_SETTINGS),0);
            }
            else if(spokenText.equals("Play Store"))
            {
                /*final Stirng appPackageName=getPackageName();
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=" + appPackageName)));
                }
                catch(android.content.ActivityNotFoundException anfe)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }*/
                Uri uri = Uri.parse("https://play.google.com/store/apps"); // missing 'http://' will cause crashed
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);



            }
            else if(spokenText.equals("Snapchat")) {
              //  try {
              //      startActivity(getPackageManager().getLaunchIntentForPackage("com.snapchat.android"));
               // } catch (ActivityNotFoundException e) {
                 //   Toast.makeText(MainActivity.this, "you don,t have this app", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("*/*");
                intent.setPackage("com.snapchat.android");
                startActivity(Intent.createChooser(intent, "Open Snapchat"));
            }
            }
            else
            {
           

           Intent intent=new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,"spokenText");
                startActivity(intent);
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
    }

