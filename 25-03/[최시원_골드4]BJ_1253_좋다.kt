package org.example

import java.util.*
import kotlin.collections.ArrayDeque

fun main(args: Array<String>) {
    val N = readln().toInt()
    val LongArr = LongArray(N)

    val st = StringTokenizer(readln())

    for(i in 0 until N){
        LongArr[i] = st.nextToken().toLong()
    }

    val arr = LongArr.sorted()


    var ans = 0

    repeat(N) { ptr ->
        var left = 0
        var right = N - 1

        while (left < right) {
            val sum = arr[left] + arr[right]

            if (sum == arr[ptr]) {
                // 투 포인터가 자기 자신을 포함하면 안됨
                if (left == ptr || right == ptr) {
                    if (left == ptr) left++
                    if (right == ptr) right--
                } else {
                    ans++
                    break
                }

            } else if (sum < arr[ptr]) {
                left++
            } else {
                right--
            }
        }
    }


    println(ans)



}

//fun twoPointer(left:Int, right:Int, target:Int, intArr: List<Int>): Boolean{
//    if(left == right || right == intArr.size || left == -1){
//        return false
//    }
//
//    val sum = intArr[left] + intArr[right]
//
//    if(sum == intArr[target]){
//        println("${intArr[left]} + ${intArr[right]} = ${intArr[target]}")
//        return true
//    }else if(sum < intArr[target]){
//        return twoPointer(left+1, right, target, intArr)
//    }else if(sum > intArr[target]){
//        return twoPointer(left, right-1, target, intArr)
//    }else{
//        return false
//    }
//}