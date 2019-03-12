package com.refreshviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.refreshviewpager.viewpager2.BannerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BannerView mBannerView;
    private Button mButton;

    List<String> imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBannerView = findViewById(R.id.bannerView);
        mButton = findViewById(R.id.button);
        imgs = new ArrayList<>();

        imgs.add("https://pic2.zhimg.com/v2-2a94d40c26310b3e4b9223fd8b1caa25_r.jpg");
        imgs.add("https://pic3.zhimg.com/v2-65ef0bc8af1353a5bf5ab7871d551596_r.jpg");
        imgs.add("https://pic3.zhimg.com/v2-e1d95e5c342cef28e479369dbe281792_r.jpg");
        imgs.add("https://pic3.zhimg.com/v2-4e76c4094087800f92f7bff0af0418fe_r.jpg");
        mBannerView.setImags(imgs);

    }

    public void refresh(View view) {
        List<String> imgs = new ArrayList<>();
        imgs.add("https://pic3.zhimg.com/v2-8b47956f9689ecc8c0e112195767f2da_r.jpg");
        imgs.add("https://pic1.zhimg.com/v2-0fc027959b884e99cf64fc908cc3e580_r.jpg");
        imgs.add("https://pic4.zhimg.com/v2-167d4a17934fa687b8d5edcbe810f063_r.jpg");
        imgs.add("https://pic4.zhimg.com/v2-b29bfc7c2d9441f473ce0566cb0bc217_r.jpg");
        mBannerView.upDate(imgs);
    }
}
