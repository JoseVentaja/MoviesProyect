package MoviesProyect.mainActivity.data.model

data class RemoteResult(
    val page: Int,
    val results: List<Dmovie>,
    val total_pages: Int,
    val total_results: Int
)