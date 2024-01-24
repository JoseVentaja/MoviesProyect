package MoviesProyect.mainActivity.ui.view

import MoviesProyect.mainActivity.R
import MoviesProyect.mainActivity.databinding.ActivityMainBinding
import MoviesProyect.mainActivity.ui.view.adapter.PopularMoviesAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this,manager.orientation)
        binding.recycerViewPopularMovies.layoutManager = manager
        binding.recycerViewPopularMovies.adapter = PopularMoviesAdapter(moviesList = emptyList())
        binding.recycerViewPopularMovies.addItemDecoration(decoration)
    }
}