package dnd.com.dndaw;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.List;

/**
 * Created by jonathanolson on 8/11/15.
 */
public class MyApplication extends Application {
    String activeUrl;
    private List<Legion> legions;
    SharedPreferences prefs;
    DatabaseHandler db;

    @Override
    public void onCreate() {

        super.onCreate();
        db = new DatabaseHandler(this);
//        db.addLegion(new Legion("Wraith 120", "http://build.dndaw.com/link/ODAxOjgwNjowOjgwNTo4MDMsODAyOjgwNDoxOTA2LDgwMjowOjA%3D"));
//        db.addLegion(new Legion("Wraith 123", "http://build.dndaw.com/link/ODAxOjgwNjowOjgwNTo4MDMsODAyOjgwNDoxOTA2LDgwMjowOjA%3D"));
//        db.addLegion(new Legion("Wraith 124", "http://build.dndaw.com/link/ODAxOjgwNjowOjgwNTo4MDMsODAyOjgwNDoxOTA2LDgwMjowOjA%3D"));
        legions = db.getAllLegions();
        if (legions.size()!=0) {
            for (Legion cn : legions) {
                String log = "Id: " + cn.getTitle() + " ,Name: " + cn.getLink();
                //            Log.d("score", "in oncreate: "+ log );
            }
        }
        else
        {
            addLegion(new Legion("Wraith 120", "http://build.dndaw.com/link/ODAxOjgwNjowOjgwNTo4MDMsODAyOjgwNDoxOTA2LDgwMjowOjA%3D"));
        }
//        addLegion(new Legion("Wraith 120", "http://build.dndaw.com/link/ODAxOjgwNjowOjgwNTo4MDMsODAyOjgwNDoxOTA2LDgwMjowOjA%3D"));
            // Writing Contacts to log

//        legions = new ArrayList<Legion>();
//        prefs = getSharedPreferences("mysh", MODE_PRIVATE);
//        int numrecords = prefs.getInt("numrecords", 0);
//        if (numrecords != 0) {
//            for(int i = 0; i<numrecords; i++) {
//                String key = Integer.toString(i);
//                String name = prefs.getString(key, "No name defined");//"No name defined" is the default value.
//                String url = prefs.getString(name, "No url defined"); //0 is the default value.
//                addLegion(new Legion(name, url));
//            }
//        }
//        else
//        {
//            addLegion(new Legion("Wraith 120", "http://build.dndaw.com/link/ODAxOjgwNjowOjgwNTo4MDMsODAyOjgwNDoxOTA2LDgwMjowOjA%3D"));
//        }
        activeUrl = "http://build.dndaw.com/upgrades";
    }
    public String getActiveUrl()
    {
        return this.activeUrl;
    }
    public void setActiveUrl(String url)
    {
        this.activeUrl = url;
    }

    public int getLegionSize()
    {
        return this.legions.size();
    }
    public Legion getLegionAt(int position)
    {
        return this.legions.get(position);
    }
    public List<Legion> getLegions()
    {
        return this.legions;
    }
    public void addLegion(Legion currlegion)
    {
        legions.add(currlegion);

        db.addLegion(currlegion);
    }
    public void removeLegion(int position) {
        Legion le = legions.remove(position);
        db.deleteLegion(le);
    }
//    public void saveLegion()
//    {
//
//        Log.d("score", "in SAVELEGIONS " );
//        DatabaseHandler db = new DatabaseHandler(this);
//
////        SharedPreferences.Editor editor = prefs.edit();
//        int numrecords = legions.size();
//        if (numrecords != 0) {
//            for(int i = 0; i<numrecords; i++) {
//                db.addLegion(new Legion(legions.get(i).getTitle(), legions.get(i).getLink()));
//
////                String key = Integer.toString(i);
////                editor.putString(key, legions.get(i).getTitle());//"No name defined" is the default value.
////                editor.putString(legions.get(i).getTitle(), legions.get(i).getLink()); //0 is the default value.
//            }
////            editor.commit(); // commit changes
//        }
//    }

}
