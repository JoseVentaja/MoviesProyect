package MoviesProyect.mainActivity.ui.view

import MoviesProyect.mainActivity.R
import MoviesProyect.mainActivity.data.model.Dmovie
import MoviesProyect.mainActivity.databinding.ActivityMainBinding
import MoviesProyect.mainActivity.ui.view.adapter.PopularMoviesAdapter
import MoviesProyect.mainActivity.ui.view.adapter.PopularMoviesViewHolder
import MoviesProyect.mainActivity.ui.viewModel.MovieListViewModel
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var popularMovieListViewModel : MovieListViewModel
    private lateinit var moviesAdapter: PopularMoviesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         popularMovieListViewModel = MovieListViewModel();
        popularMovieListViewModel.getPopularMoviesList()
        initRecyclerView()
        setUpObservers()
    }

    fun setUpObservers(){
        popularMovieListViewModel.moviesList.observe(this) {
                    moviesAdapter.moviesList = it
                    moviesAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this,manager.orientation)
        binding.recycerViewPopularMovies.layoutManager = manager
        moviesAdapter = PopularMoviesAdapter(emptyList())
        binding.recycerViewPopularMovies.adapter = moviesAdapter
        binding.recycerViewPopularMovies.addItemDecoration(decoration)
    }
}