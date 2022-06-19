package model

class Schedule(
     val employee: String,
     val shifts: ArrayList<Shift>
) {

    fun calculatePayment(): Int{
        if(shifts.isNotEmpty()){
            var payment = 0
            shifts.forEach{//Shift
                val time = it.beginsAt!!.hour
                for(i in 1..it.getShiftHours()){ //for each hour worked
                    time+1
                    payment+=getAmount(time, it.day!!)
                }
            }
            return payment
        }else{
            println("The employee $employee does not have any shifts.")
            return 0
        }
    }

     fun getAmount(time: Int, day:Day): Int{
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