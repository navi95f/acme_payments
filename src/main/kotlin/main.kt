import data.FileReader
import domain.AcmePayments


/**
 * Main function
 * */
fun main(){
    val payments = AcmePayments()
    val data = FileReader().getContent("data.txt")

    payments.parseSchedules(data)

    println(payments.printPayments())

}

