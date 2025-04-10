package org.example

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList

var R = 0; var C = 0;
lateinit var arr2d : Array<CharArray>
lateinit var visitedFire: Array<IntArray>
lateinit var visitedJihun: Array<IntArray>

fun main() {

    val st = StringTokenizer(readln())
    R = st.nextToken().toInt()
    C = st.nextToken().toInt()

    arr2d = Array(R){ CharArray(C) }
    visitedFire = Array(R){ IntArray(C) }
    visitedJihun = Array(R){ IntArray(C) }

    var startX=-1
    var startY=-1

    var fireList = ArrayList<Pair<Int, Int>>()

    repeat(R){ptr->
        val input = readln()
        repeat(C){ptr2->
            if(input[ptr2] == 'J') {
                startX = ptr
                startY = ptr2
            }

            if(input[ptr2] == 'F'){
                fireList.add(ptr to ptr2)
            }
            arr2d[ptr][ptr2] = input[ptr2]
        }
    }


    val isPossible = bfs(startX, startY, fireList)

    if(!isPossible) println("IMPOSSIBLE")




}

val dx = intArrayOf(0,0,1,-1)
val dy = intArrayOf(1,-1,0,0)

fun bfs(startX:Int, startY: Int, fireList:ArrayList<Pair<Int, Int>>): Boolean{
    val qJihun = ArrayDeque<Pair<Int, Int>>()
    val qFire = ArrayDeque<Pair<Int, Int>>()
    qJihun.add(startX to startY)

    for(i in fireList){
        qFire.add(i.first to i.second)
        visitedFire[i.first][i.second] = 1
    }
    visitedJihun[startX][startY] = 1

    //불 먼저 전부 이동시킨다.
    while(qFire.isNotEmpty()){
        val fNow = qFire.removeFirst()
        val nowFireX = fNow.first
        val nowFireY = fNow.second

        //불 먼저 이동
        for(i in 0..3){
            val nextFireX = nowFireX + dx[i]
            val nextFireY = nowFireY + dy[i]

            if(!(nextFireX in 0..<R && nextFireY in 0..<C)) continue  //맵밖이면 이동불가능
            if(arr2d[nextFireX][nextFireY] == '#') continue //벽이면 이동불가능
            if(visitedFire[nextFireX][nextFireY] > 0) continue  //한번 방문한 곳이면 이동불가능

            qFire.add(nextFireX to nextFireY)
            visitedFire[nextFireX][nextFireY] = visitedFire[nowFireX][nowFireY] + 1
        }

    }

    //만약 불이 이동속도가 기록된 visitedFire의 해당위치의 숫자보다 Jiwon이 늦었다면, 진행하지 않는다
    while(qJihun.isNotEmpty()){
        val jNow = qJihun.removeFirst()
        val nowJihunX = jNow.first
        val nowJihunY = jNow.second
        // 해당위치가 불인데, 불보다 지훈이 느렸으면 불가능. 불이 방문한 곳이어야 한다
        if(visitedFire[nowJihunX][nowJihunY] != 0 &&
            visitedFire[nowJihunX][nowJihunY] <= visitedJihun[nowJihunX][nowJihunY]) continue

        for(i in 0..3){
            val nextJihunX = nowJihunX + dx[i]
            val nextJihunY = nowJihunY + dy[i]

            //지훈이 맵 밖으로 나갈수 있다면 탈출할 수 있다
            if(!(nextJihunX in 0..<R && nextJihunY in 0..<C)) {
                println(visitedJihun[nowJihunX][nowJihunY])
                return true
            }
            if(arr2d[nextJihunX][nextJihunY] == '#') continue //벽이면 이동불가능
            if(visitedJihun[nextJihunX][nextJihunY] > 0) continue  //한번 방문한 곳이면 이동불가능

            qJihun.add(nextJihunX to nextJihunY)
            visitedJihun[nextJihunX][nextJihunY] = visitedJihun[nowJihunX][nowJihunY] + 1

        }

    }

    return false

}

