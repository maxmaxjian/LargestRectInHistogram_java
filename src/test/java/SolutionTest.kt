import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class SolutionTest(
    private val expected: Int,
    private val input: IntArray
) {
    private val soln = Solution2()

    companion object {
        @Parameterized.Parameters
        @JvmStatic
        fun params() = arrayOf(
            arrayOf(10, intArrayOf(2,1,5,6,2,3)),
            arrayOf(3, intArrayOf(2,1,2)),
            arrayOf(6, intArrayOf(4,2,0,3,2,5)),
            arrayOf(0, intArrayOf(0))
        )
    }

    @Test
    fun test() = assertEquals(expected, soln.largestRectangleArea(input))
}