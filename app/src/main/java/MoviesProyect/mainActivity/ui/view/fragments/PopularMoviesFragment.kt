package MoviesProyect.mainActivity.ui.view.fragments

import MoviesProyect.mainActivity.R
import MoviesProyect.mainActivity.databinding.FragmentPopularMoviesBinding
import MoviesProyect.mainActivity.ui.view.adapter.PopularMoviesAdapter
import MoviesProyect.mainActivity.ui.viewModel.MovieListViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

class PopularMoviesFragment : Fragment() {
    private lateinit var binding : FragmentPopularMoviesBinding
    private  val popularMovieListViewModel : MovieListViewModel by activityViewModels()
    private lateinit var moviesAdapter: PopularMoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        popularMovieListViewModel.getPopularMoviesList()
        configView()
        return binding.root
    }

    private fun configView() {

        initRecyclerView()
        setUpObservers()
        configListeners()
    }

    private fun configListeners() {
        binding.favouriteButton.setOnClickListener{
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.containerFragment, FavouriteMoviesFragment())
            fragmentTransaction.commit()
        }
    }

    fun setUpObservers(){
        popularMovieListViewModel.moviesList.observe(this.viewLifecycleOwner) {
            moviesAdapter.moviesList = it
            moviesAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context,manager.orientation)
        binding.recyclerViewPopularMovies.layoutManager = manager
        moviesAdapter = PopularMoviesAdapter(emptyList())
        binding.recyclerViewPopularMovies.adapter = moviesAdapter
        binding.recyclerViewPopularMovies.addItemDecoration(decoration)
    }
}