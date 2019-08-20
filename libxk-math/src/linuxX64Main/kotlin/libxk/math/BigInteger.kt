package libxk.math

import kotlinx.cinterop.*
import native.gmp.*

@ExperimentalUnsignedTypes
actual class BigInteger : Comparable<BigInteger> {

    private val arena = Arena()
    private val mpz: __mpz_struct = arena.alloc()

    actual constructor(
        value: String,
        radix: Int
    ) {

        (mpz_init_set_str!!)(
            mpz.ptr,
            value.cstr.getPointer(arena),
            10
        )
    }

    private constructor() {

        (mpz_init!!)(mpz.ptr)
    }

    actual fun abs(): BigInteger =
        BigInteger().apply {
            (mpz_abs!!)(
                this@apply.mpz.ptr,
                this@BigInteger.mpz.ptr
            )
        }

    actual operator fun plus(other: BigInteger): BigInteger =
        if (other == ZERO) this
        else BigInteger().apply {
            (mpz_add!!)(
                this@apply.mpz.ptr,
                this@BigInteger.mpz.ptr,
                other.mpz.ptr
            )
        }

    actual operator fun unaryMinus(): BigInteger =
        BigInteger().apply {
            (mpz_neg!!)(
                this@apply.mpz.ptr,
                this@BigInteger.mpz.ptr
            )
        }

    actual operator fun minus(other: BigInteger): BigInteger =
        if (other == ZERO) this
        else BigInteger().apply {
            (mpz_sub!!)(
                this@apply.mpz.ptr,
                this@BigInteger.mpz.ptr,
                other.mpz.ptr
            )
        }

    actual operator fun times(other: BigInteger): BigInteger =
        when (other) {
            ZERO -> ZERO
            ONE  -> this
            else -> BigInteger().apply {
                (mpz_mul!!)(
                    this@apply.mpz.ptr,
                    this@BigInteger.mpz.ptr,
                    other.mpz.ptr
                )
            }
        }

    actual operator fun div(other: BigInteger): BigInteger =
        when (other) {
            ZERO -> throw DivideByZeroException
            ONE  -> this
            else -> BigInteger().apply {

                getQuotientOfDiv(
                    this@apply.mpz.ptr,
                    this@BigInteger.mpz.ptr,
                    other.mpz.ptr
                )
            }
        }

    actual operator fun rem(other: BigInteger): BigInteger =
        when (other) {
            ZERO -> throw DivideByZeroException
            ONE  -> this
            else -> BigInteger().apply {

                (mpz_mod!!)(
                    this@apply.mpz.ptr,
                    this@BigInteger.mpz.ptr,
                    other.mpz.ptr
                )
            }
        }

    override fun compareTo(other: BigInteger) =
        (mpz_cmp!!)(
            mpz.ptr,
            other.mpz.ptr
        )

    override fun equals(other: Any?) =
        other is BigInteger && this.compareTo(other) == 0

    override fun hashCode(): Int =
        31 * arena.hashCode() + mpz.hashCode()

    override fun toString() =
        (mpz_get_str!!)(
            null,
            10,
            mpz.ptr
        )?.toKString()
        ?: "NULL"

    actual companion object {

        actual val ZERO: BigInteger = BigInteger("0")
        actual val ONE: BigInteger = BigInteger("1")

        private fun getQuotientOfDiv(
            result: mpz_ptr,
            num: mpz_ptr,
            den: mpz_t
        ) {

            val arena = Arena()
            val rem = arena.alloc<__mpz_struct>()
            (mpz_mod!!)(
                rem.ptr,
                num,
                den
            )
            val exactDivisible = arena.alloc<__mpz_struct>()
            (mpz_sub!!)(
                exactDivisible.ptr,
                num,
                rem.ptr
            )
            (mpz_divexact!!)(
                result,
                exactDivisible.ptr,
                den
            )
            arena.clear()
        }
    }
}
