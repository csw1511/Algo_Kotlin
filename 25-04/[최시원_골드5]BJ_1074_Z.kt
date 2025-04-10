package org.example

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.pow

var N = 0; var r = 0; var c = 0;
var count = 0;

fun main(args: Array<String>) {


    val br = BufferedReader(InputStreamReader(System.`in`))

    val st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()
    r = st.nextToken().toInt()
    c = st.nextToken().toInt()

    zFunc(0,0, 2.0.pow(N.toDouble()).toInt())



}


fun zFunc(startX: Int, startY: Int, nSize: Int){

    if(nSize == 2){

        for(i in 0..1){
            for(j in 0..1){
                if(startX+i == r && startY+j ==c){
                    println(count)
                }
                count++
            }
        }

        return
    }


    val halfSize = nSize / 2

    val quarterVal = halfSize.toDouble().pow(2.0).toInt()



    if(r < startX + halfSize && c < startY+ halfSize){
        zFunc(startX, startY, nSize/2)
    }else if(r < startX + halfSize && c >= startY+ halfSize){

        count += quarterVal
        zFunc(startX, startY + halfSize, nSize/2)

    }else if(r >= startX + halfSize && c < startY+ halfSize){
        count += quarterVal*2
        zFunc(startX + halfSize, startY, nSize/2)

    }else if(r >= startX + halfSize && c >= startY+ halfSize){
        count += quarterVal*3
        zFunc(startX+halfSize, startY+halfSize, nSize/2)
    }



}
