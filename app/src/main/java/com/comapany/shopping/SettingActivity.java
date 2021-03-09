package com.comapany.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.comapany.shopping.Prevalent.Prevalent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends AppCompatActivity {
    private CircleImageView profileImageView;
    private EditText fullnameEditText,userPhoneEditText,addressEditText;
    private TextView profileChangeTextbtn,closeTextbtn,saveTextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        profileImageView=findViewById(R.id.settings_profile_image);
        fullnameEditText=findViewById(R.id.setting_full_name);
        userPhoneEditText=findViewById(R.id.setting_phone_number);
        addressEditText=findViewById(R.id.setting_address);
        profileChangeTextbtn=findViewById(R.id.profile_image_change_btn);
        closeTextbtn=findViewById(R.id.close_setting_btn);
        saveTextbtn=findViewById(R.id.update_account_setting_btn);

        UserInfoDisplay(profileImageView,userPhoneEditText,fullnameEditText,addressEditText);

        saveTextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });
        

    }

    private void UserInfoDisplay(CircleImageView profileImageView, EditText userPhoneEditText, EditText fullnameEditText, EditText addressEditText)
    {
        //Display The User Information

        DatabaseReference userRef= FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.CurrntOnlineuser.getPhone());
    }
}