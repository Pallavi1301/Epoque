package com.example.pallavigupta.epoque;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rishabh on 29-Feb-16.
 */
public class Event_adapter extends BaseAdapter  {    final Context context;
    final List<Event_list_Element> listElement;

    public Event_adapter(Context context,List<Event_list_Element> listElement)
    {
        this.context=context;
        this.listElement=listElement;
    }

    @Override
    public int getCount() {
        return listElement.size();
    }

    @Override
    public Object getItem(int i) {
        return listElement.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event_list_Element entry = listElement.get(position);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.event_row_list, null);
        TextView tvName = (TextView) convertView.findViewById(R.id.txt_name);
        tvName.setText(entry.getName());
        TextView tvDate = (TextView) convertView.findViewById(R.id.txt_date);
        tvDate.setText(entry.getDate());
        TextView tvTime = (TextView) convertView.findViewById(R.id.txt_time);
        tvTime.setText(entry.getTime());
        TextView tvVenue = (TextView) convertView.findViewById(R.id.txt_venue);
        tvVenue.setText(entry.getVenue());

        return convertView;
    }

}
