package com.xuansu.opencv

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_jni.*

class JniActivity : AppCompatActivity() ,View.OnClickListener {


    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jni)
        show.setOnClickListener {
           val  bitmap=BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher)
            imageView.setImageBitmap(bitmap)
        }
        process.setOnClickListener {
            val  bitmap=BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher)
            getEdge(bitmap);
            imageView.setImageBitmap(bitmap)
        }
    }

    override fun onClick(p0: View?) {
    }

    //获得Canny边缘
    external fun getEdge(bitmap: Any)
}
