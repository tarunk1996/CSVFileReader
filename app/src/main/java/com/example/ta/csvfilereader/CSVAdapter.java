package com.example.ta.csvfilereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CSVAdapter extends ArrayAdapter<State>{
    Context ctx;
    static class ItemViewHolder {
        TextView state;
        TextView capital;
    }
    //We must accept the textViewResourceId parameter, but it will be unused
    //for the purposes of this example.
    public CSVAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);

        this.ctx = context;

        loadArrayFromFile();
    }

    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){

        View row = convertView;
        CSVAdapter.ItemViewHolder viewHolder;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_layout, parent, false);
            viewHolder = new CSVAdapter.ItemViewHolder();
            viewHolder.state = (TextView) row.findViewById(R.id.name);
            viewHolder.capital = (TextView) row.findViewById(R.id.score);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CSVAdapter.ItemViewHolder)row.getTag();
        }

        viewHolder.state.setText(getItem(pos).getName());
        viewHolder.capital.setText(getItem(pos).getCapital());

        return row;
    }


    private void loadArrayFromFile(){
        try {
            InputStream is = ctx.getResources().openRawResource(R.raw.states);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {

                String[] RowData = line.split(",");

                State cur = new State();
                cur.setName(RowData[0]);
                cur.setCapital(RowData[1]);


                this.add(cur);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
