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
    val moviesList: LiveData<List<Dmovie>> = _moviesList
    val service = ApiService.RetrofitServiceFactoy.makeRetrofitService()
    private var _currentMovie = MutableLiveData<Dmovie>()
    val currentMovie : LiveData<Dmovie> = _currentMovie
    private var _favouriteMoviesList = MutableLiveData<List<Dmovie>>()
    val favouriteMoviesList: LiveData<List<Dmovie>> = _favouriteMoviesList


    fun getPopularMoviesList() : List<Dmovie> {
        viewModelScope.launch(Dispatchers.IO) {
            val response = service.listPopularMovies(BuildConfig.API_KEY)
            withContext(Dispatchers.Main) {
                _moviesList.value = response.results
            }
        }
        return _moviesList.value?.let { return it } ?: return emptyList()
    }

    fun getFavouritesMovieList() : List<Dmovie> {
        favouriteMoviesList.value?.let { return it } ?: return emptyList()
    }

    fun addToFavouriteFilms(movie : Dmovie) {
        val currentList = _favouriteMoviesList.value ?: emptyList()
        val updatedList = currentList + movie
        _favouriteMoviesList.value = updatedList
        removeFromPopularMovies()
    }

    private fun removeFromPopularMovies() {
    //TODO tengo que eliminar la película de la lista de populares ya que pasa a ser de mis favoritas
    }

    fun accesToMovieDetail() {
        TODO("Not yet implemented")
    }

}