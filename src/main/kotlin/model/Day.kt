package model

enum class Day(val value: Int) {
    MO(1),
    TU(2),
    WE(3),
    TH(4),
    FR(5),
    SA(6),
    SU(7);

    fun isWeekDay(): Boolean{
        return this.value in 1..5
    }
}


