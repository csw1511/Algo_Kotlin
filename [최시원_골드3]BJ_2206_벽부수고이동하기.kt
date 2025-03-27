package org.example

import java.util.*
import kotlin.collections.ArrayDeque

fun main(args: Array<String>) {

    val st = StringTokenizer(readln())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val arr2d = Array(N){ IntArray(M) }

    for(i in 0 until N){
        val nowInput = readln().split("")
        for(j in 1..M){
            arr2d[i][j-1] = nowInput[j].toInt()
        }
    }

    val visited = Array(N){Array(M){ IntArray(2) { -1 } } }

    bfs(arr2d, visited)

//    for(i in 0..<N){
//        for(j in 0..<M){
//            print("${visited[i][j][0]} ")
//        }
//        println()
//    }
//
//    println()
//
//    for(i in 0..<N){
//        for(j in 0..<M){
//            print("${visited[i][j][1]} ")
//        }
//        println()
//    }



    val ans1 = visited[N-1][M-1][0]
    val ans2 = visited[N-1][M-1][1]

    if(ans1 == ans2 && ans1 == -1){
        println(-1)
    }else{

        if(ans1 == -1) println(ans2)
        else if(ans2 == -1) println(ans1)
        else println(Math.min(ans1, ans2))
    }


}

val dx = intArrayOf(0,0,1,-1,0,0,1,-1)
val dy = intArrayOf(1,-1,0,0,1,-1,0,0)

fun bfs(arr2d:Array<IntArray>, visited:Array<Array<IntArray>>){
    val q = ArrayDeque<Triple<Int, Int, Int>>()
    q.add(Triple(0,0,0))
    visited[0][0][0] = 1
    visited[0][0][1] = 1

    while (q.isNotEmpty()){
        val now = q.removeFirst()

        for(i in 0..3){
            val nextX = now.first + dx[i]
            val nextY = now.second + dy[i]
            val isBreak = now.third

            //범위밖이면 넘어가기
            if(!(nextX in arr2d.indices && nextY in arr2d[0].indices)) continue

            //벽이 아니면 진행하기
            if(arr2d[nextX][nextY] == 0){
                //방문한곳이면 넘어가기
                if(visited[nextX][nextY][isBreak] != -1) continue

                q.add(Triple(nextX, nextY, isBreak))


                visited[nextX][nextY][isBreak] = visited[now.first][now.second][isBreak] + 1
            }

            //만약 벽이라면?
            else if(arr2d[nextX][nextY] == 1){
                if(isBreak == 1)continue    //이미 벽을 부쉈으면 끝

                //방문한곳이면 넘어가기
                if(visited[nextX][nextY][isBreak+1] != -1) continue

                q.add(Triple(nextX,nextY, isBreak+1))   //벽을 부쉈음을 명시해주고 진행
                visited[nextX][nextY][isBreak+1] = visited[now.first][now.second][isBreak] + 1
            }

        }

    }

}