package org.example

import java.net.InterfaceAddress
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.max

fun main(args: Array<String>) {
    var st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val K = st.nextToken().toInt()

    val arr2d = Array(N){IntArray(M)}

    for(case in 0 until K){
        st = StringTokenizer(readln())
        val n1 = st.nextToken().toInt()
        val m1 = st.nextToken().toInt()

        var sticker = Array(n1) {IntArray(m1)}

        for(j in 0 until n1){
            st = StringTokenizer(readln())
            for(k in 0 until m1){
                sticker[j][k] = st.nextToken().toInt()
            }
        }

        for(k in 0 until 4){
            val isPaste = findLoc(sticker,arr2d)
            if(isPaste) break
            sticker = turnSticker(sticker)
        }



    }

    var ans = 0

    for(line in arr2d){
        for(ele in line){
            if(ele == 1) ans++
        }
    }

    println(ans)
}

fun findLoc(sticker:Array<IntArray>, arr2d:Array<IntArray>):Boolean{
    val N = arr2d.size
    val M = arr2d[0].size
    val n1 = sticker.size
    val m1 = sticker[0].size

    for(i in 0 until N){
        jump@ for(j in 0 until M){
//            println("${i+n1} ${j+m1}")
            if(i+n1 > N || j+m1 > M) {
//                println("over ${i+n1} ${j+m1}")
                continue
            } //스티커가 해당위치에서 범위를 벗어날때

            val arr2dTmp = arr2d.map{it.copyOf()}.toTypedArray()    //새로이 arr2d를 복사해 사용
            for(k in i until i+n1){ //해당범위에 이미 스티커가 붙어있는가? 아니면 스티커 붙이기.
                                            // arr2dTmp에 하는 이유는 진행도중 스티커가 붙어있다는걸 알면 없애기 위해서임
                for(l in j until j+m1){
                    if(arr2dTmp[k][l] == 1 && sticker[k-i][l-j] == 1) {
//                        println("cut! $k $l")
                        continue@jump
                    }
                    else if(arr2dTmp[k][l] == 1 && sticker[k-i][l-j] == 0) continue
                    arr2dTmp[k][l] = sticker[k-i][l-j]
                }
            }

            for(k in 0 until N){    //스티커 붙이기
                for(l in 0 until M){
                    arr2d[k][l] = arr2dTmp[k][l]
                }
            }

            return true

        }
    }

    return false
}


fun turnSticker(sticker:Array<IntArray>):Array<IntArray>{  //스티커 돌리기
    val turnedSticker = Array(sticker[0].size){IntArray(sticker.size)} //현재로부터 90도 돌린 스티커

    for(i in 0 until sticker.size){
        for(j in 0 until sticker[0].size){
            turnedSticker[j][sticker.size-1-i] = sticker[i][j]
        }
    }


    return turnedSticker
}
