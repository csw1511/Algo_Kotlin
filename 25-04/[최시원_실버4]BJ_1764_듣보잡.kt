package org.example

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))

    val st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val hashMap = HashMap<String, Int>()

    repeat(N){
        val name = br.readLine()

        hashMap[name] = 0
    }

    var ansCount = 0

    val ansList = ArrayList<String>()


    repeat(M){
        val name = br.readLine()

        if(hashMap.contains(name)){
            ansCount++
            ansList.add(name)
        }
    }

    ansList.sort()

    println(ansCount)

    for(i in ansList){
        println(i)
    }


}
