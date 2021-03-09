package com.comapany.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {
    private ImageView Books,Clothes,femaleDresses,sweaters;
    private ImageView BoardAndOtherGames,ActivityandLargeToys,MontessoriWoodenToys,shoes;
    private ImageView LearningandEducationToys,InfantandToddlerToys,PretendPlayToys,mobilePhones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);
        Books=findViewById(R.id.books);
        Clothes=findViewById(R.id.clothes);
//        femaleDresses=findViewById(R.id.female_dresses);
//        sweaters=findViewById(R.id.sweters);
        BoardAndOtherGames=findViewById(R.id.board_and_other_games);
        ActivityandLargeToys=findViewById(R.id.activity_and_large_toys);
        MontessoriWoodenToys=findViewById(R.id.montessori_wooden_toys);
      //  shoes=findViewById(R.id.shoes);
        LearningandEducationToys=findViewById(R.id.Learning_and_Education_Toys);
        InfantandToddlerToys=findViewById(R.id.Infant_and_Toddler_Toys);
        PretendPlayToys=findViewById(R.id.PretendPlayToys);
        //mobilePhones=findViewById(R.id.mobilephones);

        Books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","Books");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        Clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","Clothes");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
//        femaleDresses.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
//                intent.putExtra("category","Female Dresses");
//                startActivity(intent);
//            }
//        });

//        sweaters.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
//                intent.putExtra("category","Sweaters");
//                startActivity(intent);
//            }
//        });


        BoardAndOtherGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","Board and Other Games");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        ActivityandLargeToys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","Activity and Large Toys");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        MontessoriWoodenToys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","Montessori Wooden Toys");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

//        shoes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
//                intent.putExtra("category","Shoes");
//                startActivity(intent);
//            }
//        });

        LearningandEducationToys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","Learning and Education Toys");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        InfantandToddlerToys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","Infant and Toddler Toys");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        PretendPlayToys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","Pretend Play Toys");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


//        mobilePhones.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
//                intent.putExtra("category","Mobile Phones");
//                startActivity(intent);
//            }
//        });

        
    }
}