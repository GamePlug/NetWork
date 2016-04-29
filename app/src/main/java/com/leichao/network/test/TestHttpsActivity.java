package com.leichao.network.test;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.leichao.network.R;
import com.leichao.network.net.retrofit.RetrofitManager;
import com.leichao.network.util.Debug;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by leichao on 2016/4/29.
 */
public class TestHttpsActivity extends BaseActivity {

    private static final String TAG = "TestHttpsActivity";
    private Button start;
    private TextView result;
    private boolean canClick = true;

    @Override
    public void initView() {
        setContentView(R.layout.activity_test);
        start = (Button) findViewById(R.id.test_start);
        result = (TextView) findViewById(R.id.test_result);
    }

    @Override
    public void initData() {
        setTitle(getIntent().getStringExtra("des"));
        start.setText("点击开始"+getIntent().getStringExtra("des"));
    }

    @Override
    public void initEvent() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canClick) {
                    canClick = false;
                    testGet();
                }
            }
        });
    }

    private void testGet() {
        result.setText("请求中");
        new RetrofitManager.Creator()
                .create(Api.class)
                .testHttps()
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Debug.e(TAG, "onResponse:" + response.body());
                        result.setText("请求成功：" + response.body());
                        canClick = true;
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Debug.e(TAG, "onFailure:" + t.getMessage());
                        result.setText("请求失败：" + t.getMessage());
                        canClick = true;
                    }
                });
    }
}
