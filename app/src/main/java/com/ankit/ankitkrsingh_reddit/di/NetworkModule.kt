package com.ankit.ankitkrsingh_reddit.di

import com.ankit.ankitkrsingh_reddit.data.RedditRestApi
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 14 Dec 2019
 * @version : 1.0
 *
 */
@Module
class NetworkModule {

    companion object{
        private const val BASE_URL = "https://oauth.reddit.com"
        private const val ACCESS_TOKEN = "bearer 223880446459-x4LuxMFNkzQDrIV3QXON3rv_A7M" //8:26
    }

    @Singleton
    @Provides
    fun provideRedditRestApi(retrofit: Retrofit) = retrofit.create(RedditRestApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()/*createGsonConverterFactory(AppTypeAdapterFactory.newInstance())*/)
            .client(okHttpClient)
            .build()


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(object :Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request = chain.request()
                    request = request.newBuilder()
                        .addHeader("Authorization", ACCESS_TOKEN)
                        .addHeader("User-Agent", "RedditPosts by Ankitdev007")
                        .build()

                    return chain.proceed(request)
                }

            })
            .addInterceptor(logging)
            .build()
    }




    private fun createGsonConverterFactory(typeAdapterFactory: TypeAdapterFactory): GsonConverterFactory {
        val gson = GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory)
            .excludeFieldsWithoutExposeAnnotation().create()
        return GsonConverterFactory.create(gson)
    }
}