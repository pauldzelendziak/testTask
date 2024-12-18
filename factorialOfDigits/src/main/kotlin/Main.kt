package org.example

import java.math.BigInteger

fun main() {
    var factorial = BigInteger.ONE// Use BigInteger.ONE for initialize the starting value of the factorial.
    for (number in 1..100){ //The loop iterates through numbers from 1 to 100 and at each step the current factorial is multiplied by the number.
        factorial *= BigInteger.valueOf(number.toLong()) // Multiply each number by the current factorial.
    }
    val factorialString = factorial.toString()// Convert factorial to a string.
    var sumOfDigits = 0 // Variable to hold the sum of all the digits of the factorial.
    for (char in factorialString){ // The for loop iterates thought each character char in the string factorialString.
        sumOfDigits += char.toString().toInt() // Convert the character to an integer, then add integer value to  sumOfDiggits variable, accumulating sum of the digits as a loop progress.
    }
    println(sumOfDigits) // Print the result which is the sum of the digits of the factorial.
}

