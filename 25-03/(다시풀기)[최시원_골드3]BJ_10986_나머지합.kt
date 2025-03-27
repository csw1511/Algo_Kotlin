package org.example

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

fun main(args: Array<String>) {
    val (N, M) = readln().split(" ").map{it.toInt()}

    val st = StringTokenizer(readln())

    val prefixSum = LongArray(N+1)
    val cnt = LongArray(M)

    for(i in 1..N){
        prefixSum[i] = (prefixSum[i-1] + st.nextToken().toLong()) % M
        cnt[prefixSum[i].toInt()]++
    }


    var ans = cnt[0]

    for(i in 0 until M){
        ans += cnt[i] * (cnt[i]-1) /2
    }


    println(ans)

}