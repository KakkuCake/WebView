package com.example.v10tehtvt;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imageview;
    private WebView webview;
    private EditText editText;
    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = (WebView) findViewById(R.id.webview);
        editText =  findViewById(R.id.editText);

      //  webview.loadUrl("https://www.google.com");

        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_back:
                onBackPressed();
                break;

            case R.id.menu_forward:
                onForwardPressed();
                break;

            case R.id.menu_refresh:
                webview.reload();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void onForwardPressed() {
        if (webview.canGoForward()) {
            webview.goForward();
        } else {
            Toast.makeText(this, "Ei pääse eteenpäin", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void executeJavaScript(View v){

        if (counter% 2 == 0) {
            webview.evaluateJavascript("javascript:initialize()", null);
        } else {
            webview.evaluateJavascript("javascript:shoutOut()", null);
        }
        counter++;
    }

    public void openUrl(View w){
        String address = editText.getText().toString();
        if (address.equals("index.html")) {
            webview.loadUrl("file:///android_asset/index.html");
        } else {
            webview.loadUrl("http://" + address);
        }
    }
}
