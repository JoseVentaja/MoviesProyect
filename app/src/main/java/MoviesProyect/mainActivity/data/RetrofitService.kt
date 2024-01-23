package MoviesProyect.mainActivity.data

import MoviesProyect.mainActivity.data.model.RemoteResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("discover/movie?include_adult=false&include_video=true&language=es-ES&page=1&sort_by=popularity.desc")
    suspend fun listPopularMovies(
        @Query("api_key") apiKey: String
    ) :RemoteResult
    object RetrofitServiceFactoy{
        fun makeRetrofitService(): RetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
        }
    }
}