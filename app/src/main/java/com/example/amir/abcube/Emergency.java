package com.example.amir.abcube;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

/**
 * Created by Amir on 4/25/2017.
 */

public class Emergency extends Fragment {
    //varaiale declaration
    EditText Name,Email,Relationship,Address,Phone_no;;
    Button Submit;
    String server_url = "http://192.168.0.126/adcube/emergency.php";
    AlertDialog.Builder builder;
    View myview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview =  inflater.inflate(R.layout.emergency_contact,container,false);

        Name = (EditText) myview.findViewById(R.id.contactname);
        Email = (EditText) myview.findViewById(R.id.email);
        Relationship = (EditText) myview.findViewById(R.id.relation);
       Phone_no= (EditText) myview.findViewById(R.id.phone);
       Address= (EditText) myview.findViewById(R.id.address);
        Submit = (Button) myview.findViewById(R.id.buton);
        builder = new AlertDialog.Builder(getActivity());

        Submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                final String name, email,relation,address,phone;
                name = Name.getText().toString();
                email = Email.getText().toString();
                relation = Relationship.getText().toString();
                address = Address.getText().toString();
                phone = Phone_no.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("Response:" + response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Name.setText("");
                                Email.setText("");
                                Relationship.setText("");
                                Address.setText("");
                                Phone_no.setText("");
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
                        params.put("name", name);
                        params.put("email", email);
                        params.put("relation",relation);
                        params.put("address",address);
                        params.put("phone",phone);
                        return params;
                    }
                };
                Mysingleton.getInstance(getActivity()).addToRequest(stringRequest);
            }

        });
        return myview;
    }

}

