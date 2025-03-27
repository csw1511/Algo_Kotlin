package org.example

import java.util.*

fun main(args: Array<String>) {

    val arr2d = Array(4){ IntArray(4) }

    val fishLocation = Array(17){IntArray(2)}

    for(i in 0 until 4){
        val st = StringTokenizer(readln())

        for(j in 0 until 4){

            val fishNum = st.nextToken().toInt()
            val fishDirec = st.nextToken().toInt()

            // 물고기 번호와 방향을 한꺼번에 저장하기 위해 번호에 10을 곱해 저장. 12번 물고기의 7번 방향이면 127
            arr2d[i][j] = fishNum * 10 + fishDirec
            // 일단 arr2d에 물고기 방향만 저장해두는 방안으로 해보자
//            arr2d[i][j] =  fishDirec

            // fishNum을 인덱스로 활용. 1번 인덱스에 1번 물고기의 위치가 들어가있는 것이다
            fishLocation[fishNum][0] = i
            fishLocation[fishNum][1] = j

        }



    }

    for(i in arr2d){
        println(i.toList())
    }
    for(i in fishLocation){
        print("${i[0]} ${i[1]} | ")
    }
    println()

    val sharkLocX = 0
    val sharkLocY = 0

    sharkMove(0, sharkLocX, sharkLocY, arr2d, fishLocation)


    for(i in arr2d){
        println(i.toList())
    }
    for(i in fishLocation){
        print("${i[0]} ${i[1]} | ")
    }
    println()



}

fun sharkMove(ateFish:Int, sharkLocX: Int, sharkLocY: Int, arr2d: Array<IntArray>, fishLocation: Array<IntArray>){
    //1. 상어가 물고기를 잡아먹고, 그 방향을 얻음. first : fishNum / second : fishDirec
    val fish = fishInfoDecode(arr2d[sharkLocX][sharkLocY])
    // 물고기가 잡아먹혔으므로 fishNum * 10 으로 저장해둔 백, 십의 자리가 없어진다.
    // 상어가 먹은 물고기 번호 합의 최대값을 알아야 한다

    arr2d[sharkLocX][sharkLocY] = fish.second    // 1-8의 값이 들어감.

    //2. 물고기의 이동
    fishTotalMove(sharkLocX, sharkLocY, arr2d, fishLocation)


    //3. 상어의 이동. 여러칸 이동이 가능하며, 물고기 없는 칸으로 이동은 불가능하며, 이동할 수 있는 칸이 없으면 종료
    //상어는 아까 먹은 물고기의 방향으로 이동 가능하며, 한번에 여러 칸 가능하다. 최대 3칸 가능
    // 물고기 있는 칸으로만 이동 가능하며 중간에 있는 물고기는 먹지않는다
    //이때 상어가 먹는 물고기번호 합의 최대를 구하시오
    //최대로 큰 숫자가 되게끔 먹어야 한다고? 일단 상어는 방향전환을 안한다

    println(ateFish+fish.first)

    for(i in 1..3){
        var sharkNextX = sharkLocX + (dx[fish.second-1]*i)
        var sharkNextY = sharkLocY + (dy[fish.second-1]*i)

        if(!(sharkNextX in 0..3 && sharkNextY in 0 .. 3)) break // 범위 밖이면 그만두기

        if(arr2d[sharkNextX][sharkNextY] / 10 == 0) continue // 해당 위치에 물고기가 없으면 넘어가기

        sharkMove(ateFish + fish.first, sharkNextX, sharkNextY, arr2d, fishLocation)


    }


}




fun fishTotalMove(sharkLocX:Int, sharkLocY:Int, arr2d:Array<IntArray>, fishLocation:Array<IntArray>){
    //2. 물고기의 이동

    for(i in 1..16){
        //1-16번 물고기의 위치부터 구한다
        //일단 물고기가 먹혔는지부터 확인. 지금 먹혔다면 위치에 -1을 넣어주고, 먹혔다면(이미 먹힌 것을 포함해) 넘어간다
        if(fishLocation[i][0] == sharkLocX && fishLocation[i][1] == sharkLocY) {
            fishLocation[i][0] = -1
            fishLocation[i][1] = -1
        }
        if(fishLocation[i][0] == -1 && fishLocation[i][1] == -1) continue

        //이 이후엔 먹히지 않은 물고기의 이동이다.
        val fishX = fishLocation[i][0]
        val fishY = fishLocation[i][1]

        val fishNow = fishInfoDecode(arr2d[fishX][fishY])
        var fishNum = fishNow.first
        var fishDirec = fishNow.second-1

        //물고기의 다음 위치를 구하자. 이미 fish.second으로 방향은 구해졌었음
        var count = 1
        while(count <= 8){
            val fishNextX = fishX + dx[fishDirec]
            val fishNextY = fishY + dy[fishDirec]

            //범위를 벗어나는경우 혹은 상어를 만나는 경우 방향을 1칸 증가시키고 넘어가버리기
            if(!(fishNextX in 0..3 && fishNextY in 0..3)
                || (fishNextX == sharkLocX && fishNextY == sharkLocY)) {
                fishDirec++
                if(fishDirec==8) fishDirec = 0
                count++
                continue
            }

            //만약 범위 내에서, 상어도 만나지 않는 위치라면 해당 위치의 물고기와 위치바꾸기
            //위치를 바꾼다는 것은 fishLocation 배열 내의 정보를 서로 뒤바꾸고
            //arr2d 위에서의 방향도 서로 뒤바꾸는것을 의미함
            //이를 위해서는 먼저 서로 위치를 바꿀 물고기 정보를 구해야 하고, 이는 arr2d의 해당위치에서 알 수 있다

            val fishNextNum = fishInfoDecode(arr2d[fishNextX][fishNextY]).first
            val fishNextDirec = fishInfoDecode(arr2d[fishNextX][fishNextY]).second

            arr2d[fishNextX][fishNextY] = fishNum * 10 + (fishDirec+1)
            arr2d[fishX][fishY] = fishNextNum * 10 + fishNextDirec

            fishLocation[fishNextNum][0] = fishX
            fishLocation[fishNextNum][1] = fishY

            fishLocation[fishNum][0] = fishNextX
            fishLocation[fishNum][1] = fishNextY

            break
        }

    }

}



val dx = intArrayOf(-1,-1,0,1,1,1,0,-1)
val dy = intArrayOf(0,-1,-1,-1,0,1,1,1)


//arr2d[i][j]를 넣으면 물고기번호와 방향 pair를 반환함
fun fishInfoDecode(fish:Int): Pair<Int,Int> {
    val fishNum:Int = fish / 10
    val fishDirec:Int = fish % 10

    return fishNum to fishDirec
}