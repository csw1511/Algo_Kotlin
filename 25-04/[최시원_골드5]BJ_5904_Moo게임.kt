package org.example

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow

var N = 0
lateinit var mooSize:IntArray

fun main(args: Array<String>) {

    val br = BufferedReader(InputStreamReader(System.`in`))

    val st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()

    mooSize = IntArray(28)

    mooSize[0] = 3

    for(i in 1..27){
        mooSize[i] = mooSize[i-1]*2 + (i+ mooSize[0])
        if(N <= mooSize[i]){
            moo(N, i)
            break
        }
    }

//    println(mooSize.toList())



}

fun moo(nowN:Int, index:Int){
    if(index == 0){
        if(nowN == 1){
//            println("인덱스 $index 에서 값 m")
            println("m")
        }else{
//            println("인덱스 $index 에서 값 o")
            println("o")
        }
        return
    }


    if(nowN > mooSize[index-1]){
        val remainN = nowN - mooSize[index-1]
        if(remainN <= index+ mooSize[0]){
            if(remainN == 1){
//                println("인덱스 $index 에서 값 m")
                println("m")
            }else{
//                println("인덱스 $index 에서 값 o")
                println("o")
            }
            return
        }else{
            val alsoRemainN = remainN - index - mooSize[0]
            moo(alsoRemainN, index-1)
        }
    }else{
        moo(nowN, index-1)
    }

}
