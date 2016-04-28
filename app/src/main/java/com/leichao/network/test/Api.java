package com.leichao.network.test;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * 网络请求接口
 * Created by leichao on 2016/4/27.
 */
public interface Api {
    //------------- get请求 ----------------//

    @GET("api/data/福利/{pageCount}/{pageIndex}")
    Call<String> testGet(
            @Path("pageCount") int pageCount,
            @Path("pageIndex") int pageIndex,
            @Query("who") String who
    );


    //------------- post请求 ----------------//

    @POST("api/data/福利/{pageCount}/{pageIndex}")
    Call<String> testPost(
            @Path("pageCount") int pageCount,
            @Path("pageIndex") int pageIndex
    );

    @FormUrlEncoded
    @POST("api/data/福利/{pageCount}/{pageIndex}")
    Call<String> testPost(
            @Path("pageCount") int pageCount,
            @Path("pageIndex") int pageIndex,
            @Field("who") String who
    );


    //------------- 下载 ----------------//

    @GET("http://119.29.62.241/leichao/data/upload/image/{fileName}")
    @Streaming// 如果不加此注释，则会把整个文件写入内存，那么大文件会内存溢出。
    Call<ResponseBody> testGetDownload(
            @Path("fileName") String fileName
    );

    @POST("http://srctest.didi365.com/didi365/Upload/app/{fileName}")
    @Streaming
    Call<ResponseBody> testPostDownload(
            @Path("fileName") String fileName
    );


    //------------- 上传 ----------------//

    @Multipart
    @POST("http://119.29.62.241/leichao/study/UploadMoreImage.php")
    Call<String> testUpload(
            @Part("who") String who,
            @PartMap Map<String, RequestBody> params
    );
}
