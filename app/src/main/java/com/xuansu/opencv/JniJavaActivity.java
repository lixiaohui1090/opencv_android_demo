package com.xuansu.opencv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by xuansu on 2018/2/28.
 */

public class JniJavaActivity extends AppCompatActivity implements View.OnClickListener{

    static {
        System.loadLibrary("native-lib");
    }
    private ImageView imageView;
    private Button showBtn, processBtn,processOtherBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        imageView = (ImageView) findViewById(R.id.imageView);
        showBtn = (Button) findViewById(R.id.show);
        showBtn.setOnClickListener(this);
        processBtn = (Button) findViewById(R.id.process);
        processBtn.setOnClickListener(this);
        processOtherBtn=findViewById(R.id.process_other);
        processOtherBtn.setOnClickListener(this);
    }

    native  void  getEdge ( Object bitmap);
    native int[] grayProc(int[] pixels, int w, int h);
    @Override
    public void onClick(View v ) {
        if (v == showBtn) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.my23);
            imageView.setImageBitmap(bitmap);
        } else if(v==processBtn){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.my23);
            getEdge(bitmap);
            imageView.setImageBitmap(bitmap);
        }if(v==processOtherBtn){
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.my23);
            int w = bmp.getWidth();
            int h = bmp.getHeight();
            int[] pixels = new int[w*h];
            bmp.getPixels(pixels, 0, w, 0, 0, w, h);
            int[] resultInt = grayProc(pixels, w, h);
            Bitmap resultImg = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            resultImg.setPixels(resultInt, 0, w, 0, 0, w, h);
            imageView.setImageBitmap(resultImg);
        }
    }
}
