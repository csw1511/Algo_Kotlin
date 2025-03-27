package org.example

import java.util.*

fun main(args: Array<String>) {
    val N = readln().toInt()
    val st = StringTokenizer(readln())

    val arr = IntArray(N)

    repeat(N) { ptr->
        arr[ptr] = st.nextToken().toInt()
    }

    var valAsc = 1
    var valDesc = 1

    var ans = 1

    var lastNum = arr[0]

    for(i in 1 until N){
        if(arr[i] >= lastNum){
            valAsc += 1
        }else{
            valAsc = 1
        }

        ans = Math.max(valAsc, ans)

        if(arr[i] <= lastNum){
            valDesc += 1
        }else{
            valDesc = 1
        }

        ans = Math.max(valDesc, ans)

        lastNum = arr[i]
    }



    println(ans)

}
