import data.FileReader
import domain.AcmePayments
import model.Day
import model.Schedule

fun printPayments(schedules: ArrayList<Schedule>){

    schedules.forEach{
        println("The amount to pay ${it.employee} is: ${it.calculatePayment()} USD")
    }
}


fun main(){


    val data = FileReader().getContent("data.txt")
    val schedules = AcmePayments().getSchedule(data)

    printPayments(schedules)

}

