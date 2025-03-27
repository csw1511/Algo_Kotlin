package org.example

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    val N = readln().toInt()
    val K = readln().toInt()

    val snake = ArrayDeque<Pair<Int, Int>>()

    val apple = ArrayList<Pair<Int, Int>>()


    for(i in 1..K){
        val st = StringTokenizer(readln())

        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()

        apple.add(x to y)

    }

    snake.add(1 to 1)


    val moveXC = ArrayDeque<Pair<Int, String>>()

    val L = readln().toInt()
    for(i in 1..L){
        val st = StringTokenizer(readln())

        val X = st.nextToken().toInt()
        val C = st.nextToken()

        moveXC.add(X to C)

    }

    //처음으로 머리를 돌리는 시각과 방향 지정
    var headTurnTime = moveXC.first().first
    var headTurnDirec = moveXC.first().second
    moveXC.removeFirst()

    while(true){

        //1. 게임시간 1초 증가
        totalTime++

        //2. 뱀은 몸길이를 늘려 다음칸에 위치시킴
        headX += dx[direc]
        headY += dy[direc]

//        println("time : $totalTime | $headX $headY")

        //3. 만약 벽이나 자기자신의 몸과 부딪혔는지 체크. 계속할 수 있다면 뱀의 몸길이를 그대로 위치시킴
        if(!isContinue(N, snake)) return break
        snake.addFirst(headX to headY)


        //4. 이동한 칸이 사과라면 사과 없애고 넘어가기
        if(apple.contains(headX to headY)){
            apple.remove(headX to headY)
//            println("get apple $headX $headY snakesize : ${snake.size}")
        }else{
            //5. 사과가 아니라면 꼬리 칸 없애버리기
            snake.removeLast()
        }

        if(totalTime == headTurnTime){
//            println("head turned")
            if(headTurnDirec == "D") direc++
            else if(headTurnDirec == "L") direc--
            if(direc >= 4) direc = 0 else if(direc <= -1) direc = 3
            if(moveXC.isEmpty())continue
            headTurnTime = moveXC.first().first
            headTurnDirec = moveXC.first().second
            moveXC.removeFirst()
        }
    }

//    println(snake)
//    println(apple)

    println(totalTime)

}
var totalTime = 0
var headX = 1
var headY = 1

var direc = 0
val dx = arrayOf(0,1,0,-1)
val dy = arrayOf(1,0,-1,0)

fun isContinue(N:Int, snake:ArrayDeque<Pair<Int,Int>>): Boolean{
    if(headX in 1..N && headY in 1..N && !snake.contains(headX to headY)) return true
    else {
//        println("snake out! $headX $headY")
        return false
    }
}