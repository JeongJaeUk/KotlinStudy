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


// 2.3


// 2.4


// 2.5