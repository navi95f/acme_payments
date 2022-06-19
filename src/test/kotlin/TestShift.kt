import model.Shift
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestShift {

    @Test
    fun test_instance_of_shift(){
        val day = "MO"
        val startHour = "10:00"
        val finishHour = "12:00"
        val shift = Shift(day, startHour, finishHour)
        Assertions.assertNotNull(shift.day)
        Assertions.assertNotNull(shift.beginsAt)
        Assertions.assertNotNull(shift.endsAt)
    }
}