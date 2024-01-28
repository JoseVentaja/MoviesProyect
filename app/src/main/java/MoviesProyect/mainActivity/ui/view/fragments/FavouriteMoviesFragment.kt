package MoviesProyect.mainActivity.ui.view.fragments

import MoviesProyect.mainActivity.databinding.FavouriteListFragmentBinding
import MoviesProyect.mainActivity.ui.view.adapter.PopularMoviesAdapter
import MoviesProyect.mainActivity.ui.viewModel.MovieListViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

class FavouriteMoviesFragment : Fragment() {
    private lateinit var binding: FavouriteListFragmentBinding
    private  val favouriteMovieListViewModel : MovieListViewModel by activityViewModels()
    private lateinit var moviesAdapter: PopularMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavouriteListFragmentBinding.inflate(inflater, container, false)
        initRecyclerView()
        setUpObservers()
        return binding.root
    }

    private fun setUpObservers(){
        favouriteMovieListViewModel.favouriteMoviesList.observe(this.viewLifecycleOwner) {
            moviesAdapter.moviesList = it
            moviesAdapter.notifyDataSetChanged()
        }
    }
    private fun initRecyclerView() {
        val manager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context,manager.orientation)
        binding.recyclerViewFavouriteMovies.layoutManager = manager
        moviesAdapter = PopularMoviesAdapter(favouriteMovieListViewModel.getFavouritesMovieList())
        binding.recyclerViewFavouriteMovies.adapter = moviesAdapter
        binding.recyclerViewFavouriteMovies.addItemDecoration(decoration)
    }
}