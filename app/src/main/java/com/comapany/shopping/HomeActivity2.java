package com.comapany.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.comapany.shopping.Models.Product;
import com.comapany.shopping.Prevalent.Prevalent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import ViewHolder.ProductViewHolder;
import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class HomeActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DatabaseReference ProductRef;
    NavigationView navigationView;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        recyclerView=findViewById(R.id.recycler_manu);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        Paper.init(this);
        ProductRef= FirebaseDatabase.getInstance().getReference().child("Products");


        View headerview=navigationView.getHeaderView(0);
        TextView username=headerview.findViewById(R.id.nav_user_profile_name);
        CircleImageView userprofilepic=headerview.findViewById(R.id.nav_user_profile_image);
        username.setText(Prevalent.CurrntOnlineuser.getName());
    }




    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Product> options=new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(ProductRef,Product.class).build();
        FirebaseRecyclerAdapter<Product, ProductViewHolder> adapter=new
                FirebaseRecyclerAdapter<Product, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Product model) {
                        holder.textProductName.setText(model.getPname());
                        holder.textProductDescription.setText(model.getDescription());
                        holder.textProductPrice.setText("Price = "+model.getPrice());
                        Picasso.get().load(model.getImage()).into(holder.imageView);
                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout,parent,false);
                        ProductViewHolder holder=new ProductViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_cart:
                Intent intent2=new Intent(HomeActivity2.this,CartActivity.class);
                startActivity(intent2);
                return true;
            case R.id.nav_order:
                Toast.makeText(this,"video",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_categories:
                Toast.makeText(this,"audio",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_settings:
                Intent intent=new Intent(HomeActivity2.this,SettingActivity.class);
                startActivity(intent);
                return true;
            case R.id.nav_logout:
                AlertShowForLogout();
                return  true;
        }

        return false;
    }
    public void AlertShowForLogout()
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Logout");
        alert.setMessage("Are You Sure");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Paper.book().destroy();
                Intent intent=new Intent(HomeActivity2.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();

    }
}