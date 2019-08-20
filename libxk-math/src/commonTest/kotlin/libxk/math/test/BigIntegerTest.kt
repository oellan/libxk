package libxk.math.test

import libxk.math.BigInteger
import libxk.math.BigInteger.Companion.ZERO
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@Test
fun absTest() {

    assertEquals(
        ZERO,
        ZERO.abs(),
        "0 == |0| as constants"
    )
    assertEquals(
        BigInteger("51"),
        BigInteger("51").abs(),
        "51 == |51|"
    )
    assertEquals(
        BigInteger("6"),
        BigInteger("-6").abs(),
        "6 == |-6|"
    )
    assertNotEquals(
        BigInteger("-5"),
        BigInteger("-5").abs(),
        "-5 != |-5|"
    )
    assertNotEquals(
        BigInteger("75"),
        BigInteger("97").abs(),
        "75 != |97|"
    )
}

@Test
fun simpleOperationsTest() {

    var a = Random.nextInt()
    var b = Random.nextInt()

    assertEquals(
        BigInteger("${a + b}"),
        BigInteger("$a") + BigInteger("$b"),
        "Addition ($a + $b)"
    )

    assertEquals(
        BigInteger("${a - b}"),
        BigInteger("$a") - BigInteger("$b"),
        "Subtraction ($a - $b)"
    )

    assertEquals(
        BigInteger("${a * b}"),
        BigInteger("$a") * BigInteger("$b"),
        "Multiplication ($a * $b)"
    )

    assertEquals(
        BigInteger("${a / b}"),
        BigInteger("$a") / BigInteger("$b"),
        "Division ($a / $b)"
    )

    a = 121
    b = 11
    assertEquals(
        BigInteger("${a / b}"),
        BigInteger("$a") / BigInteger("$b"),
        "Division ($a / $b)"
    )
}

@Test
fun negationTest() {

    assertEquals(
        BigInteger("-1"),
        -BigInteger("1")
    )
    assertEquals(
        BigInteger("1"),
        -(-BigInteger("1"))
    )
}

@Test
fun toStringTest() {

    assertEquals(
        "0",
        ZERO.toString()
    )
    assertEquals(
        "14",
        BigInteger("14").toString()
    )
    assertEquals(
        "-84",
        BigInteger("-84").toString()
    )
}
