package MoviesProyect.mainActivity.ui.view.adapter

import MoviesProyect.mainActivity.BuildConfig
import MoviesProyect.mainActivity.Constants
import MoviesProyect.mainActivity.data.model.Dmovie
import MoviesProyect.mainActivity.data.model.formatReleaseDate
import MoviesProyect.mainActivity.databinding.CardDataMovieBinding
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl

class PopularMoviesViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val binding = CardDataMovieBinding.bind(view)

    fun render(movie : Dmovie) {
        binding.title.text = movie.title
        binding.rating.text = movie.vote_average.toString()
        binding.filmYear.text = formatReleaseDate(movie.release_date)
        var moviePhoto = binding.headerImage

        val glideUrl = GlideUrl("${Constants.imageBaseURL}${movie.poster_path}") { mapOf(Pair("Authorization", "Authorization ${BuildConfig.API_KEY}")) }

        Glide.with(moviePhoto.context)
            .load(glideUrl)
            .into(moviePhoto)
      //ver que contiene overView en la api  binding.headerImage = movie.overview
        //Añadir el resto de textViews con lo que quiero mostrar del objeto movie obtenido
        itemView.setOnClickListener {
            //Aqui próximamente debo añadir el fragment para acceder al detalle de película

        }
    }
}