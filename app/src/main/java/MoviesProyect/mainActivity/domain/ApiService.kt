package MoviesProyect.mainActivity.domain

import MoviesProyect.mainActivity.data.model.RemoteResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie?include_adult=false&include_video=true&language=es-ES&page=1&sort_by=popularity.desc")
    suspend fun listPopularMovies(
        @Query("api_key") apiKey: String
    ) :RemoteResult
    object RetrofitServiceFactoy{
        fun makeRetrofitService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
        }
    }
}