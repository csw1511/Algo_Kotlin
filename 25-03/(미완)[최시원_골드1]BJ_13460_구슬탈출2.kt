package org.example

import java.util.*
import kotlin.collections.ArrayDeque

fun main(args: Array<String>) {
    val st = StringTokenizer(readln())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    var visited = Array(N) { BooleanArray(M)}
    var arr2d = Array(N) { StringBuilder() }

    for(i in 0 until N){
        arr2d[i] = StringBuilder(readln())
        if(arr2d[i].contains('R')){
            rX = i
            rY = arr2d[i].indexOf('R')
        }
        if(arr2d[i].contains('B')){
            bX = i
            bY = arr2d[i].indexOf('B')
        }
        if(arr2d[i].contains('O')){
            hX = i
            hY = arr2d[i].indexOf('O')
        }
    }

    println("$rX $rY $bX $bY")

    left(N,M,arr2d)

    for(i in 0 until N){
        for( j in 0 until M ){
            print("${arr2d[i][j]} ")
        }
        println()
    }




}

var rX = 0
var rY = 0
var bX = 0
var bY = 0
var hX = 0
var hY = 0

val dx = arrayOf(0,0,1,-1)
val dy = arrayOf(1,-1,0,0)
/*
가정1. 구슬이 한번 지나친 위치를 다시 지나는건 bfs 중복
가정2. 모든 시행에서 R과 B를 동시에 움직여야 함
 */


fun bfs(arr2d: Array<StringBuilder>, visited:Array<BooleanArray>){
    val qRed = ArrayDeque<Pair<Int, Int>>()
    val qBlue = ArrayDeque<Pair<Int, Int>>()
    qRed.add(rX to rY)
    qBlue.add(bX to bY)

    visited[rX][rY] = true
    visited[bX][bY] = true

    while(qRed.isNotEmpty()){
        var nowRedLoc = qRed.removeFirst()
        var nowBlueLoc = qBlue.removeFirst()


    }




}

fun up(arr2d: Array<StringBuilder>){

}
fun down(arr2d: Array<StringBuilder>){

}
fun left(N:Int, M:Int, arr2d: Array<StringBuilder>){
    var mostLeftX = 1
    var mostLeftY = 1

    for(i in 1 until N-1){
        for(j in 1 until M-1){
            if(arr2d[i][j] == '.' && (arr2d[i][j-1] in arrayOf('#', 'R', 'B'))){
                mostLeftX = i
                mostLeftY = j
                println("${mostLeftX} ${mostLeftY}")
            }
            if(arr2d[i][j] == 'R' || arr2d[i][j] == 'B'){
                arr2d[mostLeftX][mostLeftY] = arr2d[i][j]
                arr2d[i][j] = '.'
            }
        }
    }
}
fun right(arr2d: Array<StringBuilder>){

}