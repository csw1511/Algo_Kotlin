package org.example

import java.util.*
import kotlin.collections.ArrayDeque

fun main(args: Array<String>) {

    val K = readln().toInt()
    var st = StringTokenizer(readln())
    val H = st.nextToken().toInt()
    val W = st.nextToken().toInt()

    if(H==W && H == 1) {
        println(0)
        return
    }

    val arr2d = Array(W) { IntArray(H) }
    for(i in 0 until W){
        st = StringTokenizer(readln())
        for(j in 0 until H){
            arr2d[i][j] = st.nextToken().toInt()
        }
    }

    println(bfs(K+1, W,H, arr2d))

}

fun bfs(K:Int, W:Int, H:Int, arr2d:Array<IntArray>): Int{
    val dx = intArrayOf(0,0,1,-1, 2, 2, -2, -2, 1, -1, 1, -1)
    val dy = intArrayOf(1,-1,0,0, 1, -1, 1, -1, 2, 2, -2, -2)
    val dz = intArrayOf(0,0,0,0,1,1,1,1,1,1,1,1)
    val visited = Array(W){Array(H){IntArray(K) {-1} } }

    val q = ArrayDeque<Triple<Int, Int, Int>>()
    q.add(Triple(0,0,0))
    for(i in 0 until K){
        visited[0][0][i] = 0
    }


    while(q.isNotEmpty()){
        val now = q.removeFirst()
//        println("${now.first} ${now.second} ${now.third}")
//
//        println("-")
        if(now.first == W-1 && now.second == H-1){
//            for(k in 0 until K){
//                for(i in 0 until W){
//                    for(j in 0 until H){
//                        print("${visited[i][j][k]} ")
//                    }
//                    println()
//                }
//                println("-")
//            }
            return visited[now.first][now.second][K-1]
        }

        for(i in 0 until 12){
            val nextX = now.first + dx[i]
            val nextY = now.second + dy[i]
            val nextZ = now.third + dz[i]

            if(nextX in 0 until W && nextY in 0 until H && nextZ in 0 until K){
                if(visited[nextX][nextY][nextZ] != -1 ) continue
//                if(visited[nextX][nextY][nextZ] > visited[now.first][now.second][now.third]+1 ) continue
                if(arr2d[nextX][nextY] == 1) continue
                q.add(Triple(nextX, nextY, nextZ))

                for(jump in nextZ until K){
                    visited[nextX][nextY][jump] = visited[now.first][now.second][now.third] + 1
                }
            }
        }
    }

    return -1

}