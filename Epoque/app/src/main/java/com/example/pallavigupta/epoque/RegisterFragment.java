package com.example.pallavigupta.epoque;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class RegisterFragment extends Fragment {
    SharedPreferences sp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sp=getActivity().getSharedPreferences("Epoque2k16", Context.MODE_PRIVATE);
        Button button= (Button) getActivity().findViewById(R.id.btnlog);
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
        ed1= (EditText) getActivity().findViewById(R.id.editText);
        ed2= (EditText) getActivity().findViewById(R.id.editText1);
        ed3= (EditText) getActivity().findViewById(R.id.editText2);
        ed4= (EditText) getActivity().findViewById(R.id.editText3);
        ed5= (EditText) getActivity().findViewById(R.id.editText4);
        if(!(ed4.getText().toString().trim().equals(ed5.getText().toString().trim())))
        {
            Toast.makeText(getActivity(),"Set Password and Confirm Password do not match",Toast.LENGTH_SHORT).show();
            Log.d("ed4", ed4.getText().toString().trim());
            Log.d("ed5",ed5.getText().toString().trim());
            return;
        }
        try {
            new HitJSPService(getActivity(), null, new TaskCompleted() {

                @Override
                public void onTaskCompleted(String result, int resultType) {
                    try {
                        if(!result.equals("")&& !result.isEmpty()) {
                            Toast.makeText(getActivity(), "You have successfully registered with registration Id" + result, Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(getActivity(), "Please register again", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // TODO: handle exception
                        Toast.makeText(getActivity(), "Please try again", Toast.LENGTH_SHORT).show();
                    }

                }
            }, url+"/register.php?libid=" + ed1.getText().toString().trim() + "&libpass=" + ed2.getText().toString().trim() + "&number=" + ed3.getText().toString().trim() + "&pass=" + ed4.getText().toString().trim(), 1).execute();
        }catch (Exception e){ Toast.makeText(getActivity(), "Invalid character found", Toast.LENGTH_SHORT).show();}
    }
}
