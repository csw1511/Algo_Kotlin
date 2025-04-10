package org.example

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow

var N = 0; var X = 0L;
lateinit var burgerSize:LongArray
lateinit var pattySize:LongArray
fun main(args: Array<String>) {

    val br = BufferedReader(InputStreamReader(System.`in`))

    val st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()
    X = st.nextToken().toLong()

    burgerSize = LongArray(N+1)
    pattySize = LongArray(N+1)

    burgerSize[0] = 1
    pattySize[0] = 1

    for(i in 1..N){
        burgerSize[i] = burgerSize[i-1] * 2 + 3
        pattySize[i] = pattySize[i-1] * 2 + 1
    }

//    println(burgerSize.toList())
//    println(pattySize.toList())

    println(pattyNum(X, N))

}

fun pattyNum(nowX:Long, nowLevel:Int):Long{

    if(nowX <= 0L){
        return 0L
    }

    if(nowLevel == 0){
        return pattySize[nowLevel]
    }

    var nowPatty = 0L

    if(nowX > burgerSize[nowLevel] / 2){
        val beforeX = nowX - (burgerSize[nowLevel-1] + 2)
        nowPatty = pattySize[nowLevel-1] + 1 + ( pattyNum( beforeX, nowLevel-1) )
    }else{
        nowPatty = pattyNum(nowX-1, nowLevel-1)
    }

    return nowPatty
}
