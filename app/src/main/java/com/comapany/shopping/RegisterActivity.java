package com.comapany.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private Button CreateAccountButton;
    private EditText inputname,inputphoneno,inputpassword;
    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        CreateAccountButton=findViewById(R.id.register_btn);
        inputname=findViewById(R.id.register_username_input);
        inputpassword=findViewById(R.id.register_password_input);
        inputphoneno=findViewById(R.id.register_phone_number_input);
        loadingbar=new ProgressDialog(this);



        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the account
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String phone=inputphoneno.getText().toString();
        String name=inputname.getText().toString();
        String password=inputpassword.getText().toString();
        //checking if any feild is left to be field
        if (inputname.getText().toString().contentEquals(""))
        {
            Toast.makeText(RegisterActivity.this, "Please type your Name", Toast.LENGTH_SHORT).show();
        }
        else if (inputphoneno.getText().toString().contentEquals(""))
        {
            Toast.makeText(RegisterActivity.this, "Please type your Phone number", Toast.LENGTH_SHORT).show();
        }
        else if (inputpassword.getText().toString().contentEquals(""))
        {
            Toast.makeText(RegisterActivity.this, "Please type your Password", Toast.LENGTH_SHORT).show();
        }
        else {
            //if all the feild is filled then we can move ahead
            loadingbar.setMessage("Please Wait..");
            loadingbar.setTitle("Create Account");
            loadingbar.setCancelable(false);
            loadingbar.show();
            Validatephoneno(phone,name,password);
        }
    }

    private void Validatephoneno(String phone,String name,String password)
    {
        //Checkinh wheather the user already have an account
        DatabaseReference rootref;
        rootref=FirebaseDatabase.getInstance().getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(phone).exists()))
                {
                    //if the user have not nay account with that no then we can store the information of the user
                    HashMap<String,Object> hashMap=new HashMap<>();
                    hashMap.put("name",name);
                    hashMap.put("phone",phone);
                    hashMap.put("password",password);
                    rootref.child("Users").child(phone).updateChildren(hashMap).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(RegisterActivity.this,"Account is created sucessfully",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterActivity.this,"Network Error : Plese try again",Toast.LENGTH_SHORT).show();
                                    }
                                    loadingbar.dismiss();
                                }
                            });
                }
                else
                {
                    //if the user have alredy have an account then we can ask them to use another no
                    Toast.makeText(RegisterActivity.this,"This "+ phone+" already exixts",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Toast.makeText(RegisterActivity.this,"Plaese try with another phone number",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}