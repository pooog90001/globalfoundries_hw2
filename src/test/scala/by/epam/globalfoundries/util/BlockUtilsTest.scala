package by.epam.globalfoundries.util

import org.scalatest.FunSuite

class BlockUtilsTest extends FunSuite {

  test("should compute sorted list and return total 23") {
    var list = List[PriorityBlock](
      PriorityBlock(1, 0, 3),
      PriorityBlock(1, 2, 7),
      PriorityBlock(1, 9, 14),
      PriorityBlock(1, 11, 25),
      PriorityBlock(1, 13, 18),
      PriorityBlock(1, 17, 21)
    )

    val expectedTotalBlockingTime = Map(1 -> 23)
    val  actualTotalBlockingTime = BlockUtils.getTotalBlockingTime(list)

    assert(actualTotalBlockingTime === expectedTotalBlockingTime)
  }

  test("should compute unsorted list and return total 19") {
    var list = List[PriorityBlock](
      PriorityBlock(2, 0, 3),
      PriorityBlock(2, 2, 7),
      PriorityBlock(2, 9, 14),

      PriorityBlock(3, 0, 3),
      PriorityBlock(3, 2, 7),
      PriorityBlock(3, 9, 14),

      PriorityBlock(1, 0, 3),
      PriorityBlock(1, 2, 7),
      PriorityBlock(1, 9, 14),

      PriorityBlock(3, 11, 25),
      PriorityBlock(3, 13, 18),
      PriorityBlock(3, 17, 21),

      PriorityBlock(2, 11, 25),
      PriorityBlock(2, 13, 18),
      PriorityBlock(2, 17, 21),

      PriorityBlock(1, 11, 22),
      PriorityBlock(1, 13, 18),
      PriorityBlock(1, 17, 21)
    )

    val expectedTotalBlockingTime = Map(1 -> 20, 2 -> 23, 3 -> 23)
    val  actualTotalBlockingTime = BlockUtils.getTotalBlockingTime(list)

    assert(actualTotalBlockingTime  === expectedTotalBlockingTime)
  }

  test("should compute unsorted list and return total 3") {
    var list = List[PriorityBlock](
      PriorityBlock(1, 0, 2),
      PriorityBlock(4, 0, 3),
      PriorityBlock(5, 0, 2),
      PriorityBlock(6, 0, 1)
    )

    val expectedTotalBlockingTime = Map(1 -> 2, 4 -> 3, 5 -> 2, 6 -> 1)
    val  actualTotalBlockingTime = BlockUtils.getTotalBlockingTime(list)

    assert(actualTotalBlockingTime === expectedTotalBlockingTime)
  }

  test("should compute empty list and return total 0") {
    var list = List[PriorityBlock]()

    val expectedTotalBlockingTime = Map()
    val  actualTotalBlockingTime = BlockUtils.getTotalBlockingTime(list)

    assert(actualTotalBlockingTime === expectedTotalBlockingTime)
  }
}
