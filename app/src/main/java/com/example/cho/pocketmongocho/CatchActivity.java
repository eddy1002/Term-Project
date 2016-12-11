package com.example.cho.pocketmongocho;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CatchActivity extends AppCompatActivity {
    ListView listView;

    MainValues mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch);

        mv = MainValues.getInstance();

        listView = (ListView) this.findViewById(R.id.List_CatchMob);

        ArrayList<Integer> items = new ArrayList<>();
        items.add(0);
        items.add(1);
        items.add(2);
        items.add(3);

        CustomAdapter adapter = new CustomAdapter(this, 0, items);
        listView.setAdapter(adapter);
    }

    private class CustomAdapter extends ArrayAdapter<Integer>{
        private ArrayList<Integer> items;

        public CustomAdapter(Context context, int textViewResourceId, ArrayList<Integer> objects){
            super(context, textViewResourceId, objects);
            this.items = objects;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            View v = convertView;
            if (v == null){
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.activity_list__catch, null);
            }

            ImageView imageView = (ImageView) v.findViewById(R.id.image_Pocket);
            TextView textView = (TextView) v.findViewById(R.id.text_CatchLocation);

            if(mv.getCatchMob(items.get(position)) == 1){
                switch (items.get(position)){
                    case 0:
                        imageView.setImageResource(R.drawable.mob_0);
                        break;
                    case 1:
                        imageView.setImageResource(R.drawable.mob_1);
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.mob_2);
                        break;
                    case 3:
                        imageView.setImageResource(R.drawable.mob_3);
                        break;
                }
                textView.setText("(" + String.format("%.2f", mv.getCacthLocationX(items.get(position))) + ", " + String.format("%.2f", mv.getCacthLocationY(items.get(position))) + ")");
            }

            return v;
        }
    }
}


