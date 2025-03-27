package org.example

import java.util.*
import kotlin.math.abs

fun main(args: Array<String>) {
    val N = readln().toInt()

    val st = StringTokenizer(readln())

    val arr = Array(N){0L}

    for(i in 0 until N){
        arr[i] = st.nextToken().toLong()
    }

    arr.sort()


    var left:Int
    var right:Int
    var ans:Long = Long.MAX_VALUE

    var bestMiddle:Int = 0
    var bestLeft:Int = 0
    var bestRight:Int = 0

    for(i in 0 until N-2){
        val nowMiddle = arr[i]

        left = i+1
        right = N-1


        while(left < right){
            val sum = nowMiddle + arr[left] + arr[right]
            val absSum = abs(sum)

            if(absSum < ans) {
                ans = absSum
                bestMiddle = i
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


    }

    println("${arr[bestMiddle]} ${arr[bestLeft]} ${arr[bestRight]}")

}
