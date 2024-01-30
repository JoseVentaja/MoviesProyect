package moviesProyect.mainActivity.utils


import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

//Esta función devuelve el año siempre y cuando cumpla el patrón establecido, cuidado con eso
    //TODO  Lo suyo sería una función que aunque cambiase el json de la api y por lo tanto el formato de la fecha siguiese funcionando
    //Además esto haría que fuese más reutilizable a la larga
    fun getYearFromDateUsingSimpleDateFormat(date: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val parsedDate = dateFormat.parse(date)
        val calendar = Calendar.getInstance()
        calendar.time = parsedDate ?: Date()
        return calendar.get(Calendar.YEAR).toString()
    }
}