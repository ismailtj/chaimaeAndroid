package module

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("temps")
    suspend fun createTemp(@Body temp: Temp):Response<Temp>
}