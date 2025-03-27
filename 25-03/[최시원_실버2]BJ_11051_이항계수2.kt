package org.example

import java.util.*

fun main(args: Array<String>) {

    val st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val mod  = 10007

    val dp = Array(N+1){IntArray(K+1)}

    for(i in 1..N){
        for(j in 0..K){
            if(j==0 || i==j) dp[i][j] = 1
            else dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]) % 10007
        }
    }

    println(dp[N][K])

}
