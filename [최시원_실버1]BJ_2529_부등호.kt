package org.example

import java.util.*

fun main() {
    val k = readln().toInt()
    val arr = readln().split(" ")

    val result = IntArray(k+1)
    val visited = BooleanArray(10)

    permu(0, k+1, arr, result, visited)

    println(maxString)
    println(minString)
}

var maxVal = Long.MIN_VALUE
var minVal = Long.MAX_VALUE
var maxString = ""
var minString = ""

fun permu(index:Int, k:Int, arr:List<String>, result:IntArray, visited:BooleanArray){
    if(index == result.size){
        val sb = StringBuilder()
        for(i in result){
            sb.append(i)
        }
        val nowResultVal = sb.toString().toLong()

        if(maxVal < nowResultVal){
            maxVal = nowResultVal
            maxString = sb.toString()
        }
        if(minVal > nowResultVal){
            minVal = nowResultVal
            minString = sb.toString()
        }

        return

    }

    for(i in 0 ..< 10){
        if(visited[i]) continue
        if(index >= 1){
            if(arr[index-1] == "<" && result[index-1] > i) continue
            if(arr[index-1] == ">" && result[index-1] < i) continue
        }

        result[index] = i
        visited[i] = true
        permu(index+1, k, arr, result, visited)
        visited[i] = false
    }

}