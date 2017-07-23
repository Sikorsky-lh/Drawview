package com.example.drawview;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout ll=new LinearLayout(this);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        drawView=new DrawView(this,displayMetrics.widthPixels,displayMetrics.heightPixels);
        ll.addView(drawView);
        setContentView(ll);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_delete);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.paint_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.black:
                drawView.paint.setColor(Color.BLACK);
                item.setChecked(true);
                break;
            case R.id.blue:
                drawView.paint.setColor(Color.BLUE);
                item.setChecked(true);
                break;
            case R.id.green:
//                drawView.paint.setColor(Color.GREEN);
//                item.setChecked(true);
                break;
            case android.R.id.home:
//                Toast.makeText(MainActivity.this,"clear",Toast.LENGTH_SHORT).show();
                drawView.cacheCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                drawView.invalidate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
