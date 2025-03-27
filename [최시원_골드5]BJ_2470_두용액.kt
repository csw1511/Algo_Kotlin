package org.example

import java.util.*

fun main(args: Array<String>) {
    val N = readln().toInt()

    val st = StringTokenizer(readln())

    val arr = Array(N){0L}

    for(i in 0 until N){
        arr[i] = st.nextToken().toLong()
    }

    arr.sort()


    var left = 0
    var right = N-1
    var ans = Long.MAX_VALUE

    var bestLeft = 0
    var bestRight = 0

    while(left < right){
        val sum = arr[left] + arr[right]
        val absSum = Math.abs(sum)

        if(absSum < ans) {
            ans = absSum
            bestLeft = left
            bestRight = right
        }

        if(sum < 0){
            left++
        }
        else{
            right--
        }
    }
    println("${arr[bestLeft]} ${arr[bestRight]}")

}
