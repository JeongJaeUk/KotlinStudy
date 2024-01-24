package Chapter3


import Chapter3.List.Companion.drop
import Chapter3.List.Companion.dropWhile
import Chapter3.List.Companion.init
import Chapter3.List.Companion.length
import Chapter3.List.Companion.setHead
import Chapter3.List.Companion.tail

fun main() {
    var testList = List.of(1,2,3,4)
    val tailList = tail(testList)

    val setHeadList = setHead(testList, 3)
    println(setHeadList)

    val dropedList = drop(testList, 3)
    println(dropedList)

    val dropedWhileList = dropWhile(testList, {n -> n < 3})
    println(dropedWhileList)

    val initedList = init(testList)
    println(initedList)

    println(length(testList))
}

sealed class List<out A> {
    companion object {
        fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun sum(ints: List<Int>): Int =  when (ints) {
            is Nil -> 0
            is Cons -> ints.head + sum(ints.tail)
        }

        fun product(doubles: List<Double>): Double = when (doubles) {
            is Nil -> 1.0
            is Cons -> doubles.head * product(doubles.tail)
        }

        fun <A, B> foldRight(xs: List<A>, z: B, f: (A, B) -> B): B = when(xs) {
            is Nil -> z
            is Cons -> f(xs.head, foldRight(xs.tail, z, f))
        }

        fun sum2(ints: List<Int>): Int =
            foldRight(ints, 0, { a, b -> a + b})

        fun product2(doubles: List<Double>): Double =
            foldRight(doubles, 1.0, { a, b -> a * b})

        fun <A> append(a1: List<A>, a2: List<A>): List<A> =
            when (a1) {
                is Nil -> a2
                is Cons -> Cons(a1.head, append(a1.tail, a2))
            }

        fun <A> empty(): List<A> = Nil

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

        // 3.3
        fun <A> drop(l: List<A>, n: Int): List<A> =
            if(n == 0) l
            else when (l) {
                is Cons -> drop(l.tail, n - 1)
                is Nil -> Nil
            }

        // 3.4
        fun <A> dropWhile(l: List<A>, f: (A) -> Boolean): List<A> =
            when(l) {
                is Cons -> if (f(l.head)) dropWhile(l.tail, f) else l
                is Nil -> Nil
            }

        // 3.5
        fun <A> init(l: List<A>): List<A> =
            when(l) {
                is Cons ->
                    if (l.tail == Nil) Nil
                    else Cons(l.head, init(l.tail))
                is Nil -> Nil
            }

        // 3.8
        fun <A> length(xs: List<A>): Int =
            foldRight(xs, 0, { _, x -> x + 1})

        // 3.9
        tailrec fun <A, B> foldLeft(xs: List<A>, z: B, f: (B, A) -> B): B =
            when(xs) {
                is Nil -> z
                is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
            }

        // 3.10
        fun sumL(xs: List<Int>): Int =
            foldLeft(xs, 0, { x, y -> x + y })

        fun productL(xs: List<Double>): Double =
            foldLeft(xs, 1.0, { x, y -> x * y })

        fun <A> lengthL(xs: List<A>): Int =
            foldLeft(xs, 0, { x, _ -> x + 1 })

        // 3.11
        fun <A> reverse(xs: List<A>): List<A> =
            foldLeft(xs, List.empty(), { f: List<A>, z: A -> Cons(z, f) })
        
    }
}

object Nil: List<Nothing>()
data class Cons<out A>(val head: A, val tail: List<A>): List<A>()


