package com.example.amir.abcube;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

/**
 * Created by Amir on 4/25/2017.
 */

public class English_language extends android.support.v4.app.Fragment {
    EditText Testname, Testdate, Testreport, Overallresult, Reading, Writing, Listening, Speaking;
    Button Submit;
    String server_url = "http://192.168.0.125/adcube/englishlanguage.php";
    AlertDialog.Builder builder;
    View myview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.english_language, container, false);

        Testname = (EditText) myview.findViewById(R.id.testname);
        Testdate = (EditText) myview.findViewById(R.id.testdate);
        Testreport = (EditText) myview.findViewById(R.id.testreport);
        Overallresult = (EditText) myview.findViewById(R.id.overallresult);
        Reading = (EditText) myview.findViewById(R.id.reading);
        Writing = (EditText) myview.findViewById(R.id.writing);
        Listening = (EditText) myview.findViewById(R.id.listening);
        Speaking = (EditText) myview.findViewById(R.id.speaking);
        Submit = (Button) myview.findViewById(R.id.submitenglish);
        builder = new AlertDialog.Builder(getActivity());


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String testname, testdate, testreport, overallresult, reading, writing, listening, speaking;
                testname = Testname.getText().toString();
                testreport = Testreport.getText().toString();
                overallresult = Overallresult.getText().toString();
                reading = Reading.getText().toString();
                writing = Writing.getText().toString();
                listening = Listening.getText().toString();
                speaking = Speaking.getText().toString();
                testdate = Testdate.getText().toString();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("Response:" + response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Testname.setText("");
                                Testdate.setText("");
                                Testreport.setText("");
                                Overallresult.setText("");
                                Reading.setText("");
                                Writing.setText("");
                                Listening.setText("");
                                Speaking.setText("");
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }

                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("testname", testname);
                        params.put("testdate",testdate);
                        params.put("testreport", testreport);
                        params.put("overallresult", overallresult);
                        params.put("reading", reading);
                        params.put("writing", writing);
                        params.put("listening", listening);
                        params.put("speaking", speaking);
                        return params;
                    }
                };

                Mysingleton.getInstance(getActivity()).addToRequest(stringRequest);
            }

        });
        return myview;
    }

}




