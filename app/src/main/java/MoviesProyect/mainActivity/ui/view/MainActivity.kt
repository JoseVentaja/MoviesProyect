package MoviesProyect.mainActivity.ui.view

import MoviesProyect.mainActivity.R
import MoviesProyect.mainActivity.databinding.ActivityMainBinding
import MoviesProyect.mainActivity.ui.view.fragments.PopularMoviesFragment
import MoviesProyect.mainActivity.ui.viewModel.MovieListViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val movieListViewModel: MovieListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configView()

    }

    private fun configView() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
         fragmentTransaction.replace(R.id.containerFragment, PopularMoviesFragment())
         fragmentTransaction.commit()
        //initRecyclerView()
        //setUpObservers()
        //configListeners()
    }

   /* private fun configListeners() {
        binding.favouriteButton.setOnClickListener{
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.favouriteListFragment, FavouriteMoviesFragment())
            fragmentTransaction.commit()
        }
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
        binding.recyclerViewPopularMovies.layoutManager = manager
        moviesAdapter = PopularMoviesAdapter(emptyList())
        binding.recyclerViewPopularMovies.adapter = moviesAdapter
        binding.recyclerViewPopularMovies.addItemDecoration(decoration)
    }*/
}