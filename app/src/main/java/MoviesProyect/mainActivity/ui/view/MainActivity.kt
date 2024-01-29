package moviesProyect.mainActivity.ui.view

import moviesProyect.mainActivity.R
import moviesProyect.mainActivity.databinding.ActivityMainBinding
import moviesProyect.mainActivity.ui.view.fragments.PopularMoviesFragment
import moviesProyect.mainActivity.ui.viewModel.MovieListViewModel
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

    override fun onBackPressed() {
        var count = supportFragmentManager.backStackEntryCount
        if(count == 0){
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
    private fun configView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
         fragmentTransaction.replace(R.id.containerFragment, PopularMoviesFragment())
        fragmentTransaction.addToBackStack("replacement")
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