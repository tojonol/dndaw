package dnd.com.dndaw;

import android.net.Uri;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        if (Uri.parse(url).getHost().equals("dndaw.com")) {
            // This is my web site, so do not override; let my WebView load the page
            return false;
        }
        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs

        return true;
    }
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event)
    {
        return super.shouldOverrideKeyEvent(view,event);
    }



}