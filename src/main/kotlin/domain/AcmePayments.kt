package domain

import model.Schedule
import model.Shift

class AcmePayments {

     fun getSchedule(data: ArrayList<String>): ArrayList<Schedule>{
         val schedules: ArrayList<Schedule> = ArrayList()
         var schedule: Schedule

         data.forEach{
            try{
                val nameAndShifts = it.split("=")
                val name = nameAndShifts[0]
                val strShifts = nameAndShifts[1].split(",")
                if(strShifts.isNotEmpty()){
                    schedule = Schedule(name, getShift(strShifts))
                    schedules.add(schedule)
                }else{
                    println("No shifts found for employee $name")
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
         return schedules
    }

    private fun getShift(strShifts:List<String>): ArrayList<Shift>{

        val shifts = ArrayList<Shift>()
        try {
            strShifts.forEach{
                val strDay = it.take(2)
                val hours = it.drop(2).split("-")
                val shift = Shift(strDay, hours[0],hours[1])
                if(shift.checkShift())
                    shifts.add(shift)
                else
                    println("Error: shift [$it] it's not correctly formatted")
            }
        }catch (e: Exception){
            e.printStackTrace()
            println("Error: at parsing the shifts of the employee")
        }
        return shifts

    }
}