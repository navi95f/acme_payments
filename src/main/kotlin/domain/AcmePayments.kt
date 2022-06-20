package domain

import model.Day
import model.Schedule
import model.Shift

/**
 * Pseudo-controller class that contains the main functions of the project
 * */
class AcmePayments {

    var schedules: ArrayList<Schedule>? = null

    /**
     * @param data -> List containing the lines of the input data file
     *
     * Parses each object on the data list into a Schedule object and adds it to the Schedules list
     * */
     fun parseSchedules(data: ArrayList<String>){
         val schedules: ArrayList<Schedule> = ArrayList()
         var schedule: Schedule

         data.forEach{
            try{
                val formattedText = it.filter { char -> !char.isWhitespace() }
                val nameAndShifts = formattedText.split("=")
                val name = nameAndShifts[0]
                val strShifts = nameAndShifts[1].split(",")
                if(strShifts.isNotEmpty() && name.isNotEmpty()){
                    schedule = Schedule(name, parseShifts(strShifts))
                    schedules.add(schedule)
                }else{
                    println("Error: The following entry is not correctly formatted: [$it]")
                }
            }catch (e: Exception){
                println("Error: The following entry is not correctly formatted: [$it]")
                //e.printStackTrace()
            }
        }
         this.schedules = schedules
    }

    /**
     * @param strShifts -> list containing the day and hours worked by employee
     *
     * Parses the list of strings into Shift objects that will be added to the Schedule of each employee
     * */
    private fun parseShifts(strShifts:List<String>): ArrayList<Shift>{

        val shifts = ArrayList<Shift>()
        try {
            strShifts.forEach{
                val strDay = it.take(2)
                val hours = it.drop(2).split("-")
                val shift = Shift(strDay, hours[0],hours[1])
                if(shift.checkShift())
                    shifts.add(shift)
                else
                    println("Error: shift [$it] it's not correctly formatted " +
                            "\nContinuing with parsing shifts ...\n")
            }
        }catch (e: Exception){
            e.printStackTrace()
            println("Error: at parsing the shifts of the employee")
        }
        return shifts

    }


    /**
     * @return -> A String which indicates the amount to pay to each employee
     * */
    fun printPayments(): String{
        var output = ""
        this.schedules?.forEach{
            output+="The amount to pay ${it.employee} is: ${this.calculatePayment(it)} USD\n"
        }
        return output
    }


    //TODO Can be private, currently testing
    /**
     * @param schedule -> Schedule that contains the shifts used to calculate the amount to pay to an employee
     *
     * Goes through each shift and adds the amount to pay per shift to the total amount to pay to the employee
     * */
    fun calculatePayment(schedule: Schedule): Int{
        return if(schedule.shifts.isNotEmpty()){
            var payment = 0
            schedule.shifts.forEach{//Shift
                val time = it.beginsAt!!.hour
                for(i in 1..it.getShiftHours()){ //for each hour worked
                    time+1
                    payment+=getAmount(time, it.day!!)
                }
            }
            payment
        }else{
            println("The employee ${schedule.employee} does not have any shifts.")
            0
        }
    }

    //TODO Can be private, currently testing
    /**
     * @param time -> Represents a single hour of work
     * @param day -> Day in which the employee worked
     * @return -> the amount to pay according to the hour and day worked
     *
     * Calculates the amount to pay depending on the day and on each hour worked by the employee
     * */
    fun getAmount(time: Int, day: Day): Int{
        var amount = 0
        when(time){
            in 0 .. 9 ->{
                amount += if(day.isWeekDay()){
                    25
                }else{
                    30
                }
            }
            in 10 .. 18 ->{
                amount += if(day.isWeekDay()){
                    15
                }else{
                    20
                }
            }
            in 17 .. 24 ->{
                amount += if(day.isWeekDay()){
                    20
                }else{
                    25
                }
            }
        }
        return amount
    }
}