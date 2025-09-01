package org.example

import java.io.File
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import java.util.Date
import kotlin.time.Duration.Companion.milliseconds


fun main() {
    val json = File(
        "/Users/israellacerdagomessantos/IdeaProjects/SortBenchmark/src/main/kotlin/input.json"
    ).readText()

    val arr = Json.decodeFromString<Array<String>>(json)
    val arr2 = Json.decodeFromString<Array<String>>(json)
    val arr3 = Json.decodeFromString<Array<String>>(json)


    val beforeInsertion = Date().time.milliseconds
    insertionSort(arr)
    val afterInsertion = Date().time.milliseconds
    println("Insertion Sort: ${afterInsertion - beforeInsertion}")

    val beforeBubble = Date().time.milliseconds
    bubbleSort(arr2)
    val afterBubble = Date().time.milliseconds
    println("Bubble Sort: ${afterBubble - beforeBubble}")

    val beforeQuick = Date().time.milliseconds
    quickSortRecursive(arr3)
    val afterQuick = Date().time.milliseconds
    println("Quick Sort: ${afterQuick - beforeQuick}")




}

fun bubbleSort(array: Array<String>) {
    for (i in 0..array.lastIndex) {
        var swapped = false
        for (j in 0..array.lastIndex - 1 - i) {
            if (array[j] > array[j + 1]) {
                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
                swapped = true
            }
        }
        if (!swapped) {
            return@bubbleSort
        }
    }
}

fun insertionSort(array: Array<String>) {
    for (k in 1..array.lastIndex) {
        for (j in k downTo 1) {
            if (array[j] < array[j - 1]) {
                val temp = array[j - 1]
                array[j - 1] = array[j]
                array[j] = temp
            }
        }
    }
}

fun quickSortRecursive(arr: Array<String>, begin: Int = 0, end: Int = arr.lastIndex) {
    if (begin < end) {
        var i = begin - 1
        for (current in begin..end) {
            if (arr[current] > arr[end]) {
                continue
            }
            i++
            if (current > i) {
                val swap = arr[i]
                arr[i] = arr[current]
                arr[current] = swap
            }
        }
        quickSortRecursive(arr, begin, i - 1)
        quickSortRecursive(arr, i + 1, end)
    }
}


