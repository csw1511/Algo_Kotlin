package org.example

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.HashMap


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val N = br.readLine().toInt()
    val arr = arrayListOf<Int>()
    val addComb = mutableSetOf<Int>()
    var answer = 0;

    for(i in 0..<N){
        arr.add(br.readLine().toInt())
    }

    arr.sort()

    for(i in 0..<N){
        for(j in 0..<N){
            addComb.add(arr[i] + arr[j])
        }
    }


    for(i in 0..<N){
        for(j in 0..<i){
            val target = arr[i] - arr[j]
            if(addComb.contains(target)){
                answer = arr[i]
            }
        }
    }

    println(answer)
    

}

