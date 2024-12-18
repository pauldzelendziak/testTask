package org.example

// Function to generate balanced parentheses combinations.
fun generateParentheses(number: Int): List<String> {
    val result = mutableListOf<String>() // List to store valid parentheses combinations.
    // Recursive function to generate valid parentheses combinations.
    fun generate(current: String, openParenthese: Int, closeParenthese: Int) {
        // If current string has reached 2 * number, we found fair so add it to result.
        if (current.length == 2 * number) {
            result.add(current)
            return
        }
        // If the number of open parentheses less than number, then add open parentheses.
        if (openParenthese < number)
            generate(current + "(", openParenthese + 1, closeParenthese) // Add opening parentheses.
        // If the number of close parentheses less than number, then add close parentheses.
        if (closeParenthese < openParenthese)
            generate(current + ")", openParenthese, closeParenthese + 1) // Add closing parentheses.
    }
    // Start recursive generation with 0 opened and 0 closed parentheses.
    generate("", 0, 0)
    // Return list of all valid combinations.
    return result
}

fun main() {
    println("Enter the number of pairs of parentheses (N):") // Prompt the user to enter number.
    val number = readLine()!!.toInt() // Read and convert input value to an integer.
    // Check if the input number is non-negative.
    if (number > 0) {
        val parenthesesList = generateParentheses(number) // Generate list of valid parentheses combinations which based on input integer.
        println("Valid parentheses expressions:") // Print the generated valid expressions.
        parenthesesList.forEach(::println) // Print each valid combination.
    } else {
        println("Enter non-negative integer. Or integer bigger than 0") // If the number is negative or lower than 0, prompt the user to enter valid number.
    }
}