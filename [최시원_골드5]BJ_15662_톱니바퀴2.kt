package org.example

import java.util.*
fun main(args: Array<String>) {

    val T = readln().toInt()

    val arrGear = Array(T) {StringBuilder()}

    for(i in 0 until T){
        //톱니바퀴정보. 12시부터 시계방향으로 1 = S, 0 = N
        arrGear[i] = StringBuilder(readln())
    }


    val K = readln().toInt()
    for(i in 0 until K){
        val st = StringTokenizer(readln())
        //돌아가는 톱니바퀴 번호. 1..T로 입력이 된다
        val spinnedGear = st.nextToken().toInt()-1
        // 방향이 1이면 시계, -1이면 반시계
        val spinDirec = st.nextToken().toInt()

        val willSpinGear:IntArray = spinCheck(spinnedGear, spinDirec, arrGear)

        for(j in 0 until willSpinGear.size){
//            println(willSpinGear[j])
            if(willSpinGear[j] == 1){
                val teeth = arrGear[j].last()
                arrGear[j].deleteAt(7)
                arrGear[j].insert(0, teeth)
            }else if(willSpinGear[j] == -1){
                val teeth = arrGear[j].first()
                arrGear[j].deleteAt(0)
                arrGear[j].append(teeth)
            }
//            println(arrGear[j])
        }

    }

    var ans = 0

    for(i in 0 until T){
        if(arrGear[i][0] == '1'){
            ans++
        }
    }

    println(ans)

}

fun spinCheck(spinnedGear:Int, spinDirec:Int, arrGear: Array<StringBuilder>): IntArray{
    val result = IntArray(arrGear.size)
    result[spinnedGear] = spinDirec

    for(i in spinnedGear-1 downTo 0){
        if(arrGear[i+1][6] != arrGear[i][2]){
            result[i] = result[i+1] * -1
        }else{
            break
        }
    }

    for(i in spinnedGear+1 until arrGear.size){
        if(arrGear[i-1][2] != arrGear[i][6]){
            result[i] = result[i-1] * -1
        }else{
            break
        }
    }


    return result
}