package com.example.browseurl;

import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	WebView myWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myWebView = (WebView) findViewById(R.id.mywebview);
		myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		myWebView.setWebViewClient(new MyWebViewClient());

		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setLoadsImagesAutomatically(true);
	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
//			if (url.equals("http://www.imrokraft.com")) {
//				// This is my web site, so do not override; let my WebView load the page
//				return false;
//			}
//			// Otherwise, the link is not for a page on my site, 
//			//so launch another Activity that handles URLs say Browsers
//
//			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//			startActivity(intent);
			return false;
		}
	}

	public void loadURL(View v) {
		EditText myEditText=(EditText) findViewById(R.id.editText1);
		String url=myEditText.getText().toString();
		if(! url.contains("http://")){
			url="http://"+url;
		}
		try {
			new URL(url);
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Invalid Web address entered", Toast.LENGTH_LONG).show();
			myEditText.setError("Invalid Web Address entered");
			return;
		}
		InputMethodManager imm = (InputMethodManager)getSystemService( Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
		myWebView.loadUrl(url);
	}
}