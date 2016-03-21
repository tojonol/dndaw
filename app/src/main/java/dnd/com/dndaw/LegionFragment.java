package dnd.com.dndaw;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LegionFragment extends Fragment {
//    private OnLegionSelectedListener listener;
    private ListView mListView;
    public interface OnLegionSelectedListener {
        public void onLegionSelected(int index);
    }
    public LegionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Log.d("score", "Bearror: " + "OnCreate Call");
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_legions, container, false);
        //retrieve frament view
        mListView = (ListView) fragmentView.findViewById(R.id.MyListView);

        mListView.setAdapter(new MyAdapterLegion());

        return fragmentView;
    }



//    public void addLegion()
//    {
//        legions.add(new Legion("Dummy #X", "www.error4011.com"));
//        Log.d("score", "Error: " + "SIZE " + legions.size());
//
//    }

    private class MyAdapterLegion extends BaseAdapter{

        @Override
        public int getCount() {
            return ((MyApplication) getActivity().getApplication()).getLegionSize();
        }

        @Override
        public Object getItem(int position) {
            return  ((MyApplication) getActivity().getApplication()).getLegionAt(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

//            Log.d("score", "Bearror: " + "GetView Call " + position);
            List<Legion> legions =(List<Legion>) ((MyApplication) getActivity().getApplication()).getLegions();
            final int relativePos = position;
            View rowView = getActivity().getLayoutInflater().inflate(R.layout.row, null);

            Legion rowLegion = ((MyApplication) getActivity().getApplication()).getLegionAt(position);

            TextView tvRow = (TextView) rowView.findViewById(R.id.textView);
            tvRow.setText(rowLegion.getTitle());

            Button dButton = (Button) rowView.findViewById(R.id.delete_button);
            dButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click
                    Log.d("score", "Error: " + "404 " + ((MyApplication) getActivity().getApplication()).getLegionAt(relativePos).getLink());

                    ((MyApplication) getActivity().getApplication()).removeLegion(relativePos);
                    ((MainActivity)getActivity()).deleteButton();
                }
            });
            Button lButton = (Button) rowView.findViewById(R.id.load_button);
            lButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click
//                    Log.d("score", "Error: " + "404 " + ((MyApplication) getActivity().getApplication()).getLegionAt(relativePos).getLink());
//                    Log.d("score", "B4 " + ((MyApplication) getActivity().getApplication()).getActiveUrl());

                    MyApplication thisApp =(MyApplication) getActivity().getApplication();
                    thisApp.setActiveUrl(((MyApplication) getActivity().getApplication()).getLegionAt(relativePos).getLink());
                    ((MainActivity)getActivity()).loadButton();

//                    Log.d("score", "AFTER " + ((MyApplication) getActivity().getApplication()).getActiveUrl());
                }
            });
            return rowView;
        }
    }

}
