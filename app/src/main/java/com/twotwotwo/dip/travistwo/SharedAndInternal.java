package com.twotwotwo.dip.travistwo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SharedAndInternal extends AppCompatActivity {
    private EditText edtShared;
    public static String fileName = "myData";
    private FileOutputStream outputStream;
    private FileInputStream inputStream;
    private String fileNameInternal = "internalData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpref);

        edtShared = findViewById(R.id.edtSaveShared);
    }

    public void saveToInternal(View view) {
        String dataInternal = edtShared.getText().toString();
       /* File dataFile = new File(fileNameInternal);

        try {
            outputStream = new FileOutputStream(dataFile);
            outputStream.write();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            outputStream = openFileOutput(fileNameInternal, MODE_PRIVATE);
            outputStream.write(dataInternal.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadFromInternal(View view) {
      new LoadData().execute(fileNameInternal);
    }

    private class LoadData extends AsyncTask<String, Integer, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            //Set up variable
            progressDialog = new ProgressDialog(SharedAndInternal.this);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String data = null;

            for(int i = 0; i <= 20 ; i++){
                publishProgress(5);
            }

            try {
                inputStream = openFileInput(fileNameInternal);
                byte[] dataArray = new byte[inputStream.available()];
                while (inputStream.read(dataArray) != -1){
                    data = new String(dataArray);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return data;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            progressDialog.incrementProgressBy(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.cancel();
            edtShared.setText(result);
        }
    }
}
