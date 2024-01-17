import Chapter2.*

fun main() {
    // 2.1 test
    println(fibo(8))

    // 2.2 test
    val list = listOf(1,2,3,4,5)
    val resultList = isSorted(list, { x, y -> x < y })
    println(resultList)

    // 2.3 test
    val curried = curry({a: Int, b: Int -> a + b})
    println(curried(2)(3))

    // 2.4 test
    val unCurried = uncurry {
        a: Int -> {
            b: Int -> a + b
        }
    }
    println(unCurried(2, 3))

    // 2.5 test
    val composed = compose({ b: Int -> b * 2 }, { a: Int -> a * 3 })
    println(composed(2))
}

