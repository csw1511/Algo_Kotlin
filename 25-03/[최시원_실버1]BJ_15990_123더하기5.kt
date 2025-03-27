package org.example

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

fun main(args: Array<String>) {
    val tc = readln().toInt()

    val mod = 1_000_000_009
    val dp = Array(100001){LongArray(3)}

    dp[1][0] = 1
    dp[2][1] = 1
    dp[3][0] = 1
    dp[3][1] = 1
    dp[3][2] = 1

    for(i in 4 until 100001){
        dp[i][0] = (dp[i-1][1] + dp[i-1][2]) % mod
        dp[i][1] = (dp[i-2][0] + dp[i-2][2]) % mod
        dp[i][2] = (dp[i-3][0] + dp[i-3][1]) % mod
    }

    repeat(tc){

        val N = readln().toInt()

//        val ans = (dp[N][0] + dp[N][1] + dp[N][2]) % mod

        println("${(dp[N][0] + dp[N][1] + dp[N][2]) % mod}")

    }


}