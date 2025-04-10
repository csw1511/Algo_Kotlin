package org.example

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList

lateinit var arr2d: Array<IntArray>
lateinit var virus: ArrayList<Pair<Int, Int>>
var N = 0; var M = 0;
var count = 0
var ans = Int.MAX_VALUE

fun main() {

    val st = StringTokenizer(readln())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()

    arr2d = Array(N){IntArray(N)}
    result = IntArray(M)
    virus = ArrayList()

    repeat(N){ i->
        val input = readln().split(" ")
        repeat(N){ j->
            arr2d[i][j] = input[j].toInt()
            if(arr2d[i][j] == 1) count++
            if(arr2d[i][j] == 2){
                virus.add(i to j)
                count++
            }
        }
    }

    //모든 빈 칸의 개수
    count = N*N - count

    combi(0,0)

    if(ans == Int.MAX_VALUE){
        println(-1)
    }else{
        println(ans)
    }


//    if(ans!=Int.MAX_VALUE){
//        println(ans)
//    }else{
//        println(-1)
//    }


}

val dx = intArrayOf(0,0,1,-1)
val dy = intArrayOf(1,-1,0,0)

fun bfs(startList:ArrayList<Pair<Int,Int>>){
    var nowLowestTime = 0
    val visited = Array(N){IntArray(N){-1} }
    val q = ArrayDeque<Pair<Int, Int>>()

    var needFillCount = 0

    for(i in startList){
        q.add(i)
        visited[i.first][i.second] = 0
    }

    while(q.isNotEmpty()){
        val now = q.removeFirst()
        if(arr2d[now.first][now.second] != 2){
            nowLowestTime = Math.max(nowLowestTime, visited[now.first][now.second])
        }

        for(i in 0..3){
            val nextX = now.first + dx[i]
            val nextY = now.second + dy[i]

            // 범위밖으로 나가는 경우
            if(!(nextX in 0..<N && nextY in 0..<N)) continue
            // 벽인 경우
            if(arr2d[nextX][nextY] == 1) continue
            // visited방문한 경우
            if(visited[nextX][nextY] != -1) continue

            //추가로 바이러스인 2인 경우 그냥 통과는 가능하되

            q.add(nextX to nextY)
            visited[nextX][nextY] = visited[now.first][now.second] + 1
            needFillCount++
        }

    }

//    for(i in visited){
//        for(j in i.toList()){
//            if(j == 0){
//                return
//            }
//        }
//    }

    for(i in 0..<N){
        for(j in 0..<N){
            if(arr2d[i][j] == 0 && visited[i][j] == -1) {
                return
            }
        }
    }



    ans = ans.coerceAtMost(nowLowestTime)


//    for(i in visited){
//        println(i.toList())
//    }
//    println(ans)
//    println()
}


lateinit var result:IntArray

fun combi(now:Int, beforeIndex:Int){
    if(now == M){
        //종료조건

        val virusList = ArrayList<Pair<Int, Int>>()
        for(i in result){
            virusList.add(virus[i])
        }

//        println(virusList)

        bfs(virusList)

        return
    }

    for(i in beforeIndex ..< virus.size){
        result[now] = i
        combi(now+1, i+1)
    }
}