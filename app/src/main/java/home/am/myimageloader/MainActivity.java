package home.am.myimageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private Button downloadBtn;
    private ImageView mainView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Implementing view to find by ID
        initViews();
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadImage().execute(AppConstants.IMAGE_URL);
            }
        });
    }

    private void initViews() {
        downloadBtn = findViewById(R.id.btnDownlaodID);
        mainView = findViewById(R.id.imageViewMainID);
        progressBar = findViewById(R.id.ppppp1);
    }


    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... URL) {
            String imageUrl = URL[0];
            Bitmap bitmap = null;

            try {
                // Download Image From URL class
                InputStream inputStream = new java.net.URL(imageUrl).openStream();
                // Decode input stream to bitmap
                bitmap = BitmapFactory.decodeStream(inputStream);
                 Thread.sleep(1500);
            } catch (Exception e) {
                Log.i(TAG, "doInBackground: " + e.getMessage());
            }
            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressBar.setVisibility(View.GONE);
            if (bitmap != null) {
                mainView.setImageBitmap(bitmap);
            }
        }
    }


}
