package com.example.sabbir.homerent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class Image_Select extends AppCompatActivity {

    private ImageView imageView;
    private EditText etName;
    private Button btnChoose,btnSave;
    private MainActivity mainActivity = new MainActivity();
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private  String url;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image__select);
        imageView = (ImageView)findViewById(R.id.imageview);
        etName = (EditText)findViewById(R.id.imageName);
        btnChoose = (Button)findViewById(R.id.choosebtn);
        btnSave = (Button)findViewById(R.id.uploadbtn);

        url = getIntent().getStringExtra("url");


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

btnSave.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        name = etName.getText().toString();
        if(bitmap != null && !name.equals("")) {
            String encoded = OwnerProfile.getStringImage(bitmap);
            OwnerProfile.registerOwnerMember(encoded, name, url);
            startActivity(new Intent(Image_Select.this,OwnerProfile.class));
            finish();
        }else{

            Toast.makeText(getApplicationContext(),"No Image Select",Toast.LENGTH_SHORT).show();

        }
    }
});


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



}
