package dnd.com.dndaw;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.List;

public class MainActivity extends AppCompatActivity  {
    View popupView;
    PopupWindow pw;
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.pager);
        vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(1);
    }

    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new LegionFragment();

            } else {
                return new WebViewFragment();

            }
        }

//        NUMBER OF PAGES INCLUDED
        @Override
        public int getCount() {
            return 2;
        }

        //        sets the name of the tabs
        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Legions";
            } else {
                return "Build";
            }
        }
    }

    public void addLegion(View v) {
        EditText legionNameet = (EditText) popupView.findViewById(R.id.lname);
        EditText legionUrlet = (EditText) popupView.findViewById(R.id.lurl);
        String legionName = legionNameet.getText().toString();
        String legionUrl = legionUrlet.getText().toString();
        if (ensureUnique(legionName,legionUrl))
            ((MyApplication) getApplication()).addLegion(new Legion(legionName, legionUrl));
        ((MyApplication) getApplication()).setActiveUrl(legionUrl);
        loadButton();
        pw.dismiss();
    }


    public void showPopup(View anchorView) {

        popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        pw = new PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        // If the PopupWindow should be focusable
        pw.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
        pw.setBackgroundDrawable(new ColorDrawable());

        int location[] = new int[2];

        // Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);

        pw.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
    }


    public void loadButton()
    {
        vp.setCurrentItem(1);
        FragmentManager fm = getSupportFragmentManager();

        //if you added fragment via layout xml
        WebViewFragment fragmentObj=(WebViewFragment) vp.getAdapter().instantiateItem(vp, 1);
        fragmentObj.loadUrl();

//        Log.d("score", "inLB " + ((MyApplication) getApplication()).getActiveUrl());
    }

    public void deleteButton()
    {
        vp.setCurrentItem(1);
    }

    public boolean ensureUnique(String a, String b)
    {
        List<Legion> legions = ((MyApplication) getApplication()).getLegions();
        for (Legion legion: legions)
        {
            String c = legion.getTitle();
            String d = legion.getLink();
            if(c.equals(a)||c.equals(b)||d.equals(a)||d.equals(b)) {
                return false;
            }
        }
        return true;
    }
}
