package dnd.com.dndaw;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment {

    WebView wv;
    View fragmentView;
    int numberBack = 0;
    public WebViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_webview, container, false);
        wv = (WebView) fragmentView.findViewById(R.id.webkit);
        wv.setWebViewClient(new MyWebView());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setDomStorageEnabled(true);
        MyApplication thisApp =(MyApplication) getActivity().getApplication();
        String activeUrl = thisApp.getActiveUrl();
        wv.loadUrl(activeUrl);
        return fragmentView;

    }
    public void loadUrl()
    {
        MyApplication thisApp =(MyApplication) getActivity().getApplication();

        String activeUrl = thisApp.getActiveUrl();
        wv.loadUrl(activeUrl);
    }

}
