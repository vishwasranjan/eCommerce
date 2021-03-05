package com.comapany.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.comapany.shopping.Models.User;
import com.comapany.shopping.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private EditText Inputphoneno,Inputpassword;
    private Button Loginbutton;
    private ProgressDialog loadingbar;
    private CheckBox checkBoxrememberme;
    private TextView Adminlink,Notadminlink;
    private String parentsDBname="Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Inputpassword=findViewById(R.id.login_password_input);
        Inputphoneno=findViewById(R.id.login_phone_number_input);
        Loginbutton=findViewById(R.id.login_btn);
        loadingbar=new ProgressDialog(this);
        checkBoxrememberme=findViewById(R.id.remember_me_checkbox);
        Adminlink=findViewById(R.id.i_am_admin_link);
        Notadminlink=findViewById(R.id.i_am_not_admin_link);

        Paper.init(this);

        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logintouser();
            }
        });


        Adminlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loginbutton.setText("Login Admin");
                Adminlink.setVisibility(View.INVISIBLE);
                Notadminlink.setVisibility(View.VISIBLE);
                parentsDBname="Admins";
            }
        });

        Notadminlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loginbutton.setText("Login");
                Adminlink.setVisibility(View.VISIBLE);
                Notadminlink.setVisibility(View.INVISIBLE);
                parentsDBname="Users";
            }
        });


    }

    private void Logintouser()
    {
        String phone=Inputphoneno.getText().toString();
        String password=Inputpassword.getText().toString();

        if(Inputphoneno.getText().toString().contentEquals(""))
        {
            Toast.makeText(LoginActivity.this, "Please type your Phone number", Toast.LENGTH_SHORT).show();
        }
        else if (Inputpassword.getText().toString().contentEquals(""))
        {
            Toast.makeText(LoginActivity.this, "Please type your Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingbar.setMessage("Please Wait..");
            loadingbar.setTitle("Login Account");
            loadingbar.setCancelable(false);
            loadingbar.show();

            Allowacesstouser(phone,password);
        }
    }

    private void Allowacesstouser(String phone, String password)
    {

        if (checkBoxrememberme.isChecked())
        {
            Paper.book().write(Prevalent.userphonekey,phone);
            Paper.book().write(Prevalent.userpasswordkey,password);
        }
        DatabaseReference rootref;
        rootref= FirebaseDatabase.getInstance().getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(parentsDBname).child(phone).exists())
                {
                    User user=snapshot.child(parentsDBname).child(phone).getValue(User.class);
                    if (user.getPhone().equals(phone))
                    {
                        if (user.getPassword().equals(password))
                        {
                           if (parentsDBname.equals("Admins"))
                           {
                               Toast.makeText(LoginActivity.this,"Admin is Signned in Sucessfully",Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(LoginActivity.this,AdminCategoryActivity.class);
                               startActivity(intent);
                           }
                           else if (parentsDBname.equals("Users"))
                           {
                               Toast.makeText(LoginActivity.this,"Signned In Sucessfully",Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(LoginActivity.this,HomeActivity2.class);
                               startActivity(intent);
                               Prevalent.CurrntOnlineuser=user;
                           }
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"Incorrect Password",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Account with this  "+phone+" do not exist",Toast.LENGTH_SHORT).show();
                }
                loadingbar.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}