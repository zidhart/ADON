package com.zid.adon;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    protected File imageFile;
    EditText userEMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void mailme(View v) {
        Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"one.jpg");
        cam.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        cam.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
        startActivityForResult(cam, 0);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0){
            switch (resultCode){
                case Activity.RESULT_OK:
                    //                  if (imageFile.exists())
                {
                    userEMail = (EditText) findViewById(R.id.userEMail);
                    Intent intent = null, chooser=null;
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.setData(Uri.parse("mailto:"));
                    String[] to = {"siddharthss93@gmail.com",userEMail.getText().toString()};
                    intent.putExtra(Intent.EXTRA_EMAIL, to);
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Hello you have a visitor");
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imageFile));
                    intent.setType("message/rfc822");
                    chooser = Intent.createChooser(intent,"Send Email");
                    startActivity(intent);
                }
                case Activity.RESULT_CANCELED:
                    break;
                default:
                    break;
            }
        }
    }

}
