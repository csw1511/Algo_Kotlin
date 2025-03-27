package org.example

import java.util.*

fun main() {
    val root = Node(readln().toInt(), null, null)

    while(true){
        val input = readlnOrNull() ?: break
        val n = input.toInt()
        root.addChildNode(n)
    }

    postOrder(root)
    println(sb)

}

val sb = StringBuilder()

fun postOrder(node: Node?){
    if(node != null){
        postOrder(node.left)
        postOrder(node.right)
        sb.append(node.value).append("\n")
    }
}

data class Node(val value:Int, var left: Node?, var right: Node?){  // 생성자에 멤버변수도 같이 선언되는 그런느낌
    fun addChildNode(n: Int){
        if(n > value){ // 들어온 값 n이 노드의 값 value보다 크면 오른쪽으로 가야 함
            //right가 비어있을 때 새 노드 삽입
            if(right == null) right = Node(n, null, null)
            //right가 있을 때 오른쪽 자식노드로 가서 addChildNode 수행
            else right!!.addChildNode(n)
        } else {
            if(left == null) left = Node(n, null, null)
            else left!!.addChildNode(n)
        }
    }
}