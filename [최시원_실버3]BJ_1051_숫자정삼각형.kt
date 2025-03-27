package org.example

import java.util.*
import kotlin.math.pow

fun main() {

    var st = StringTokenizer(readln())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val arr2d = Array(N){IntArray(M)}

    for(i in 0..<N){
        val input = readln()
        for(j in 0..<M){
            arr2d[i][j] = input[j].digitToInt()
        }
    }


    for(i in 0..<N){
        for(j in 0..<M){
            square(i, j, arr2d)
        }
    }
    println(largestSize)

}

var largestSize = 1;

fun square(x:Int, y:Int, arr2d:Array<IntArray>){
    var squareSize = 1

    while(true){
        val xOther = x + squareSize
        val yOther = y + squareSize
        if(!(xOther in arr2d.indices && yOther in arr2d[0].indices)) break

        if(arr2d[x][y] == arr2d[x][yOther]
            && arr2d[x][y] == arr2d[xOther][y]
            && arr2d[x][y] == arr2d[xOther][yOther]
            ){
            largestSize = largestSize.coerceAtLeast((squareSize+1).toDouble().pow(2.0).toInt())
        }

        squareSize++
    }
}