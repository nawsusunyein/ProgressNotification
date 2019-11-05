package com.example.progressnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    NotificationManager mNotify;
    NotificationCompat.Builder mBuilder;
    int notiId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDownload = (Button) findViewById(R.id.btnDownloadFile);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotify = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mBuilder = new NotificationCompat.Builder(MainActivity.this,"1");
                mBuilder.setSmallIcon(R.drawable.cloud_download)
                        .setContentTitle("File Download")
                        .setContentText("Download is in progress...");

                new Thread(
                    new Runnable(){

                        @Override
                        public void run(){
                            int incr;

                            for(incr = 0 ; incr <= 100 ; incr += 10){
                                mBuilder.setProgress(100,incr,false);
                                mNotify.notify(notiId,mBuilder.build());

                                try{
                                    Thread.sleep(1*1000);
                                }catch (InterruptedException e){
                                    Log.d("TAG","Sleep Failure");
                                }
                            }
                            mBuilder.setContentText("Download is completed");
                            mBuilder.setProgress(0,0,false);
                            mNotify.notify(notiId,mBuilder.build());
                        }
                    }
                ).start();

            }
        });
    }
}
