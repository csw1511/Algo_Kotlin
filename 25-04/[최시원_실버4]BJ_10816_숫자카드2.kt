package org.example

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


var N = 0; var M = 0;
lateinit var numArr: HashMap<Int, Int>

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))

    N = br.readLine().toInt()
    numArr = HashMap()

    var st = StringTokenizer(br.readLine())

    repeat(N){
        val nowNum = st.nextToken().toInt()

        if(!numArr.contains(nowNum)){
            numArr[nowNum] = 1
        }else{
            numArr[nowNum] = numArr[nowNum]!! + 1
        }

    }

    M = br.readLine().toInt()

    st = StringTokenizer(br.readLine())

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    repeat(M){

        val nowFindNum = st.nextToken().toInt()

        if(numArr.contains(nowFindNum)){

            bw.write("${numArr[nowFindNum]} ")

        }else{
            bw.write("0 ")
        }

    }

    bw.flush()
    bw.close()


}
