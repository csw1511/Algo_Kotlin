package org.example

import java.util.*
fun main(args: Array<String>) {
    var st = StringTokenizer(readln())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

//    val graph = Array(N+1){ LinkedList<Pair<Int, Int>>() }

    val arrVertex = Array(M){ IntArray(3) }
    val parent = IntArray(N+1){ it }

    for(i in 0 until M){
        st = StringTokenizer(readln())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val price = st.nextToken().toInt()
//        graph[a].add(b to price)
//        graph[b].add(a to price)

        arrVertex[i][0] = a
        arrVertex[i][1] = b
        arrVertex[i][2] = price

    }

    arrVertex.sortBy { it[2] }


    var answer  = 0
    var edgeCount = 0
    for(node in arrVertex){
        if(edgeCount == N-2) break;

        val x = find(parent, node[0])
        val y = find(parent, node[1])
        if(x != y){
            union(parent,node[0], node[1])
            answer += node[2]
            edgeCount++;
        }
    }

    println(answer)
}

fun find(parent: IntArray, node: Int): Int{
    if(parent[node] != node){
        parent[node] = find(parent,parent[node])
    }
    return parent[node]

//    if(parent[node] == node){
//        return node
//    }
//    parent[node] = find(parent, parent[node])
//    return find(parent, parent[node])
}

fun union(parent: IntArray, a:Int, b:Int){
    val rootA = find(parent,a)
    val rootB = find(parent,b)

//    println("${rootA} ${rootB}")

    if(rootA < rootB) parent[rootB] = rootA
    else parent[rootA] = rootB
}

