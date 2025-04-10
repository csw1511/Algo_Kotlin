package org.example

import java.util.*
import kotlin.collections.ArrayDeque

var N = 0; var M = 0; var K = 0;
var ans = Int.MAX_VALUE

fun main(args: Array<String>) {

    val st = StringTokenizer(readln())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    K = st.nextToken().toInt()

    val arr2d = Array(N){ IntArray(M) }

    for(i in 0 until N){
        val nowInput = readln().split("")
        for(j in 1..M){
            arr2d[i][j-1] = nowInput[j].toInt()
        }
    }

    val visited = Array(K+1){Array(N){ IntArray(M) { -1 } } }


    println(bfs(arr2d, visited))


//    for(k in 0..K){
//        for(i in 0..<N){
//            for(j in 0..<M){
//                print("${visited[k][i][j]} ")
//            }
//            println()
//        }
//        println()
//    }




}

val dx = intArrayOf(0,0,1,-1)
val dy = intArrayOf(1,-1,0,0)

fun bfs(arr2d:Array<IntArray>, visited:Array<Array<IntArray>>):Int{
    val q = ArrayDeque<Triple<Int, Int, Int>>()
    q.add(Triple(0,0,0))

    for(k in 0..K){
        visited[k][0][0] = 1
    }

    while (q.isNotEmpty()){
        val now = q.removeFirst()


        if(now.second == N-1 && now.third == M-1){
            return visited[now.first][now.second][now.third]
        }

        for(i in 0..3){
            val breakCount = now.first
            val nextX = now.second + dx[i]
            val nextY = now.third + dy[i]

            //범위밖이면 넘어가기
            if(!(nextX in arr2d.indices && nextY in arr2d[0].indices)) continue


            //벽이 아니면 진행하기
            if(arr2d[nextX][nextY] == 0){
                //방문한곳이면 넘어가기

                if(visited[breakCount][nextX][nextY] == -1) {

                    q.add(Triple(breakCount, nextX, nextY))

                    visited[breakCount][nextX][nextY] = visited[breakCount][now.second][now.third] + 1
                }


            }

            //만약 벽이라면?
            else if(arr2d[nextX][nextY] == 1){
                if(breakCount == K)continue    //벽 부순 횟수 K회를 다 사용했다면 끝

                //방문한곳이면 넘어가기
                if(visited[breakCount+1][nextX][nextY] == -1) {

                    q.add(Triple(breakCount+1, nextX, nextY))   //벽을 부쉈음을 명시해주고 진행

                    visited[breakCount+1][nextX][nextY] = visited[breakCount][now.second][now.third] + 1
                }

            }

        }

    }

    return -1

}