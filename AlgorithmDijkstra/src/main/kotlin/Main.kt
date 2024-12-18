package org.example

import java.util.*
// Data class representing an edge between cities (index and cost)
data class Edge(val to: Int, val cost: Int)
// Function to execute algorithm
fun dijkstra(startPoint: Int, numberOfCities: Int, graph: Array<MutableList<Edge>>): IntArray {
    val distance = IntArray(numberOfCities) { Int.MAX_VALUE } // Array of distances from the start city(point) to other, initialized to the maximum value.
    val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second }) // Queue to select next city with minimum cost.
    distance[startPoint] = 0 // Starting city has 0 point distance.
    priorityQueue.add(Pair(startPoint, 0)) // Add the starting city to queue.
    while (priorityQueue.isNotEmpty()) { // Process queue while it's not empty.
        val (current, currentCost) = priorityQueue.poll() // Select city with min cost.
        if (currentCost > distance[current]) continue //  If Current cost is greater that founded cost, skip it.
        for (edge in graph[current]) { // Iterate thought all neighbor of the current city.
            val newCost = currentCost + edge.cost // Calculate new cost of the path through the current city.
            if (newCost < distance[edge.to]) { // If current cost is smaller than founded, update the distance and add it to the queue.
                distance[edge.to] = newCost
                priorityQueue.add(Pair(edge.to, newCost))
            }
        }
    }
    return distance // Return result array of distances.
}

fun main() {
    val reader = System.`in`.bufferedReader() // read input data.
    val testCases = reader.readLine().toInt() // read the number of test cases.
    // for each test case
    repeat(testCases) {
        val numberOfCity = reader.readLine().toInt() // Read number of cities.
        val cityIndex = mutableMapOf<String, Int>() // Map to store city indices.
        val graphToStoreConnection = Array(numberOfCity) { mutableListOf<Edge>() }// graph to store the list of connections (ways) for each city.

        // Read data from each city.
        for (city in 0 until numberOfCity) {
            val cityName = reader.readLine() // Read city name.
            cityIndex[cityName] = city // Add the city to the map.
            val p = reader.readLine().toInt() // Read the number of neighbors for the current city.
            repeat(p) {
                val (neighbor, cost) = reader.readLine().split(" ").map { it.toInt() } //  Read the index of the neighboring city and the cost of the connection
                // Add the connection to the graph
                graphToStoreConnection[city].add(Edge(neighbor - 1, cost))// Subtract 1 from the index for 0-based indexing
            }
        }

        val r = reader.readLine().toInt() // Read numbers of queries to find path.
        val results = mutableListOf<Int>() // List to store result

        repeat(r) {
            val (nameOfFirstCity, nameOfSecondCity) = reader.readLine().split(" ") // Read 2 cities for which shortest way is found.
            //  Get indices of the city from the map
            val startIndex = cityIndex[nameOfFirstCity]!!
            val endIndex = cityIndex[nameOfSecondCity]!!

            val distances = dijkstra(startIndex, numberOfCity, graphToStoreConnection) // Call dijkstr'a algorithm to find the shortest path
            results.add(distances[endIndex]) // Add result for the current query ( the path cost between 2 cities0
        }


        results.forEach { println(it) }// Output results for all queries
        if (testCases > 1) println() // If there are more than one test cases , print empty line after the result
    }
}
