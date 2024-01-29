package moviesProyect.mainActivity.ui.view.fragments

import moviesProyect.mainActivity.R
import moviesProyect.mainActivity.data.model.Dmovie
import moviesProyect.mainActivity.databinding.FavouriteListFragmentBinding
import moviesProyect.mainActivity.ui.view.adapter.PopularMoviesAdapter
import moviesProyect.mainActivity.ui.viewModel.MovieListViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

class FavouriteMoviesFragment : Fragment(), PopularMoviesAdapter.OnClickListener {
    private lateinit var binding: FavouriteListFragmentBinding
    private val favouriteMovieListViewModel: MovieListViewModel by activityViewModels()
    private lateinit var moviesAdapter: PopularMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavouriteListFragmentBinding.inflate(inflater, container, false)
        initRecyclerView()
        setUpObservers()
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setUpObservers() {
        favouriteMovieListViewModel.favouriteMoviesList.observe(this.viewLifecycleOwner) {
            moviesAdapter.moviesList = it
            moviesAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, manager.orientation)
        binding.recyclerViewFavouriteMovies.layoutManager = manager
        moviesAdapter = PopularMoviesAdapter(favouriteMovieListViewModel.getFavouritesMovieList())
        binding.recyclerViewFavouriteMovies.adapter = moviesAdapter
        binding.recyclerViewFavouriteMovies.addItemDecoration(decoration)
        moviesAdapter.addListener(this)
    }

    override fun addOrRemoveFavourite(movie: Dmovie) {
        favouriteMovieListViewModel.removeFromFavouriteList(movie)
    }

    override fun accessToMovieDetail(movie: Dmovie) {
        favouriteMovieListViewModel.currentMovie = movie
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.containerFragment, MovieDetailFragment())
        fragmentTransaction.addToBackStack("replacement")
        fragmentTransaction.commit()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().supportFragmentManager.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}