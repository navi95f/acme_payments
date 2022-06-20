package model

/**
 * Data class that represents the input of data
 * @param employee -> Name of the employee
 * @param shifts -> Represent the days and hours that the employee worked
 * */
data class Schedule(
     val employee: String,
     val shifts: ArrayList<Shift>
)