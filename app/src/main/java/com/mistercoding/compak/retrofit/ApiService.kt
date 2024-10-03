package com.mistercoding.compak.retrofit

import com.mistercoding.compak.classes.Plans
import com.mistercoding.compak.classes.Projects
import com.mistercoding.compak.classes.Update
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("Projects/Index")
    suspend fun getAllProjects(): List<Projects>

    @GET("Updates/Index")
    suspend fun getAllUpdates(): List<Update>


    @GET("Plans/Index")
    suspend fun getAllPlans(): List<Plans>

    @FormUrlEncoded
    @POST("Subscription/Subscribe")
    suspend fun subscribePlan(
        @Field("email") email: String,
        @Field("planId") planId: Int
    ): Unit


    @GET("Subscription/GetSubscriptions")
    suspend fun getUserSubscriptions(
        @Query("email") email: String
    ): List<Plans>
}