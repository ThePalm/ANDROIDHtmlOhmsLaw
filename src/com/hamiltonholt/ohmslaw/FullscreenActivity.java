package com.hamiltonholt.ohmslaw;

import com.hamiltonholt.ohmslaw.util.SystemUiHider;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.app.AlertDialog;
import android.webkit.WebView;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity 
{
    
    WebView webview;
	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise,
	 * will show the system UI visibility upon interaction.
	 */
	private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	private SystemUiHider mSystemUiHider;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		 setContentView(R.layout.activity_fullscreen);
        
		 webview = (WebView) findViewById(R.id.webView1);
	     webview.getSettings().setJavaScriptEnabled(true);
	     webview.getSettings().setUseWideViewPort(true);
	     webview.getSettings().setBuiltInZoomControls(true);
	     webview.setVerticalScrollBarEnabled(false);
	     webview.setHorizontalScrollBarEnabled(false);
	     Display display = getWindowManager().getDefaultDisplay();
	     double ratio = display.getWidth() / 9.7;
	     webview.setInitialScale((int) ratio);
	     webview.loadUrl("file:///android_asset/ohmslaw.htm");
	     
        final Context myApp = this;
        
        webview.setWebChromeClient(new WebChromeClient()
        {
@Override
public boolean onJsAlert(WebView view, String url,
          String message, final android.webkit.JsResult result)
{
new AlertDialog.Builder(myApp)
.setTitle(" ").setMessage(message)
.setPositiveButton(android.R.string.ok,
        new AlertDialog.OnClickListener()
        {
public void onClick(DialogInterface dialog, int which)
{
result.confirm();
}
})

.setCancelable(false).create().show();

return true;  
};  
});


final View controlsView = findViewById(R.id.fullscreen_content_controls);
final View contentView = findViewById(R.id.fullscreen_content);


}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) 
	{
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
		//delayedHide(100);
	}


}
