package com.example.morningdbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.morningdbdemo.data.DatabaseHandler;
import com.example.morningdbdemo.model.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button cmdAdd, cmdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cmdAdd = findViewById(R.id.cmdAdd);
        cmdView = findViewById(R.id.cmdViewAll);

        cmdAdd.setOnClickListener(startAddActivity);
        cmdView.setOnClickListener(startViewActivity);

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

        db.getAllProducts();

        List<Product> productList =  db.getAllProducts();

        for(Product product: productList) {
            Log.d("MainActivity", "On Create: " + product.getName() + ", " + product.getId());
        }


        //search
            Product product = db.getProduct(1);




        //Product product = new Product();

        product.setId(2);
        product.setName("Head Phone");
        product.setQuantity(2);
        product.setPrice(1500);

        int flag = db.updateProduct(product);
        Log.d("Flag", "Flag: " + flag);
    }

    View.OnClickListener startViewActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ViewAllProducts.class);
            startActivity(intent);
        }
    };

    View.OnClickListener startAddActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
            startActivity(intent);
        }
    };
}