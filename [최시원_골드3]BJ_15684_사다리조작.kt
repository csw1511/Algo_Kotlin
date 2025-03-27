package org.example

import java.util.*

var isEnd = false
var ans = -1

fun main() {
    val st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val H = st.nextToken().toInt()

    val arr2d = Array(H+1){IntArray(N+1)}

    for(i in 0..< M){
        val st = StringTokenizer(readln())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()

        arr2d[x][y] = 1
        arr2d[x][y+1] = -1
    }


    for(i in 0..3){
        dfs(0, i, N, H, arr2d)

        if(isEnd) break
    }


    println(ans)


}

fun dfs(v:Int, count:Int, N:Int, H:Int, arr2d:Array<IntArray>){
    if(v == count){
        if(check(N, H, arr2d)){
            ans = count
            isEnd = true
//            for(t in arr2d){
//                println(t.toList())
//            }
//            println()
        }
        return
    }


    for(i in 1..<N){
        for(j in 1..H){
            if(arr2d[j][i] == 0 && arr2d[j][i+1] == 0){
                arr2d[j][i] = 1
                arr2d[j][i+1] = -1

                dfs(v+1, count, N, H, arr2d)

                arr2d[j][i] = 0
                arr2d[j][i+1] = 0
            }
        }
    }
}

fun check(N: Int, H: Int, arr2d:Array<IntArray>): Boolean{
    for(i in 1..N){
        var line = i
        for(j in 1..H){
            line += arr2d[j][line]
        }
        if(line != i) return false
    }
    return true
}

