package org.example

import java.util.*

fun main() {
    val N = readln().toInt()

    if(N <= 9){
        println(N)
        return
    }
    if(N > 1022){
        println(-1)
        return
    }

    var numList = LinkedList<String>()

    repeat(10){ptr->
        numList.add(ptr.toString())
    }


    var count = 0

    run@ while(true){


        val tmpList = LinkedList<String>()
        for(i in 1..9){
            for(j in numList){
                if(i > j[0].digitToInt()){ //열람한 숫자 j 의 맨 앞이 i보다 작으면
                    //j 앞에 i 붙여서 리스트에 집어넣기
                    val sb = StringBuilder()
                    sb.append(i)
                    sb.append(j)
                    tmpList.add(sb.toString())
                }
            }
        }

//        println(tmpList)
        numList = tmpList

        for(i in tmpList){
            if(count == N-10) {
                println(i)
                break@run
            }
            count++
        }

    }

}
