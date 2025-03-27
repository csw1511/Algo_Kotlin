package org.example

import java.util.*

fun main(args: Array<String>) {
    var st = StringTokenizer(readln())

    val N = st.nextToken().toInt()
    val S = st.nextToken().toInt()

    st = StringTokenizer(readln())

    val prefixSum = IntArray(N+1)

    for(i in 1..N){
        prefixSum[i] = prefixSum[i-1] + st.nextToken().toInt()
    }

    var left = 0
    var right = 1

    var ans = Int.MAX_VALUE

    var bestLeft = 0
    var bestRight = 0

    while(left < right && left in 0..N && right in 0..N){
        var sum = prefixSum[right] - prefixSum[left]
//        println("${left} ${right} $sum")

        if(sum >= S){
            if(ans > right - left){
                bestLeft = left
                bestRight = right
                ans = right - left
            }
            left += 1
        }
        else{
            right += 1
        }

    }

//    println("${bestLeft} ${bestRight}")


    println(bestRight - bestLeft)






}
