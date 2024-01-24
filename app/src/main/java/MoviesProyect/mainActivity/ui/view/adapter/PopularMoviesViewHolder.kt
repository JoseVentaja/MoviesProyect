package MoviesProyect.mainActivity.ui.view.adapter

import MoviesProyect.mainActivity.data.model.Dmovie
import MoviesProyect.mainActivity.databinding.CardDataMovieBinding
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PopularMoviesViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val binding = CardDataMovieBinding.bind(view)

    fun render(movie : Dmovie) {
        binding.title.text = "hola"
        var moviePhoto = binding.headerImage
        Glide.with(moviePhoto.context)
            .load(movie.backdrop_path)
            .into(moviePhoto)
      //ver que contiene overView en la api  binding.headerImage = movie.overview
        //Añadir el resto de textViews con lo que quiero mostrar del objeto movie obtenido
        itemView.setOnClickListener {
            //Aqui próximamente debo añadir el fragment para acceder al detalle de película

        }
    }
}