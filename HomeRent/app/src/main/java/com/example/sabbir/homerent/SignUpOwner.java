
package com.example.sabbir.homerent;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sabbir.homerent.app.AppConfig;
import com.example.sabbir.homerent.app.AppController;
import com.example.sabbir.homerent.helper.SQLiteHandler;
import com.example.sabbir.homerent.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SignUpOwner extends Activity {
    private static final String TAG = SignUpOwner.class.getSimpleName();
    private Button btnRegister;
    private EditText inputFirstName;
    private EditText inputSecondName;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputHomename;
    private EditText inputHomeadress;
    private EditText inputHowmanyFlat;
    private EditText inputHowmanyFlatinaflor;
    private EditText inputFamilyMember;
    private EditText inputm1;
    private EditText inputm2;
    private EditText inputm3;
    private EditText inputm4;

    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;


    private Button btnChoose;

    private ImageView imageView;


    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_owner);

        inputFirstName = (EditText) findViewById(R.id.OwnerFirstName);
        inputSecondName = (EditText) findViewById(R.id.OwnerLastName);

        inputEmail = (EditText) findViewById(R.id.Email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputHomename = (EditText) findViewById(R.id.OwnerHomeName);
        inputHomeadress = (EditText) findViewById(R.id.OwnerHomeAddress);
        inputHowmanyFlat = (EditText) findViewById(R.id.OwnerNoOfFlat);
        inputHowmanyFlatinaflor = (EditText) findViewById(R.id.OwnerNoOfFlatRent);
        inputFamilyMember = (EditText) findViewById(R.id.OwnerFamilyMember);
        inputm1 = (EditText) findViewById(R.id.OwnerMem1);
        inputm2 = (EditText) findViewById(R.id.OwnerMem2);
        inputm3 = (EditText) findViewById(R.id.OwnerMem3);
        inputm4 = (EditText) findViewById(R.id.OwnerMem4);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        imageView = (ImageView) findViewById(R.id.imageView);


        btnChoose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showFileChooser();

            }
        });


        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(SignUpOwner.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String FirstName = inputFirstName.getText().toString().trim();
                String SecondName = inputSecondName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String homename = inputHomename.getText().toString().trim();
                String homeadress = inputHomeadress.getText().toString().trim();
                String hflat = inputHowmanyFlat.getText().toString().trim();
                String hflatfloor	 = inputHowmanyFlatinaflor.getText().toString().trim();
                String fmember = inputFamilyMember.getText().toString().trim();
                String m1 = inputm1.getText().toString().trim();
                String m2 = inputm2.getText().toString().trim();
                String m3 = inputm3.getText().toString().trim();
                String m4 = inputm4.getText().toString().trim();
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                String image_name = inputFirstName.getText().toString().trim();


                if (!FirstName.isEmpty() && !SecondName.isEmpty() && !email.isEmpty() && !password.isEmpty() && imageView.getDrawable() != null) {
                    registerUser(FirstName, SecondName, email, password, homename, homeadress, hflat, hflatfloor, fmember, m1, m2, m3, m4, image, image_name);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });


    }

       public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
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
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     */
    private void registerUser(final String FirstName,final String SecondName, final String email,
                              final String password, final String homename, final String homeadress,
                              final String hflat,final String hflatfloor,final String fmember,final String m1,final String m2,final String m3,final String m4, final String image, final String image_name) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("FirstName");
                        String email = user.getString("email");

                        String created_at = user
                                .getString("created_at");

                        // Inserting row in users table
                        db.addUser(name, email, uid, created_at);

                        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                SignUpOwner.this,
                                Login.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("FirstName", FirstName);
                params.put("SecondName", SecondName);
                params.put("email", email);
                params.put("password", password);
                params.put("homename", homename);
                params.put("homeadress", homeadress);
                params.put("hflat", hflat);
                params.put("hflatfloor", hflatfloor);
                params.put("fmember", fmember);
                params.put("m1", m1);
                params.put("m2", m2);
                params.put("m3", m3);
                params.put("m4", m4);
                params.put("image", image);
                params.put("image_name", image_name);


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}