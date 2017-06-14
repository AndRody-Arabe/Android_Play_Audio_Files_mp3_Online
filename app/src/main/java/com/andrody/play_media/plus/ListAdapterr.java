package com.andrody.play_media.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andrody.play_media.PlayerActivity;
import com.andrody.play_media.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Abboudi_Aliwi on 14.06.2017.
 * Website : http://andrody.com/
 * our channel on YouTube : https://www.youtube.com/c/Andrody2015
 * our page on Facebook : https://www.facebook.com/andrody2015/
 * our group on Facebook : https://www.facebook.com/groups/Programming.Android.apps/
 * our group on Whatsapp : https://chat.whatsapp.com/56JaImwTTMnCbQL6raHh7A
 * our group on Telegram : https://t.me/joinchat/AAAAAAm387zgezDhwkbuOA
 * Preview the app from Google play : https://play.google.com/store/apps/details?id=nasser_alqtami.andrody.com
 */

public class ListAdapterr extends BaseAdapter {

    LayoutInflater inflater;
    private List<Data> Datalist = null;
    private ArrayList<Data> arraylist;
    Context context;

    public ListAdapterr(Activity context, List<Data> openSite) {
        this.context=context;
        this.Datalist = openSite;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(openSite);

    }

    @Override
    public int getCount() {
        return Datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return Datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        View Item =inflater.inflate(R.layout.listview_item, null,true);

        LinearLayout LL = (LinearLayout) Item.findViewById(R.id.Ll_);

        LL.setBackgroundColor(Color.parseColor("#099806"));

        TextView txtTitle = (TextView) Item.findViewById(R.id.titleid);

        txtTitle.setTextColor(Color.parseColor("#099806"));

        txtTitle.setText(Datalist.get(position).getSubject());

        Item.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(context,PlayerActivity.class);
                i.putExtra("name",Datalist.get(position).getSubject());
                i.putExtra("url",Datalist.get(position).getLink());
                context.startActivity(i);
            }
        });

        if (position % 2 == 0){
            LL.setBackgroundColor(Color.parseColor("#038001"));
            txtTitle.setTextColor(Color.parseColor("#038001"));
        }

        return Item;



    };
}