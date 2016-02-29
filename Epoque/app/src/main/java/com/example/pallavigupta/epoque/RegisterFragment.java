package com.example.pallavigupta.epoque;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by USER on 02/27/2016.
 */
public class RegisterFragment extends AppCompatActivity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sp=getSharedPreferences("Epoque2k16", Context.MODE_PRIVATE);
        Button button= (Button) findViewById(R.id.btnlog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click","click");
                Authenticate();
            }
        });
    }
    public void Authenticate()
    {
        Log.d("click","click");
        Constant co=new Constant();
        String url=co.url;
        EditText ed1,ed2,ed3,ed4,ed5;
        ed1= (EditText) findViewById(R.id.editText);
        ed2= (EditText) findViewById(R.id.editText1);
        ed3= (EditText) findViewById(R.id.editText2);
        ed4= (EditText) findViewById(R.id.editText3);
        ed5= (EditText) findViewById(R.id.editText4);
        if(!(ed4.getText().toString().trim().equals(ed5.getText().toString().trim())))
        {
            Toast.makeText(this,"Set Password and Confirm Password do not match",Toast.LENGTH_SHORT).show();
            Log.d("ed4", ed4.getText().toString().trim());
            Log.d("ed5",ed5.getText().toString().trim());
            return;
        }
        try {
            new HitJSPService(this, null, new TaskCompleted() {

                @Override
                public void onTaskCompleted(String result, int resultType) {
                    try {
                        if(!result.equals("")&& !result.isEmpty()) {
                            Toast.makeText(RegisterFragment.this, "You have successfully registered with registration Id" + result, Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(RegisterFragment.this, "Please register again", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // TODO: handle exception
                        Toast.makeText(RegisterFragment.this, "Please try again", Toast.LENGTH_SHORT).show();
                    }

                }
            }, url+"/register.php?libid=" + ed1.getText().toString().trim() + "&libpass=" + ed2.getText().toString().trim() + "&number=" + ed3.getText().toString().trim() + "&pass=" + ed4.getText().toString().trim(), 1).execute();
        }catch (Exception e){ Toast.makeText(RegisterFragment.this, "Invalid character found", Toast.LENGTH_SHORT).show();}
    }
}
