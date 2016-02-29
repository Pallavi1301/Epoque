package com.example.pallavigupta.epoque;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventList extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Constant co=new Constant();
        String url=co.url;
        sp=getSharedPreferences("Epoque2k16", Context.MODE_PRIVATE);
        int id=getIntent().getExtras().getInt("id");
        new HitJSPService(this, null, new TaskCompleted() {
            @Override
            public void onTaskCompleted(String result, int resultType) throws JSONException {
                try {
                    List<Event_list_Element> aa=new ArrayList<Event_list_Element>();
                    ListView lv= (ListView) findViewById(R.id.List);
                    JSONObject jo = new JSONObject(result);
                    JSONArray ja = jo.getJSONArray("result");
                    int len=ja.length();
                    for(int i=0;i<len;i++) {
                        JSONObject jo1 = ja.getJSONObject(i);
                        Event_list_Element element;
                        element=new Event_list_Element(jo1.getString("Start_time"),jo1.getString("Event_name"),jo1.getString("Date"),jo1.getString("Venue"),jo1.getInt("Event_Id"),jo1.getInt("Max"),jo1.getInt("Min"));
                        aa.add(element);
                    }
                    Event_adapter adap=new Event_adapter(getApplicationContext(),aa);
                    Animation anim= AnimationUtils.loadAnimation(EventList.this, R.anim.list_animation);
                    lv.setAdapter(adap);
                    lv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    lv.setAnimation(anim);
                }catch (Exception e){}
            }
        },url+"/Other.php?func=3&id=" + id + "&dept=" + sp.getString("dept",null),1).execute();
    }
}
