package project.edu.example.delicoffee.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import project.edu.example.delicoffee.activity.Product_Detail;
import project.edu.example.delicoffee.activity.Profile_S;
import project.edu.example.delicoffee.R;
import project.edu.example.delicoffee.activity.SingIn;
import project.edu.example.delicoffee.activity.Your_Orders;
import project.edu.example.delicoffee.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    List<Product> products;
    Context context;
    public ProductAdapter(Context context,List<Product> products)
    {
        this.context=context;
        this.products=products;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_product,parent,false);
        return new ProductViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) { //bind dữ liệu từ class lên
        final Product product=products.get(position);
        if(product==null)
        {
            return;
        }
        holder.home_item_productName.setText(product.getName());
        holder.txt_home_item_price.setText(product.getPrice());
        Glide.with(holder.img_home_item_url.getContext()).load(product.getUrl()).into(holder.img_home_item_url);

        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOrderProduct(product);
            }
        });
    }
    private void getOrderProduct(Product product){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null)
        {
            Intent intent = new Intent(context,SingIn.class);
            context.startActivity(intent);
        }
        else
        {
            Intent intent=new Intent(context, Product_Detail.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("key_product",product);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }

    }
    @Override
    public int getItemCount() { //get size list product
        if(products!=null)
        {
            return products.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView home_item_productName,txt_home_item_price;
        ImageView img_home_item_url;
        private Button btnadd;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            home_item_productName=itemView.findViewById(R.id.home_item_productName);
            txt_home_item_price=itemView.findViewById(R.id.txt_home_item_price);
            img_home_item_url=itemView.findViewById(R.id.img_home_item_url);
            btnadd  = itemView.findViewById(R.id.home_add);
            initListener(btnadd);
        }
    }

    private void initListener(Button btnadd)
    {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewer) {
                checkLogin(viewer);
            }
        });
    }
    private void checkLogin(View viewer)
    { // kiemr tra login hay chưa
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null)
        {
            // chưa login
            Intent intent = new Intent(viewer.getContext(), SingIn.class);
            viewer.getContext().startActivity(intent);

        }
        else
        {
            Intent intent = new Intent(viewer.getContext(), Product_Detail.class);
            viewer.getContext().startActivity(intent);
        }
        Activity activity = (Activity)viewer.getContext();
        activity.finish();
    }

}
