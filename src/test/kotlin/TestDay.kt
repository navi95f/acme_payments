import data.FileReader
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDay {

    @Test
    fun test_val_of_day(){
        val day = model.Day.valueOf("MO")
        Assertions.assertEquals(1, day.value)
    }

    @Test
    fun test_val_of_day_error(){
        val day = model.Day.valueOf("xu")
        Assertions.assertEquals(1, day.value)
    }


    @Test
    fun test_check_if_weekday(){
        val day = model.Day.valueOf("MO")
        Assertions.assertEquals(true, day.isWeekDay())
    }


}