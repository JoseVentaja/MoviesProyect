package moviesProyect.mainActivity.utils




import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class DateTimeUtilsTest {


    @Test
    fun `getYearFromDateUsingSimpleDateFormat should return the correct year`() {
        // given
        val year = "2021"
        val inputDate = "${year}-01-30"
        // when
        val result = DateTimeUtils.getYearFromDateUsingSimpleDateFormat(inputDate)
        // then
        assertEquals(year, result)
    }
}