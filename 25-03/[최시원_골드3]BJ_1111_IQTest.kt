package org.example

import java.util.*
import kotlin.collections.ArrayDeque

fun main(args: Array<String>) {

    val N = readln().toInt()

    val st = StringTokenizer(readln())

    var arr = IntArray(N)

    for(i in 0 until N){
        arr[i] = st.nextToken().toInt()
    }

    if(N == 1){
        println("A")
        return
    }else if(N == 2){
        if(arr[0] == arr[1]){
            println(arr[0])
        }else{
            println("A")
        }
        return
    }else{
        var a:Int = 0
        if(arr[0] != arr[1]){
            a = (arr[2] - arr[1]) / (arr[1] - arr[0])
        }
        var b = arr[1] - arr[0] * a

        var expect = 0
        for(i in 0 until N-1){
            expect = arr[i] * a + b
            if(arr[i+1] != expect){
                println("B")
                return

            }
        }

        println(arr[N-1]*a+b)
    }



}
