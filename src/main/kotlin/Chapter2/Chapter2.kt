package Chapter2

// 2.1
fun fibo(n: Int): Int {
    tailrec fun fibonacci(n: Int, n0: Int, n1: Int): Int {
        return if (n == 0) {
            n0
        } else {
            fibonacci(n - 1, n1, n0 + n1)
        }
    }
    return fibonacci(n, 0, 1)
}

// 2.2
val <T> List<T>.tail: List<T>
    get() = drop(1)
val <T> List<T>.head: T
    get() = first()

fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean {
    tailrec fun checkSorted(x: A, xList: List<A>): Boolean =
        if (xList.isEmpty()) true
        else if (!order(x, xList.head)) false
        else checkSorted(xList.head, xList.tail)

    return checkSorted(aa.head, aa.tail)
}

// 2.3
fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C = {
    a: A -> {
        b: B -> f(a, b)
    }
}

// 2.4
fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A, B) -> C = {
    a: A, b: B -> f(a)(b)
}

// 2.5
fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C = {
    a: A -> f(g(a))
}