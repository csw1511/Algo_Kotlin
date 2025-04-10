package org.example

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.contracts.contract

var L = 0; var R = 0; var C = 0
var sX = 0; var sY = 0; var sZ = 0;
var eX = 0; var eY = 0; var eZ = 0;
lateinit var arr3d: Array<Array<CharArray>>
lateinit var visited: Array<Array<IntArray>>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    while(true){

        var st = StringTokenizer(br.readLine())

        L = st.nextToken().toInt()
        R = st.nextToken().toInt()
        C = st.nextToken().toInt()

        if(L == 0 && R == 0 && C == 0) break

        arr3d = Array(L){Array(R){ CharArray(C) }}
        visited = Array(L){Array(R){IntArray(C){-1} }}

        for(i in 0..<L){
            for(j in 0..<R){
                val input = br.readLine()
                for(k in 0..<C){
                    arr3d[i][j][k] = input[k]
                    if(arr3d[i][j][k] == 'S'){
                        sX = i; sY = j; sZ = k
                    }
                    if(arr3d[i][j][k] == 'E'){
                        eX = i; eY = j; eZ = k
                    }
                }
            }

            br.readLine()
        }


        bfs()


    }

}

val dx = intArrayOf(0,0,1,-1,0,0)
val dy = intArrayOf(1,-1,0,0,0,0)
val dz = intArrayOf(0,0,0,0,1,-1)

fun bfs(){
    val q = ArrayDeque<Triple<Int, Int, Int>>()
    q.add(Triple(sX, sY, sZ))
    visited[sX][sY][sZ] = 0

    while(q.isNotEmpty()){
        val now = q.removeFirst()

        if(now.first == eX && now.second == eY && now.third == eZ){
            println("Escaped in ${visited[eX][eY][eZ]} minute(s).")
            return
        }

        for(i in 0..<6){
            val nextX = now.first + dx[i]
            val nextY = now.second + dy[i]
            val nextZ = now.third + dz[i]

            if(!(nextX in 0..<L && nextY in 0..<R && nextZ in 0..<C)) continue
            if(visited[nextX][nextY][nextZ] != -1) continue
            if(arr3d[nextX][nextY][nextZ] == '#') continue

            q.add(Triple(nextX, nextY, nextZ))
            visited[nextX][nextY][nextZ] = visited[now.first][now.second][now.third] + 1

        }
    }

    println("Trapped!")

}
