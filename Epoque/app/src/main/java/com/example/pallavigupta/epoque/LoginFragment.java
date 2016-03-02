package com.example.pallavigupta.epoque;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by USER on 02/24/2016.
 */
public class LoginFragment extends AppCompatActivity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sp=getSharedPreferences("Epoque2k16", Context.MODE_PRIVATE);
        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Authenticate();
            }
        });
    }
    public void Authenticate()
    {
        Constant co=new Constant();
        String url=co.url;
        final EditText ed1,ed2;
        ed1= (EditText) findViewById(R.id.editText);
        ed2= (EditText) findViewById(R.id.editText1);
        try {
            new HitJSPService(this, null, new TaskCompleted() {

                @Override
                public void onTaskCompleted(String result, int resultType) {
                    try {
                        JSONObject jo = new JSONObject(result);
                        JSONArray ja = jo.getJSONArray("result");
                        JSONObject jo1 = ja.getJSONObject(0);
                        sp.edit().putString("dept", jo1.getString("id")).commit();
                        sp.edit().putString("id", ed1.getText().toString().trim()).commit();
                        sp.edit().putBoolean("isTrue", true).commit();
                        Toast.makeText(LoginFragment.this, "Login with Id "+Integer.parseInt(jo1.getString("id")), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                        // TODO: handle exception
                        Toast.makeText(LoginFragment.this, "Email and Code do not match", Toast.LENGTH_SHORT).show();
                    }

                }
            }, url+"/index.php?user=" + ed1.getText().toString().trim() + "&pass=" + ed2.getText().toString().trim(), 1).execute();
        }catch (Exception e){ Toast.makeText(LoginFragment.this, "Invalid character found", Toast.LENGTH_SHORT).show();}
    }
}
