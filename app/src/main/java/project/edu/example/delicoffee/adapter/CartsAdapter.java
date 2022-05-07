package project.edu.example.delicoffee.adapter;

import android.content.Context;
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

import java.util.List;

import project.edu.example.delicoffee.R;
import project.edu.example.delicoffee.model.Product;

public class CartsAdapter extends RecyclerView.Adapter<CartsAdapter.CartsViewHolder> {
    List<Product> products;
    Context context;
    public CartsAdapter(Context context,List<Product> products)
    {
      this.context=context;
        this.products=products;
    }
    @NonNull
    @Override
    public CartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_your_order,parent,false);
        return new CartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartsViewHolder holder, int position) {
        final Product product=products.get(position);
        if(product==null)
        {
            return;
        }
        holder.home_item_productName.setText(product.getName());
        holder.txt_home_item_price.setText(product.getPrice());
        Glide.with(holder.img_home_item_url.getContext()).load(product.getUrl()).into(holder.img_home_item_url);

//        holder.btnadd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getOrderProduct(product);
//            }
//        });
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Integer.parseInt(holder.txtQuantity.getText().toString()) == 1) {
                    return;
                }
                holder.txtQuantity.setText(String.valueOf(Integer.parseInt(holder.txtQuantity.getText().toString()) - 1));
              getDetailProduct();

                return;
            }
        });
        PlusQuantityProduct();
    }

    private void MinusQuantityProduct() {

    }
    private void PlusQuantityProduct()
    {
    }
    private void getDetailProduct()
    {

    }

    @Override
    public int getItemCount() {
        if(products!=null)
        {
            return products.size();
        }
        return 0;
    }

    public class CartsViewHolder extends RecyclerView.ViewHolder{
        TextView home_item_productName,txt_home_item_price,txtQuantity;
        ImageView img_home_item_url;
        private Button btnMinus,btnPlus,btnBack,btnAddCart;

        public CartsViewHolder(@NonNull View itemView) {
            super(itemView);
            home_item_productName=itemView.findViewById(R.id.order_name);
            txt_home_item_price=itemView.findViewById(R.id.order_price);
            img_home_item_url=itemView.findViewById(R.id.order_url);
            btnMinus=itemView.findViewById(R.id.carts_btninus);
            btnPlus=itemView.findViewById(R.id.carts_btnPlus);
            txtQuantity=itemView.findViewById(R.id.carts_txtQuantity);
        }
    }
}
