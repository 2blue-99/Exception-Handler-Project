package com.example.data.dataSource

import com.example.data.model.ServerResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 2023-10-10
 * pureum
 */
interface DataSource {

    @GET("todos______/{id}")
    suspend fun getApiDataSource(
        @Path("id") id: String
    ):ServerResponse

    @GET("todos/{id}")
    suspend fun getApiFlowDataSource(
        @Path("id") id: String
    ): ServerResponse

    @GET("todos/{id}")
    suspend fun getApiResponseDataSource(
        @Path("id") id: String
    ): Response<ServerResponse>

    @GET("todos/{id}")
    suspend fun getApiResponseSealedDataSource(
        @Path("id") id: String
    ): Response<ServerResponse>
}