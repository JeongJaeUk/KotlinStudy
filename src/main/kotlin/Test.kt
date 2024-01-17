import Chapter2.fibo
import Chapter2.isSorted

fun main() {
    println(fibo(8))
    val list = listOf(1,2,3,4,5)
    val resultList = isSorted(list, { x, y -> x < y })
    println(resultList)
}

