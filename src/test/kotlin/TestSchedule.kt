import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import model.Day
import model.Schedule
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TestSchedule {


    @MockK(relaxUnitFun = true)
    lateinit var day: Day

    @Test
    fun test_get_amount_to_pay(){

        val schedule = Schedule("TEST", ArrayList())

        every { day.value } returns 1
        every { day.isWeekDay() } returns true
        Assertions.assertEquals(25, schedule.getAmount(8, day))


    }
}