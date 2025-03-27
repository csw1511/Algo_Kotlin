package org.example

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

fun main(args: Array<String>) {
    val st = StringTokenizer(readln())

    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()

    val dp = Array(K+1) {IntArray(N+1) { 1 } }

    for(i in 2..K){
        for(j in 1..N){
            dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1_000_000_000
        }
    }

    println(dp[K][N])

}