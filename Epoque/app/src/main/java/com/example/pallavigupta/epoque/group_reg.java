package com.example.pallavigupta.epoque;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;

/**
 * Created by Rishabh on 02-Mar-16.
 */
public class group_reg extends AppCompatActivity
{
    EditText et1,et2,et3,et4;
    Button b1,b2;
    ListView lv1;
    SharedPreferences sp;
    ArrayAdapter<String> members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_reg);
        sp=getSharedPreferences("Epoque2k16", Context.MODE_PRIVATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et1=(EditText)findViewById(R.id.edit_name);
        et2=(EditText)findViewById(R.id.edit_leader);
        et3=(EditText)findViewById(R.id.edit_size);
        et4=(EditText)findViewById(R.id.edit_members);
        b1=(Button)findViewById(R.id.addbtn);
        b2=(Button)findViewById(R.id.btn_reg);
        lv1=(ListView)findViewById(R.id.list_members);

        members=new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1);
        et2.setText(""+sp.getInt("id",0));

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mem=et4.getText().toString();
                members.add(mem);
                lv1.setAdapter(members);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder("");
                Constant co=new Constant();
                String url=co.url;
                String name=et1.getText().toString();
                int size = Integer.parseInt(et3.getText().toString());
                for(int i=0;i<size;i++)
                {
                    sb.append(members.getItem(i));
                }
                String team=new String(sb);
                new HitJSPService(group_reg.this, null, new TaskCompleted() {
                    @Override
                    public void onTaskCompleted(String result, int resultType) throws JSONException {

                    }
                },url+"/Other.php?func=1&id="+et2.getText().toString().trim()+"&name="+et1.getText().toString().trim()+"&event="+getIntent().getExtras().getInt("event")+"&size="+size+"&team="+team,1).execute();
            }
        });




    }
}
