package org.example

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

fun main(args: Array<String>) {
    var st = StringTokenizer(readln())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val arr2d = Array(N+2){IntArray(M+2)}

    for(i in 1..N){
        st = StringTokenizer(readln())
        for(j in 1..M){
            arr2d[i][j] = st.nextToken().toInt()
        }
    }


    val swimPool = Array(N+2){IntArray(M+2)}



}

val dx = intArrayOf(0,0,1,-1)
val dy = intArrayOf(1,-1,0,0)

fun bfs(x:Int, y:Int, arr2d:Array<IntArray>, swimPool:Array<IntArray>){
    if(swimPool[x][y] != 0) return

    val q = ArrayDeque<Pair<Int, Int>>()
    val visited = Array(arr2d.size){BooleanArray(arr2d[0].size)}
    q.add(x to y)
    visited[x][y] = true

    var lowestWall = 0 // 벽은 벽인데 가장 낮은 벽

    while(q.isNotEmpty()){
        val now = q.removeFirst()
        if(arr2d[now.first][now.second] == 0) {
            swimPool[x][y] = -1
            break
        } //땅을 만나는 영역이면 나가버리자. 혹은 boolean 변수로 나가는 여부를 결정 후
                                                    // swimpool의 해당영역을 -1로 채우든가






    }
}