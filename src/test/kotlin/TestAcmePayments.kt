import data.FileReader
import domain.AcmePayments
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import model.Day
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TestAcmePayments {

    private val list = ArrayList<String>()

    @MockK(relaxUnitFun = true)
    lateinit var day: Day

    private val acmePayments = AcmePayments()

    private val dataSuccess = "RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00"
    private val datafailDay = "RENE=MO10:00-12:00, T10:00-12:00, TH01:00-03:00,SA14:00-18:00,SU20:00-21:00"
    private val datafailTime = "RENE=MO10:00-12:00, TU1:00-12:00, TH01:00-03:00,SA14:00-18:00,SU20:00-21:00"
    private val dataNoName = "=MO10:00-12:00, TU10:00-12:00, TH01:00-03:00,SA14:00-18:00,SU20:00-21:00"




    @Test
    fun test_parse_schedule_empty_list(){
//        val mockedList = mockk<ArrayList<String>>()
//        every { mockedList.iterator() } answers {list.iterator()}

        //list.add("RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00")

        val mockFileReader = mockk<FileReader>()
        every { mockFileReader.getContent("")} returns list

        Assertions.assertEquals(true, mockFileReader.getContent("").isEmpty())

        verify { mockFileReader.getContent("") }

        acmePayments.parseSchedules(mockFileReader.getContent(""))

        Assertions.assertEquals(true, acmePayments.schedules?.isEmpty())
    }

    @Test
    fun check_if_schedules_are_not_empty(){
        list.add(dataSuccess)
         acmePayments.parseSchedules(list)
        Assertions.assertEquals(true, acmePayments.schedules?.isNotEmpty())
    }

    @Test
    fun test_case_no_name(){
        list.add(dataNoName)
        acmePayments.parseSchedules(list)
        Assertions.assertEquals(true, acmePayments.schedules?.isNotEmpty())
    }

    @Test
    fun test_get_amount_to_pay(){
        every { day.value } returns 1
        every { day.isWeekDay() } returns true
        Assertions.assertEquals(25, acmePayments.getAmount(8, day))

    }

    @Test
    fun test_calculate_payment(){
        list.add(dataSuccess)
        acmePayments.parseSchedules(list)
        Assertions.assertEquals(215, acmePayments.calculatePayment(acmePayments.schedules!![0]))
    }
}