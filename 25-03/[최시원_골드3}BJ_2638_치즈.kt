package org.example

import java.util.*
import kotlin.collections.ArrayDeque

fun main(args: Array<String>) {
    var st = StringTokenizer(readln())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val arr2d = Array(N) { IntArray(M) }

    for(i in 0 until N){
        st = StringTokenizer(readln())
        for(j in 0 until M){
            arr2d[i][j] = st.nextToken().toInt()
        }
    }


    var ans = 0

    while(true){
        var count = 0
        val nowCheese = bfs(N,M,arr2d)

        for(i in 0 until N){
            for(j in 0 until M){
                if(nowCheese[i][j] == 2){
                    arr2d[i][j] = 0
                    count++
                }
            }
        }

//        println(count)
        if(count == 0) break

        ans++
//        for(i in 0 until N){
//            println(arr2d[i].toList())
//        }
    }

    print(ans)



}

val dx  = arrayOf(0,0,1,-1)
val dy  = arrayOf(1,-1,0,0)

fun bfs(N:Int, M:Int, arr2d:Array<IntArray>): Array<IntArray>{
    val visited = Array(N){ IntArray(M) }
    val q = ArrayDeque<Pair<Int,Int>>()

    q.add(0 to 0)
    visited[0][0] = -1

    while(q.isNotEmpty()){
        var now = q.removeFirst()

        for(i in 0..3){
            val nextX = now.first + dx[i]
            val nextY = now.second + dy[i]
            if(nextX in 0 until N && nextY in 0 until M){
                if(visited[nextX][nextY] == 0 || visited[nextX][nextY] == 1) {
//                    println("$nextX $nextY")

                    if(arr2d[nextX][nextY] == 1) {
                        visited[nextX][nextY] += 1
                    }
                    else {
                        visited[nextX][nextY] = -1
                        q.add(nextX to nextY)
                    }
                }

            }
        }
    }


    return visited
}