package libxk.math

expect class BigInteger(
    value: String,
    radix: Int = 10
) : Comparable<BigInteger> {

    fun abs(): BigInteger
    operator fun plus(other: BigInteger): BigInteger
    operator fun unaryMinus(): BigInteger
    operator fun minus(other: BigInteger): BigInteger
    operator fun times(other: BigInteger): BigInteger
    operator fun div(other: BigInteger): BigInteger
    operator fun rem(other: BigInteger): BigInteger

    companion object {

        val ZERO: BigInteger
        val ONE: BigInteger
    }
}
