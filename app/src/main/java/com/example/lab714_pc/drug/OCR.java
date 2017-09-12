package com.example.lab714_pc.drug;


import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.googlecode.tesseract.android.TessBaseAPI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.lab714_pc.drug.R.id.OCRTextView;

public class OCR extends AppCompatActivity implements View.OnClickListener {

    static Bitmap image;
    public int count = 0;
    private TessBaseAPI mTess;
    String datapath = "";
    private  Button button,btOCR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);

        //init image


        //initialize Tesseract API
        String language = "eng+chi_tra";
        datapath = getFilesDir() + "/tesseract/";
        mTess = new TessBaseAPI();

        checkFile(new File(datapath + "tessdata/"));

        mTess.init(datapath, language);
        button = (Button) findViewById(R.id.b01);
        button.setOnClickListener(this);
        button.setText("選擇圖片");
        btOCR = (Button) findViewById(R.id.OCRbutton);
        btOCR.setText("RUN OCR");
        btOCR.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b01:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
                break;
            case R.id.OCRbutton:
                new ProcessImage().execute("");



        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {
                this.image = BitmapFactory.decodeStream(cr.openInputStream(uri));
                ImageView imageView = (ImageView) findViewById(R.id.iv01);
                /* 將Bitmap設定到ImageView */
                imageView.setImageBitmap(this.image);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    private void checkFile(File dir) {
        copyFiles Copy =new copyFiles();
        if (!dir.exists()&& dir.mkdirs()) {
            Copy.execute("tessdata/eng.traineddata","tessdata/chi_tra.traineddata");
        }
        if (dir.exists()) {
            Copy.execute("tessdata/eng.traineddata","tessdata/chi_tra.traineddata");

        }
    }
    public class copyFiles extends  AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            //執行前 設定可以在這邊設定
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            //執行中 在背景做事情

            try {
                for(int i =0;i<params.length;i++) {


                    String filepath = datapath + params[i];
                    if (!new File(filepath).exists()) {
                        AssetManager assetManager = getAssets();

                        InputStream instream = assetManager.open(params[i]);
                        OutputStream outstream = new FileOutputStream(filepath);

                        byte[] buffer = new byte[1024];
                        int read;
                        while ((read = instream.read(buffer)) != -1) {
                            outstream.write(buffer, 0, read);
                        }


                        outstream.flush();
                        outstream.close();
                        instream.close();

                        File file = new File(filepath);
                        if (!file.exists()) {
                            throw new FileNotFoundException();
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return "Executed";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //執行中 可以在這邊告知使用者進度

        }

        @Override
        protected void onPostExecute(String bitmap) {
            //執行後 完成背景任務

        }

    }

    public class ProcessImage extends AsyncTask<String,String, String> {
        String OCRresult = null;

        @Override
        protected String doInBackground(String... args) {


            try {

                mTess.setImage(OCR.image);
                OCRresult = mTess.getUTF8Text();
                mTess.end();
                mTess.clear();

            } catch (RuntimeException e) {
                Log.e("OcrRecognizeAsyncTask",
                        "Caught RuntimeException in request to Tesseract. Setting state to CONTINUOUS_STOPPED.",
                        e);

                try {

                } catch (NullPointerException e1) {
                    // Continue
                }
                return null;
            }

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            TextView txt = (TextView) findViewById(OCRTextView);
            txt.setText("請按返回"); // txt.setText(result);
            if(OCRresult!=null) {
                txt.setText(OCRresult);
            }
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {}



    }
}
