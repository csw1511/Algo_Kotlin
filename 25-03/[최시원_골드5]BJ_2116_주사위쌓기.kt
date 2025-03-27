package org.example

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.max

fun main(args: Array<String>) {

    val N = readln().toInt()

    val arr2d = Array(N){IntArray(6)}

    for(i in 0 until N){
        val st = StringTokenizer(readln())
        for(j in 0 until 6){
            arr2d[i][j] = st.nextToken().toInt()
        }
    }

    var maxVal = 0
    for(i in 1..6){
        maxVal = max(calcDice(0, i, 0, arr2d), maxVal)
    }

    println(maxVal)



    //a b c d e f 순서로 입력받음.
    //0-5 / 1-3 / 2-4 가 서로 반대이다


}




// nowBottomIndex 로 처음부터 찾지 말고 nowBottomValue를 입력받은 후, 이를 바탕으로 nowBottomIndex를 찾는 로직으로 짜야
// 재귀형태로 구현할 수 있다

fun calcDice(nowLevel:Int, nowBottomValue:Int, totalValue:Int, arr2d: Array<IntArray>):Int{
    if(nowLevel == arr2d.size){
//        println("hello its end ${totalValue}")
        return totalValue
    }

    var nowTopIndex = 0
    var nowBottomIndex = 0

    // 입력받은 bottom 값에 맞는 bottom index 구하기
    for(i in 0 until 6){
        if(arr2d[nowLevel][i] == nowBottomValue){
            nowBottomIndex = i
        }
    }

    //bottom index에 따른 top index 구하기
    when(nowBottomIndex){
        0 -> nowTopIndex = 5
        in 1..4-> {
            nowTopIndex = nowBottomIndex + 2
            if(nowTopIndex > 4) nowTopIndex -= 4
        }
        5 -> nowTopIndex = 0
    }

    var nowMaxValue = 0

    //골라진 bottom index, top index 제외하고 최대값 구하기
    for(i in 0 until 6){
        if(i == nowBottomIndex || i == nowTopIndex) continue
        nowMaxValue = max(arr2d[nowLevel][i], nowMaxValue)
    }

    //아까 구한 top index를 통해 top value를 넘겨주면 다음 재귀 구현가능

    return calcDice(nowLevel+1, arr2d[nowLevel][nowTopIndex], totalValue + nowMaxValue, arr2d)
}
