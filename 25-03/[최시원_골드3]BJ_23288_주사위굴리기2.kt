package org.example

import java.util.*
import kotlin.collections.ArrayDeque

fun main(args: Array<String>) {
    var st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val K = st.nextToken().toInt()

    val arr2d = Array(N){IntArray(M)}

    for(i in 0 until N){
        st = StringTokenizer(readln())
        for(j in 0 until M){
            arr2d[i][j] = st.nextToken().toInt()
        }
    }

    var diceX = 0
    var diceY = 0

    // 0-5 인덱스 각각 상 좌 앞 뒤 우 하
    val dice = Array(6){0}
    dice[0] = 1
    dice[1] = 4
    dice[2] = 5
    dice[3] = 2
    dice[4] = 3
    dice[5] = 6
//    println("  ${dice[3]}")
//    println("${dice[1]} ${dice[0]} ${dice[4]}")
//    println("  ${dice[2]}")
//    println("  ${dice[5]}")


    //0-3 인덱스 각각 굴림방향 좌 하 우 상. 즉 시계방향
    var direc = 0

    val scoreBoard = Array(N){IntArray(M)}

    var totalAns = 0
    for(rolling in 0 until K){
        //1. 주사위가 이동방향으로 한칸 굴러감
        //1-1. 주사위의 위치 조정. direc과 diceX, diceY를 활용함
        if(!(diceX + dx[direc] in 0 until N &&  diceY + dy[direc] in 0 until M)){
            direc += 2
            if(direc >= 4) direc -= 4
        }

        diceX += dx[direc]
        diceY += dy[direc]

        //1-2. 주사위 자체를 굴린 결과를 만들어냄
        diceRoll(direc,dice)


        //2. 주사위 도착 칸의 점수 획득
        totalAns += bfsDiceScore(diceX, diceY, arr2d, scoreBoard)

        //3. 주사위 아랫면 정수 A와 주사위 도착칸 B를 비교해 이동방향 변경
        if(dice[5] > arr2d[diceX][diceY]){
            direc += 1  //방향 시계로 90도 변경
            if(direc >=4) direc -= 4
        }else if(dice[5] < arr2d[diceX][diceY]){
            direc -= 1  //방향 반시계로 90도 변경
            if(direc < 0) direc += 4
        }

//        println("now dice loc : ${diceX+1} ${diceY+1} ${direc}")
//        println("  ${dice[3]}")
//        println("${dice[1]} ${dice[0]} ${dice[4]}")
//        println("  ${dice[2]}")
//        println("  ${dice[5]}")


    }


    println(totalAns)


}

//이 역시 방향이 좌 하 우 상 -> 시계방향
val dx = arrayOf(0,1,0,-1)
val dy = arrayOf(1,0,-1,0)

fun bfsDiceScore(x:Int, y:Int, arr2d:Array<IntArray>, scoreBoard:Array<IntArray>): Int{
    if(scoreBoard[x][y] != 0){
        return scoreBoard[x][y]
    }


    val q = ArrayDeque<Pair<Int, Int>>()
    val visited = Array(scoreBoard.size){BooleanArray(scoreBoard[0].size)}
    q.add(x to y)
    visited[x][y] = true

    var scoreSum = 0

    while(q.isNotEmpty()){
        val now = q.removeFirst()
        scoreSum += arr2d[now.first][now.second]    //점수구해주기

        for(i in 0 until 4){
            val nextX = now.first + dx[i]
            val nextY = now.second + dy[i]

            if(nextX in arr2d.indices && nextY in 0 until arr2d[0].size){
                if(!visited[nextX][nextY] && arr2d[x][y] == arr2d[nextX][nextY]){
                    q.add(nextX to nextY)
                    visited[nextX][nextY] = true
                }
            }
        }
    }

    for(i in scoreBoard.indices){
        for(j in scoreBoard[0].indices){
            if(visited[i][j]){
                scoreBoard[i][j] = scoreSum
            }
        }
    }

    return scoreSum

}



fun diceRoll(direc:Int, dice:Array<Int>){
    when(direc){
        0 ->{   //좌측 굴림
            val tmp = dice[0]
            dice[0] = dice[1]
            dice[1] = dice[5]
            dice[5] = dice[4]
            dice[4] = tmp
        }
        1 ->{   //아래쪽 굴림
            val tmp = dice[0]
            dice[0] = dice[3]
            dice[3] = dice[5]
            dice[5] = dice[2]
            dice[2] = tmp
        }
        2 ->{   //우측 굴림
            val tmp = dice[0]
            dice[0] = dice[4]
            dice[4] = dice[5]
            dice[5] = dice[1]
            dice[1] = tmp
        }
        3 ->{   //위쪽 굴림
            val tmp = dice[0]
            dice[0] = dice[2]
            dice[2] = dice[5]
            dice[5] = dice[3]
            dice[3] = tmp
        }
    }

}
