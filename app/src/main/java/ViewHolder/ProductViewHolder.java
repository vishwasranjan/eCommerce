package ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.comapany.shopping.R;

import Interface.ItemClickListner;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView textProductName,textProductDescription,textProductPrice;
    public ImageView imageView;
    public ItemClickListner listner;
    public ProductViewHolder(@NonNull View itemView) {

        super(itemView);

        textProductName=itemView.findViewById(R.id.user_product_name);
        textProductDescription=itemView.findViewById(R.id.user_product_description);
        textProductPrice=itemView.findViewById(R.id.user_product_price);
        imageView=itemView.findViewById(R.id.user_product_image);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner=listner;
    }

    @Override
    public void onClick(View v)
    {
        listner.onclick(v,getAdapterPosition(),false);
    }
}
