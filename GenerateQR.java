package com.example.cconsiteapsn;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class GenerateQR extends AppCompatActivity {

    EditText txtname,txtemail,txtphone,txtdisability,txtnotes;
    Button btnsave;
    private ImageView imageView;
    DatabaseReference reff;
    Members members;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_q_r);
        txtname=(EditText)findViewById(R.id.txtname);
        txtemail=(EditText)findViewById(R.id.txtemail);
        txtphone=(EditText)findViewById(R.id.txtphone);
        txtdisability=(EditText)findViewById(R.id.txtdisability);
        txtnotes=(EditText)findViewById(R.id.txtnotes);
        btnsave=(Button)findViewById(R.id.btnsave);
        imageView=findViewById(R.id.imageView);
        members=new Members();
        reff= FirebaseDatabase.getInstance().getReference().child("Members");
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                members.setName(txtname.getText().toString().trim());
                members.setEmail(txtemail.getText().toString().trim());
                members.setPhone(txtphone.getText().toString().trim());
                members.setDisability(txtdisability.getText().toString().trim());
                members.setNotes(txtnotes.getText().toString().trim());
                reff.child("member").setValue(members);
                String qrInput;
                qrInput = "Name:"+txtname.getText()+" Email:"+txtemail.getText()+" Phone:"+txtphone.getText()+" Disability:"+txtdisability.getText()+" Notes:"+txtnotes.getText();

                QRCodeWriter qrCodeWriter=new QRCodeWriter();

                try {
                    BitMatrix bitMatrix= qrCodeWriter.encode(qrInput.toString(), BarcodeFormat.QR_CODE,200,200);
                    Bitmap bitmap= Bitmap.createBitmap(200,200,Bitmap.Config.RGB_565);

                    for(int x=0;x<200;x++)
                    {
                        for(int y=0;y<200;y++)
                        {
                            bitmap.setPixel(x,y,bitMatrix.get(x,y)? Color.BLACK : Color.WHITE);
                        }
                    }

                    imageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
