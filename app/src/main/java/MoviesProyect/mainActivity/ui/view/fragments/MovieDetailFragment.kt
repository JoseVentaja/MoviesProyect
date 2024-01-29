package moviesProyect.mainActivity.ui.view.fragments

import moviesProyect.mainActivity.databinding.MovieDetailFragmentBinding
import moviesProyect.mainActivity.ui.viewModel.MovieListViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import moviesProyect.mainActivity.BuildConfig
import moviesProyect.mainActivity.Constants

class MovieDetailFragment : Fragment() {

    private lateinit var binding: MovieDetailFragmentBinding
    private val movieListViewModel: MovieListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        configView()
        return binding.root
    }

    private fun configView() {
        binding.textDescription.text = movieListViewModel.currentMovie.overview
        val glideUrl = GlideUrl("${Constants.imageBaseURL}${movieListViewModel.currentMovie.poster_path}") {
            mapOf(
                Pair(
                    "Authorization",
                    "Authorization ${BuildConfig.API_KEY}"
                )
            )
        }

        Glide.with(binding.actorPhoto.context)
            .load(glideUrl)
            .into(binding.actorPhoto)

        Glide.with(binding.videoMovie.context)
            .load(glideUrl)
            .into(binding.videoMovie)

    }

}