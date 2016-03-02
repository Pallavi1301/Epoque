package com.example.pallavigupta.epoque;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Invitations extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_invitation);
        sp=getSharedPreferences("Epoque2k16", Context.MODE_PRIVATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Constant co=new Constant();
        final String url=co.url;
        ListView list= (ListView) findViewById(R.id.list);
        final ArrayAdapter<String> invite =new ArrayAdapter<String>(Invitations.this,R.layout.row);
        final ArrayAdapter<String> event =new ArrayAdapter<String>(Invitations.this,R.layout.row);
        final ArrayAdapter<String> by =new ArrayAdapter<String>(Invitations.this,R.layout.row);
        new HitJSPService(Invitations.this, null, new TaskCompleted() {
            @Override
            public void onTaskCompleted(String result, int resultType) throws JSONException {
                JSONObject jsonObject=new JSONObject(result);
                JSONArray ja=jsonObject.getJSONArray("result");
                int len=ja.length();
                for(int i=0;i<len;i++) {
                    JSONObject jsonObject1 = ja.getJSONObject(i);
                    invite.add("Invited to " +jsonObject1.getString("id")+" by "+jsonObject1.getString("by"));
                    event.add(jsonObject1.getString("id1"));
                    by.add(jsonObject1.getString("by"));
                }
            }
        },url+"/Other.php?func=6&to="+sp.getString("id",null),1).execute();
        list.setAdapter(invite);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(Invitations.this)
                        .setMessage("Invitation")
                        .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                new HitJSPService(Invitations.this, null, new TaskCompleted() {
                                    @Override
                                    public void onTaskCompleted(String result, int resultType) throws JSONException {

                                    }
                                }, url + "/Other.php?func=4&to=" + sp.getString("id", null) + "&by=" + by.getItem(position)+"&event="+event.getItem(position),1).execute();
                            }
                        }).setNegativeButton("Reject", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new HitJSPService(Invitations.this, null, new TaskCompleted() {
                            @Override
                            public void onTaskCompleted(String result, int resultType) throws JSONException {

                            }
                        }, url + "/Other.php?func=4&to=" + sp.getString("id", null) + "&by=" + by.getItem(position)+"&event="+event.getItem(position), 1).execute();
                    }
                })
                        .show();
            }
        });

    }

}
