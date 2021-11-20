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


       Product product = db.getProduct(3);

        if(product != null) {
            db.deleteProduct(product);
            Toast.makeText(getApplicationContext(),"Product" + product.getName() + " Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Id does not exist.", Toast.LENGTH_SHORT).show();
        }


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