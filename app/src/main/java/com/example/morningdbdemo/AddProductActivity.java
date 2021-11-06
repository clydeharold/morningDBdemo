package com.example.morningdbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.morningdbdemo.data.DatabaseHandler;
import com.example.morningdbdemo.model.Product;

import java.util.List;

public class AddProductActivity extends AppCompatActivity {

    Button cmdAddProduct;
    EditText txtName, txtPrice, txtQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        refs();

        cmdAddProduct.setOnClickListener(addNewProduct);
    }

    View.OnClickListener addNewProduct = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(txtName.getText().toString().isEmpty()) {

            }
            else {
                Product product = new Product();
                product.setName(txtName.getText().toString());
                product.setPrice(Long.parseLong(txtPrice.getText().toString()));
                product.setQuantity(Integer.parseInt(txtQuantity.getText().toString()));

                DatabaseHandler db = new DatabaseHandler(AddProductActivity.this);
                if(db.addProduct(product)) {
                    Toast.makeText(getApplicationContext(), "Successfully Added", Toast.LENGTH_SHORT).show();

                    List<Product> productList =  db.getAllProducts();

                    for(Product prod: productList) {
                        Log.d("MainActivity", "On Create: " + prod.getName());
                    }
                }

            }
        }
    };

    public void refs(){
        cmdAddProduct = findViewById(R.id.cmdAddProduct);
        txtName = findViewById(R.id.txtProductName);
        txtPrice = findViewById(R.id.txtPrice);
        txtQuantity = findViewById(R.id.txtQuantity);
    }
}