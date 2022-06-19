import data.FileReader
import domain.AcmePayments
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestAcmePayments {

    private val list = ArrayList<String>()

    @Test
    fun test_get_schedule_empty_list(){

//        val mockedList = mockk<ArrayList<String>>()
//        every { mockedList.iterator() } answers {list.iterator()}

        //list.add("RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00")

        val mockFileReader = mockk<FileReader>()
        every { mockFileReader.getContent("")} returns list

        Assertions.assertEquals(true, mockFileReader.getContent("").isEmpty())

        verify { mockFileReader.getContent("") }
    }

    @Test
    fun check_if_schedules_are_not_empty(){
        list.add("RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00")
        val acmePayments = AcmePayments()
        val schedules = acmePayments.getSchedule(list)
        Assertions.assertEquals(true, schedules.isNotEmpty())
    }
}