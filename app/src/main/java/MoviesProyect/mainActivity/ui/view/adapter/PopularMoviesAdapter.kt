package MoviesProyect.mainActivity.ui.view.adapter

import MoviesProyect.mainActivity.R
import MoviesProyect.mainActivity.data.model.Dmovie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PopularMoviesAdapter(private val moviesList: List<Dmovie>) :
    RecyclerView.Adapter<PopularMoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PopularMoviesViewHolder(
            layoutInflater.inflate(
                R.layout.card_data_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val item = moviesList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = moviesList.size

}