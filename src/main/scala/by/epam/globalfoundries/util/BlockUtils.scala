package by.epam.globalfoundries.util

object BlockUtils {

  /*
  * Just sort list of blocks by timeStart field
  */
  def sortByTimeStart(listBlocks: List[PriorityBlock]) :List[PriorityBlock] = {
    listBlocks.sortBy(block => block.startTime)
  }

  /*
  * compute total blocking time by priority by task
  * return map: key - proirity; value - total time this priority
  */
  def getTotalBlockingTime(listBlocks: List[PriorityBlock]) : Map[Int, Int] = {
    val sortedListBlocks = sortByTimeStart(listBlocks)

    // map of total time and max end time per every priority
    var valuesByPriorities = Map[Int, (Int, Int)]()

    sortedListBlocks.foreach(block => {
      var totalTime = 0
      var maxEndTime = 0

      val isPriorityExist = valuesByPriorities.contains(block.priority)

      if (isPriorityExist) {
        var values = valuesByPriorities(block.priority)
        totalTime = values._1
        maxEndTime = values._2
      }

      if (maxEndTime <= block.startTime) {
        totalTime += block.endTime - block.startTime

      } else if (maxEndTime < block.endTime) {
        totalTime +=  block.endTime - maxEndTime
      }
      if (maxEndTime < block.endTime) {
        maxEndTime = block.endTime
      }
      valuesByPriorities += (block.priority -> (totalTime, maxEndTime))

    })

    valuesByPriorities.map{ case (priority, (totalTime,_)) => (priority, totalTime) }
  }



}
