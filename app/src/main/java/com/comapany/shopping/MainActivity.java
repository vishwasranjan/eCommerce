package com.comapany.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.comapany.shopping.Models.User;
import com.comapany.shopping.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private Button joinnowbutton,loginbutton;
    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        joinnowbutton=findViewById(R.id.main_join_now_btn);
        loginbutton=findViewById(R.id.main_login_btn);
        loadingbar=new ProgressDialog(this);

        Paper.init(this);

        //welcome activity

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        joinnowbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        String UserphoneKey= Paper.book().read(Prevalent.userphonekey);
        String Userpasswordkey=Paper.book().read(Prevalent.userpasswordkey);
        if (UserphoneKey!=""&&Userpasswordkey!="")
        {
            if (!TextUtils.isEmpty(UserphoneKey)&&!TextUtils.isEmpty(UserphoneKey))
            {

                loadingbar.setMessage("Please Wait..");
                loadingbar.setTitle("Already Logged In");
                loadingbar.setCancelable(false);
                loadingbar.show();
                AllowAcess(UserphoneKey, Userpasswordkey);
            }
        }

    }

    private void AllowAcess(String userphoneKey, String userpasswordkey)
    {
        DatabaseReference rootref;
        rootref= FirebaseDatabase.getInstance().getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("Users").child(userphoneKey).exists())
                {
                    User user=snapshot.child("Users").child(userphoneKey).getValue(User.class);
                    if (user.getPhone().equals(userphoneKey))
                    {
                        if (user.getPassword().equals(userpasswordkey))
                        {
                            Toast.makeText(MainActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,HomeActivity2.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Incorrect Password",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Account with this  "+userphoneKey+" do not exist",Toast.LENGTH_SHORT).show();
                }
                loadingbar.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}