package Chapter3

import Chapter3.List.Companion.setHead
import Chapter3.List.Companion.tail

fun main() {
    var testList = List.of(1,2,3,4)
    val tailList = tail(testList)

    println(tailList)

    val setHeadList = setHead(testList, 3)
    println(setHeadList)
}

sealed class List<out A> {
    companion object {
        fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun sum(ints: List<Int>): Int =
            when (ints) {
                is Nil -> 0
                is Cons -> ints.head + sum(ints.tail)
            }

        fun product(doubles: List<Double>): Double =
            when (doubles) {
                is Nil -> 1.0
                is Cons ->
                    if (doubles.head == 0.0) 0.0
                    else doubles.head * product(doubles.tail)
            }

        // 3.1
        fun <A> tail(xs: List<A>): List<A> =
            when (xs) {
                is Nil -> Nil
                is Cons -> xs.tail
            }

        // 3.2
        fun <A> setHead(xs: List<A>, x: A): List<A> =
            when (xs) {
                is Nil -> Nil
                is Cons -> Cons(x, xs.tail)
            }
    }
}

object Nil: List<Nothing>()
data class Cons<out A>(val head: A, val tail: List<A>): List<A>()


