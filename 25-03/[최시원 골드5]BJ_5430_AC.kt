package org.example

import java.util.*
import kotlin.collections.ArrayDeque

fun main(args: Array<String>) {

    val T = readln().toInt()

    for(tc in 0 until T){
        val funcP = StringBuilder(readln())
        val n = readln().toInt()
        val st = StringTokenizer(readln(), "[],")

        val numList = ArrayDeque<Int>()

        for(i in 0 until n){
            numList.add(st.nextToken().toInt())
        }


        var isReverse = false
        var isError = false

        for(i in 0 until funcP.length){
            val order = funcP.get(i)

            if(order == 'R'){
                isReverse = !isReverse
            }else if(order == 'D'){
                if(numList.isEmpty()){
                    isError = true
                    break
                }
                if(!isReverse){
                    numList.removeFirst()
                }else{
                    numList.removeLast()
                }
            }
        }


        if(isError) println("error")
        else printNumList(numList, isReverse)


    }
}

fun printNumList(numList: ArrayDeque<Int>, isReverse: Boolean){
    val result = StringBuilder()
    result.append("[")
    while(numList.isNotEmpty()){
        if(!isReverse){
            result.append(numList.first())
            numList.removeFirst()
        }else{
            result.append(numList.last())
            numList.removeLast()
        }
        if(numList.isNotEmpty()) result.append(",")
    }
    result.append("]")
    println(result)
}