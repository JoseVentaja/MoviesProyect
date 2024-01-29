package moviesProyect.mainActivity.ui.view.adapter

import moviesProyect.mainActivity.BuildConfig
import moviesProyect.mainActivity.R
import moviesProyect.mainActivity.data.model.Dmovie
import moviesProyect.mainActivity.data.model.formatReleaseDate
import moviesProyect.mainActivity.databinding.CardDataMovieBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import moviesProyect.mainActivity.Constants

class PopularMoviesAdapter(var moviesList: List<Dmovie>) :
    RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {
    private lateinit var listener: PopularMoviesAdapter.OnClickListener
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularMoviesAdapter.PopularMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PopularMoviesViewHolder(
            layoutInflater.inflate(
                R.layout.card_data_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: PopularMoviesAdapter.PopularMoviesViewHolder,
        position: Int
    ) {
        val item = moviesList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = moviesList.size

    fun addListener(listener: OnClickListener) {
        this.listener = listener
    }

    inner class PopularMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = CardDataMovieBinding.bind(view)
        fun render(movie: Dmovie) {
            binding.title.text = movie.title
            binding.rating.text = movie.vote_average.toString()
            binding.filmYear.text = formatReleaseDate(movie.release_date)
            binding.duration.text = "1:20h"
            var moviePhoto = binding.headerImage

            val glideUrl = GlideUrl("${Constants.imageBaseURL}${movie.poster_path}") {
                mapOf(
                    Pair(
                        "Authorization",
                        "Authorization ${BuildConfig.API_KEY}"
                    )
                )
            }

            Glide.with(moviePhoto.context)
                .load(glideUrl)
                .into(moviePhoto)
            //ver que contiene overView en la api  binding.headerImage = movie.overview
            if(movie.isFavourite){
                binding.buttonAddFavourite.setBackgroundResource(R.drawable.ic_favorite_checked)
            } else {
                binding.buttonAddFavourite.setBackgroundResource(R.drawable.ic_add)
            }
            binding.buttonAddFavourite.setOnClickListener {
                    movie.isFavourite = !movie.isFavourite

                listener.addOrRemoveFavourite(movie)
            }
            itemView.setOnClickListener {
                listener.accessToMovieDetail(movie)
            }
        }
    }

    interface OnClickListener {
        fun addOrRemoveFavourite(movie: Dmovie)
        fun accessToMovieDetail(movie: Dmovie)
    }
}