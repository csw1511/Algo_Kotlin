package org.example

import java.util.*

fun main(args: Array<String>) {

    val N = readln().toInt()
    var arr2d = Array(N) {IntArray(N)}

    for(i in 0 until N){
        val st = StringTokenizer(readln())
        for(j in 0 until N){
            arr2d[i][j] = st.nextToken().toInt()
        }
    }

    movePlate(1, N, arr2d)



}

var maxVal = 0
fun recurGame(count:Int, N:Int, arr2d: Array<IntArray>){

}

fun movePlate(direcCode:Int, N:Int, arr2d: Array<IntArray>): Int{

    when(direcCode){
        1 -> {



            for(i in 0 until N){
                var j = 0
                while(j < N){
                    if(arr2d[i][j] != 0){


                    }
                    else{
                        j++
                    }

                }
            }
        }
    }



    return 0
}

fun printArr2d(arr2d: Array<IntArray>){

    for(i in 0 until arr2d.size){
        for(j in 0 until arr2d.size){
            print("${arr2d[i][j]} ")
        }
        println()
    }

    println("-------")
}