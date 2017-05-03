package com.example.sabbir.homerent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.sabbir.homerent.app.AppController;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OwnerProfile extends AppCompatActivity {

    private TextView address,city,zip,state,floor,name_m1,name_m2,name_m3,name_m4,name_e1,name_e2,name_e3,name_e4;
    private String fName,lName,ownerAddress,ownerCity,ownerZip,ownerState,ownerFloor;
    private ImageView houseImg,img_m1,img_m2,img_m3,img_m4,img_e1,img_e2,img_e3,img_e4;
    private Bitmap bitmap;
    private String link;
    private String name;
    private String Oid;
    private int count;
    private static String id = "3";
    private int PICK_IMAGE_REQUEST = 1;
    private String url_m1 = "http://appincubatorbd.xyz/homerent/owner_page_data/member_one_retrive.php";
    private String url_m2 = "http://appincubatorbd.xyz/homerent/owner_page_data/member_two_retrive.php";
    private String url_m3 = "http://appincubatorbd.xyz/homerent/owner_page_data/member_three_retrive.php";
    private String url_m4 = "http://appincubatorbd.xyz/homerent/owner_page_data/member_four_retrive.php";
    private String url_e1 = "http://appincubatorbd.xyz/homerent/owner_page_data/employee_one_retrive.php";
    private String url_e2 = "http://appincubatorbd.xyz/homerent/owner_page_data/employee_two_retrive.php";
    private String url_e3 = "http://appincubatorbd.xyz/homerent/owner_page_data/employee_three_retrive.php";
    private String url_e4 = "http://appincubatorbd.xyz/homerent/owner_page_data/employee_four_retrive.php";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        address = (TextView)findViewById(R.id.owner_house_address);
        city = (TextView)findViewById(R.id.owner_city);
        zip = (TextView)findViewById(R.id.owner_zip);
        state = (TextView)findViewById(R.id.owner_house_address);
        floor = (TextView)findViewById(R.id.owner_floor_no);
        houseImg = (ImageView)findViewById(R.id.owner_house_banner_img);
        img_m1 = (ImageView)findViewById(R.id.owner_profile_member_one_img);
        name_m1 = (TextView)findViewById(R.id.owner_profile_member_one_name);
        img_m2 = (ImageView)findViewById(R.id.owner_profile_member_two_img);
        name_m2 = (TextView)findViewById(R.id.owner_profile_member_two_name);
        img_m3 = (ImageView)findViewById(R.id.owner_profile_member_three_img);
        name_m3 = (TextView)findViewById(R.id.owner_profile_member_three_name);
        img_m4 = (ImageView)findViewById(R.id.owner_profile_member_four_img);
        name_m4 = (TextView)findViewById(R.id.owner_profile_member_four_name);
        img_e1 = (ImageView)findViewById(R.id.owner_profile_staff_one_img);
        name_e1 = (TextView)findViewById(R.id.owner_profile_staff_one_name);
        img_e2 = (ImageView)findViewById(R.id.owner_profile_staff_two_img);
        name_e2 = (TextView)findViewById(R.id.owner_profile_staff_two_name);
        img_e3 = (ImageView)findViewById(R.id.owner_profile_staff_three_img);
        name_e3 = (TextView)findViewById(R.id.owner_profile_staff_three_name);
        img_e4 = (ImageView)findViewById(R.id.owner_profile_staff_four_img);
        name_e4 = (TextView)findViewById(R.id.owner_profile_staff_four_name);



        fetchingData();
        fetchingBannerData();
        fetchingMemOneData(img_m1,name_m1,url_m1);
        fetchingMemOneData(img_m2,name_m2,url_m2);
        fetchingMemOneData(img_m3,name_m3,url_m3);
        fetchingMemOneData(img_m4,name_m4,url_m4);
        fetchingMemOneData(img_e1,name_e1,url_e1);
        fetchingMemOneData(img_e2,name_e2,url_e2);
        fetchingMemOneData(img_e3,name_e3,url_e3);
        fetchingMemOneData(img_e4,name_e4,url_e4);

        houseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerProfile.this,Image_Select.class);
                intent.putExtra("url","http://appincubatorbd.xyz/homerent/owner_page_data/banner.php");
                startActivity(intent);
                finish();
            }
        });

        img_m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerProfile.this,Image_Select.class);
                intent.putExtra("url","http://appincubatorbd.xyz/homerent/owner_page_data/member_one.php");
                startActivity(intent);
                finish();
            }
        });

        img_m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerProfile.this,Image_Select.class);
                intent.putExtra("url","http://appincubatorbd.xyz/homerent/owner_page_data/member_two.php");
                startActivity(intent);
                finish();
            }
        });

        img_m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerProfile.this,Image_Select.class);
                intent.putExtra("url","http://appincubatorbd.xyz/homerent/owner_page_data/member_three.php");
                startActivity(intent);
                finish();
            }
        });

        img_m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerProfile.this,Image_Select.class);
                intent.putExtra("url","http://appincubatorbd.xyz/homerent/owner_page_data/member_four.php");
                startActivity(intent);
                finish();
            }
        });

        img_e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerProfile.this,Image_Select.class);
                intent.putExtra("url","http://appincubatorbd.xyz/homerent/owner_page_data/employee_one.php");
                startActivity(intent);
                finish();
            }
        });

        img_e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerProfile.this,Image_Select.class);
                intent.putExtra("url","http://appincubatorbd.xyz/homerent/owner_page_data/employee_two.php");
                startActivity(intent);
                finish();
            }
        });

        img_e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerProfile.this,Image_Select.class);
                intent.putExtra("url","http://appincubatorbd.xyz/homerent/owner_page_data/employee_three.php");
                startActivity(intent);
                finish();
            }
        });

        img_e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerProfile.this,Image_Select.class);
                intent.putExtra("url","http://appincubatorbd.xyz/homerent/owner_page_data/employee_four.php");
                startActivity(intent);
                finish();
            }
        });


    }


    public static String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        //Toast.makeText(getApplicationContext(),encodedImage+"end",Toast.LENGTH_SHORT).show();
        return encodedImage;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                houseImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    protected static void registerOwnerMember(final String image, final String name, String url) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";
//        String URL_REGISTER = "http://appincubatorbd.xyz/homerent/owner_page_data/banner.php";
        String URL_REGISTER = url;

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                       // Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        //Toast.makeText(OwnerProfile.this, errorMsg+"err", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getApplicationContext(),
                      //  error.getMessage()+"err", Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("image", image);
                params.put("name", name);
                params.put("id",id);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    void fetchingBannerData(){

        //String url = "http://192.168.0.103/android_connect/api/gettingnews.php";
        final String url = "http://appincubatorbd.xyz/homerent/owner_page_data/banner_retrive.php";


        JsonArrayRequest jar = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = response.length() - 1 ; i >= 0 ; i--){

                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        link = jsonObject.getString("banner_url");
                        Oid = jsonObject.getString("owner_id");
                        if( Oid.equals(id)) {
                            count = i;
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                //Toast.makeText(getApplicationContext(),link+"",Toast.LENGTH_SHORT).show();
                Picasso.with(OwnerProfile.this).load(link).into(houseImg);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Errpr:"+error);
            }
        }
        );

        AppController.getInstance().addToRequestQueue(jar);

    }

    void fetchingMemOneData(final ImageView img, final TextView txt, String url){

        //final String url = "http://appincubatorbd.xyz/homerent/owner_page_data/member_one_retrive.php";

        JsonArrayRequest jar = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                for(int i = response.length() - 1 ; i >= 0 ; i--){

                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        link = jsonObject.getString("url");
                        name = jsonObject.getString("name");
                        Oid = jsonObject.getString("owner_id");
                        if( Oid.equals(id)) {
                            count = i;
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                Picasso.with(OwnerProfile.this).load(link).into(img);
                txt.setText(name);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Errpr:"+error);
            }
        }
        );

        AppController.getInstance().addToRequestQueue(jar);

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
