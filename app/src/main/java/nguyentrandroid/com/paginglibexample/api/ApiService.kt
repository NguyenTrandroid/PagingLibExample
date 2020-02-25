package nguyentrandroid.com.paginglibexample.api

import nguyentrandroid.com.paginglibexample.models.like.Like
import nguyentrandroid.com.paginglibexample.models.notis.Notis
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response


interface ApiService {


    @GET("/likes")
    suspend fun likes(
        @Query("u") u: String? =null,
        @Query("w") w: String? = null,
        @Query("l") l: Int? = null
        ): Response<Like>

    @GET("/likes")
    suspend fun likesAfter(
        @Query("u") u: String? =null,
        @Query("w") w: String? = null,
        @Query("l") l: Int? = null,
        @Query("a") a: String? = null
    ): Response<Like>

    @GET("/notis?t=information,order,order_n_busi,wallet,business,info_n_busi,info_n_wallet,busi_n_wallet,order_n_wallet,info_n_busi_n_wallet,order_n_busi_n_wallet,noti_user_react_tour,noti_user_react_shop,noti_user_react_hotel,noti_user_react_page")
    suspend fun notis(
        @Query("u") u: String? =null,
        @Query("l") l: Int? = null
    ): Response<Notis>

    @GET("/notis?t=information,order,order_n_busi,wallet,business,info_n_busi,info_n_wallet,busi_n_wallet,order_n_wallet,info_n_busi_n_wallet,order_n_busi_n_wallet,noti_user_react_tour,noti_user_react_shop,noti_user_react_hotel,noti_user_react_page")
    suspend fun notisAfter(
        @Query("u") u: String? =null,
        @Query("l") l: Int? = null,
        @Query("a") a: String? = null
    ): Response<Notis>




}