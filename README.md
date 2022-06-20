# ACME PAYMENTS

Project created as a coding exercise for the Senior Mobile Software Engineer at IOET.

The project consists of a program that calculates de amount to pay each of its employees in accord to the hours they worked.

#### Input Example:
"RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00"

#### Output example:
"The amount to pay RENE is: 215 USD"

### Overview of the solution
The objective is to calculate the amount to pay to the employees of the company based on the number of worked hours.
For the calculation we take in consideration the following table:

|               | Monday - Friday | Saturday - Sunday | 
|:-------------:|:---------------:|:-----------------:|
| 00:01 - 09:00 |     25 USD      |      30 USD       |
| 09:01 - 18:00 |     15 USD      |      20 USD       |
| 18:01 - 00:00 |     20 USD      |      25 USD       |

Also the days of the week are represented as follows:

* MO: Monday
* TU: Tuesday
* WE: Wednesday
* TH: Thursday
* FR: Friday
* SA: Saturday
* SU: Sunday

As we can see the amount to pay depends on two variables: the hours in which the employee worked and if they worked on
a weekday or during the weekend.

The approach taken was to take the data as an 'Schedule' which consists of the name of the employee and one or more shifts. 
The shifts, on the other hand, have a day and the hours of the beginning and end of the shift. 

The project is mainly composed by the [Domain](./src/main/kotlin/domain) and [Model](./src/main/kotlin/model/) packages. 
The Model package contains the data structures
previously mentioned that can be represented as follows:

* [Schedule](./src/main/kotlin/model/Schedule.kt)
   * Employee (String)
   * [Shifts](./src/main/kotlin/model/Shift.kt)
     * [Day](./src/main/kotlin/model/Day.kt)
     * Start hour (LocalTime)
     * Finish hour (LocalTime)

On the Domain package we can find the [AcmePayments](./src/main/kotlin/domain/AcmePayments.kt) class which acts as
the controller for the program. This class contains the functions needed for parsing the input data into the 
data structures used. It also contains the functions that calculate the amount to pay each employee based on their 
shifts. 

For the calculations three things are taken into consideration: 
* The amount of hours worked.
* On which time frame each hour falls.
* If the day is a weekday or weekend.

after the calculations are completed, it returns the desired information in the following format (Example):

* The amount to pay RENE is: 215 USD

### How to run the project
* Check if you have installed at least Java 1.8 (If not, please install it)
* Download and install Intellij IDEA IDE. 
  * You can download it from https://www.jetbrains.com/idea/download
* Once installed, run the IDE an open the project.
  * Intellij IDEA will download the dependencies necessary to run the project, which are indicated on the gradle file. This wil take a few minutes.
* Once it finishes downloading the dependencies open the main.kt file, right click on it and select 'run'