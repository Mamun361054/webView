package com.example.sabbir.homerent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sabbir.homerent.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OwnerProfile extends AppCompatActivity {

    private TextView address,city,zip,state,floor;
    private String fName,lName,ownerAddress,ownerCity,ownerZip,ownerState,ownerFloor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);
        address = (TextView)findViewById(R.id.owner_house_address);
        city = (TextView)findViewById(R.id.owner_city);
        zip = (TextView)findViewById(R.id.owner_zip);
        state = (TextView)findViewById(R.id.owner_house_address);
        floor = (TextView)findViewById(R.id.owner_floor_no);

        fetchingData();

    }


    void fetchingData(){

        //String url = "http://192.168.0.103/android_connect/api/gettingnews.php";
        String url = "http://appincubatorbd.xyz/homerent/android_login_api/ownerProfileData.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0 ; i < response.length() ; i++){

                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        fName = jsonObject.getString("FirstName");
                        lName = jsonObject.getString("SecondName");
                        ownerAddress = jsonObject.getString("homeadress");
                        ownerFloor = jsonObject.getString("hflatfloor");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                address.setText(ownerAddress);
                floor.setText(ownerFloor);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Errpr:"+error);
            }
        }
        );

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    }


}
