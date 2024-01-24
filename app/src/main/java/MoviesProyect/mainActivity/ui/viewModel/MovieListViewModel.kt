package MoviesProyect.mainActivity.ui.viewModel

import MoviesProyect.mainActivity.BuildConfig
import MoviesProyect.mainActivity.data.model.Dmovie
import MoviesProyect.mainActivity.domain.ApiService
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieListViewModel : ViewModel() {
    private var _moviesList = MutableLiveData<List<Dmovie>>()
    val listaPeliculas: LiveData<List<Dmovie>> = _moviesList
    val service = ApiService.RetrofitServiceFactoy.makeRetrofitService()

    fun getPopularMoviesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = service.listPopularMovies(BuildConfig.API_KEY)
            withContext(Dispatchers.Main) {
                _moviesList.value = response.results
            }
        }
    }
}