package moviesProyect.mainActivity.ui.viewModel

import android.util.Log
import moviesProyect.mainActivity.data.model.Dmovie
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moviesProyect.mainActivity.BuildConfig
import moviesProyect.mainActivity.domain.ApiService


class MovieListViewModel : ViewModel() {
    private var _moviesList = MutableLiveData<List<Dmovie>>()
    val moviesList: LiveData<List<Dmovie>> = _moviesList
    val service = ApiService.RetrofitServiceFactoy.makeRetrofitService()
    lateinit var currentMovie: Dmovie
    private var _favouriteMoviesList = MutableLiveData<List<Dmovie>>()
    val favouriteMoviesList: LiveData<List<Dmovie>> = _favouriteMoviesList
    private var _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isLoading : LiveData<Boolean> = _isLoading


     fun getPopularMoviesList(): List<Dmovie> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
            _isLoading.postValue(true)
            val response = service.listPopularMovies("${BuildConfig.API_KEY}")
            withContext(Dispatchers.Main) {
                _moviesList.value = response?.results
                _isLoading.postValue(false)
            }
                //TODO Aqui podría poner un mensaje más personalizado y controlar el tipo de excepción mostrando un toast. Por ejemplo fallo conexión
                 //TODO (msg = Revise su conexión a internet e intente refrescar el listado)
            } catch (e: Exception) {
                _isLoading.postValue(false)
                Log.e("getPopularMoviesListError", "Error: ${e.message}", e)
            }
        }
        return _moviesList.value?.let { return it } ?: return emptyList()
    }

    fun getFavouritesMovieList(): List<Dmovie> {
        favouriteMoviesList.value?.let { return it } ?: return emptyList()
    }

    fun addOrRemoveToFavouriteFilms(movie: Dmovie) {
        updatePopularList(movie)
        if(!movie.isFavourite){
            removeFromFavouriteList(movie)
            return
        }
        val currentList = _favouriteMoviesList.value ?: emptyList()
        val updatedList = currentList + movie
        _favouriteMoviesList.value = updatedList
        removeFromPopularMovies()
    }

    private fun updatePopularList(movie: Dmovie) {
        val auxiliarList = _moviesList.value
        var index = auxiliarList?.indexOfFirst { it.id == movie.id } ?: -1
        if (index != -1) {
            auxiliarList?.get(index)?.isFavourite = movie.isFavourite
            _moviesList.value = auxiliarList
        }
    }

     fun removeFromFavouriteList(movie: Dmovie) {
        val auxiliarList = _favouriteMoviesList.value?.toMutableList()
        var index = auxiliarList?.indexOfFirst { it.id == movie.id } ?: -1
        if (index != -1) {
            auxiliarList?.removeAt(index)
            _favouriteMoviesList.value = auxiliarList
        }
    }

    private fun removeFromPopularMovies() {
        //TODO tengo que eliminar la película de la lista de populares ya que pasa a ser de mis favoritas
    }

}