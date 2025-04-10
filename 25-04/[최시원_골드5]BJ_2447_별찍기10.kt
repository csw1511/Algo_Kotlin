package org.example

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.pow

var N = 0

lateinit var arr2d:Array<IntArray>

fun main(args: Array<String>) {

    val br = BufferedReader(InputStreamReader(System.`in`))

    N = br.readLine().toInt()

    arr2d = Array(N){ IntArray(N) }

    star(0,0,N)

    val sb = StringBuilder()

    for(i in 0..<N){
        for(j in 0..<N){
            if(arr2d[i][j] == 0) sb.append(' ')
            else sb.append('*')
        }
        sb.append('\n')
    }

    println(sb)

}


fun star(startX:Int, startY:Int, nowSize:Int){

    if(nowSize == 3){
        for(i in 0..2){
            for(j in 0..2){
                if(i==1 && j==1) continue
                arr2d[startX+i][startY+j] = 1
            }
        }
        return
    }


    val nextSize = nowSize / 3


    for(i in 0..2){
        for(j in 0..2){
            if(i==1 && j==1) continue
            star(startX + (nextSize*i), startY + (nextSize*j), nextSize)
        }
    }



}