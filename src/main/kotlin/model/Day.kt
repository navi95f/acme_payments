package model

/**
 * Enum class that represent the days of the week that the employee can work
 * */
enum class Day(val value: Int) {
    MO(1),
    TU(2),
    WE(3),
    TH(4),
    FR(5),
    SA(6),
    SU(7);

    /**
     * @return -> Boolean that indicates if the instance of Day is a weekday or not
     * */
    fun isWeekDay(): Boolean{
        return this.value in 1..5
    }
}


