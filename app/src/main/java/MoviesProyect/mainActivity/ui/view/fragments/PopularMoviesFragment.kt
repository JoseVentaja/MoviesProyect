package moviesProyect.mainActivity.ui.view.fragments

import moviesProyect.mainActivity.R
import moviesProyect.mainActivity.data.model.Dmovie
import moviesProyect.mainActivity.databinding.FragmentPopularMoviesBinding
import moviesProyect.mainActivity.ui.view.adapter.PopularMoviesAdapter
import moviesProyect.mainActivity.ui.viewModel.MovieListViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

class PopularMoviesFragment : Fragment(), PopularMoviesAdapter.OnClickListener {
    private lateinit var binding: FragmentPopularMoviesBinding
    private val popularMovieListViewModel: MovieListViewModel by activityViewModels()
    private lateinit var moviesAdapter: PopularMoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        configView()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularMovieListViewModel.getPopularMoviesList()
    }
    private fun configView() {
        initRecyclerView()
        setUpObservers()
        configListeners()
    }

    private fun configListeners() {
        binding.favouriteButton.setOnClickListener {
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.containerFragment, FavouriteMoviesFragment())
            fragmentTransaction.addToBackStack("replacement")
            fragmentTransaction.commit()
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            popularMovieListViewModel.getPopularMoviesList()
            binding.swipeRefreshLayout.isRefreshing = false
        }
       configSearchViewListener()
    }

    private fun configSearchViewListener() {
        binding.searchViewMovies.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchText: String?): Boolean {
                popularMovieListViewModel.moviesList.value?.let { movieList ->
                    val filteredList = movieList.filter { movie ->
                        movie.original_title.contains(searchText.orEmpty(), ignoreCase = true)

                    }
                    moviesAdapter.updateList(filteredList)
                }
                return true
            }
        })
    }


    fun setUpObservers() {
        popularMovieListViewModel.moviesList.observe(this.viewLifecycleOwner) {
            moviesAdapter.moviesList = it
            moviesAdapter.notifyDataSetChanged()
        }
        popularMovieListViewModel.isLoading.observe(this.viewLifecycleOwner) { isLoading ->
            binding.swipeRefreshLayout.isRefreshing = isLoading
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, manager.orientation)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let {drawable -> decoration.setDrawable(drawable) }
        binding.recyclerViewPopularMovies.layoutManager = manager
        moviesAdapter = PopularMoviesAdapter(emptyList())
        binding.recyclerViewPopularMovies.adapter = moviesAdapter
        binding.recyclerViewPopularMovies.addItemDecoration(decoration)
        moviesAdapter.addListener(this)
    }

    override fun addOrRemoveFavourite(movie: Dmovie) {
        popularMovieListViewModel.addOrRemoveToFavouriteFilms(movie)
    }

    override fun accessToMovieDetail(movie: Dmovie) {
        popularMovieListViewModel.currentMovie = movie
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.containerFragment, MovieDetailFragment())
        fragmentTransaction.addToBackStack("replacement")
        fragmentTransaction.commit()
    }


}