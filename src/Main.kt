/**
 * Kotlin Collections Task 3 - Monkey Errors
 *
 *       OOOOO  OOO   OOO
 *          O  O   O O   O
 *         O   O   O O   O
 *       OOOOO  OOO   OOO
 * +-------------+-------------+
 * |    __v__    |     ___     |
 * |   ( o o )   |    /   |    |
 * |    (---)    |       _/    |
 * |    __|__    |      |      |
 * |   /|. .|\   |      o      |
 * +-------------+-------------+
 *
 * It seems that some of the zookeepers are not very
 * good at their job, and keep getting the cage numbers
 * muddled up. Can you add some error checking to the
 * system to help indicate when things go wrong?
 */


/**
 * Constant vales used to define some key values
 * which can then be used throughout the code...
 */
const val NUMCAGES = 8      // The total number of cages
const val EMPTY = "---"     // Represents an empty cage


/**
 * Program entry point
 */
fun main() {
    //-------------------------------------------------
    println("Setting up the cages...")

    val cages = setupCages()

    showMonkeyCages(cages)
    println()

    //-------------------------------------------------
    println("Placing monkeys into cages...")

    placeMonkeyInCage(cages, 1, "Kevin")
    placeMonkeyInCage(cages, 8, "Sally")
    placeMonkeyInCage(cages, 4, "Pam")
    placeMonkeyInCage(cages, 3, "Samson")
    placeMonkeyInCage(cages, 5, "Frank")
    placeMonkeyInCage(cages, 6, "Jim")

    println()

    showMonkeyCages(cages)
    println("Monkeys: ${monkeyCount(cages)}")
    println("Empty: ${emptyCount(cages)}")
    println()

    //-------------------------------------------------
    println("Trying some invalid placements...")

    placeMonkeyInCage(cages, 0, "Nigel")
    placeMonkeyInCage(cages, NUMCAGES + 1, "Nigel")

    println()

    //-------------------------------------------------
    println("Trying some more invalid placements...")

    placeMonkeyInCage(cages, 2, "")
    placeMonkeyInCage(cages, 2, "        ")

    println()

    //-------------------------------------------------
    println("Trying to clear invalid cages...")

    clearCage(cages, 0)
    clearCage(cages, NUMCAGES + 1)

    println()

    //-------------------------------------------------
    println("Trying to do some invalid swaps...")

    swapCages(cages, 0, 0)
    swapCages(cages, 0, 1)
    swapCages(cages, 1, 0)

    swapCages(cages, NUMCAGES + 1, NUMCAGES + 1)
    swapCages(cages, NUMCAGES + 1, NUMCAGES)
    swapCages(cages, NUMCAGES, NUMCAGES + 1)
}

/**
 * Creates and returns a Mutable List, size NUMCAGES,
 * populated with strings representing empty cages
 */
fun setupCages(): MutableList<String> {
    val cageList = mutableListOf<String>()
    for (i in 1..NUMCAGES) cageList.add(EMPTY)
    return cageList
}


/**
 * Put a given monkey into the specified cage number (1...MAX)
 */
fun placeMonkeyInCage(cageList: MutableList<String>, cageNum: Int, name: String) {
    println("+++ Putting $name into cage $cageNum")

    if (cageNum < 1 || cageNum > NUMCAGES) {
        println("Cage $cageNum is out of bounds")
        return
    }

    if (name.isBlank()) {
        println("Name is blank!")

    }

    // Now safe to do this
    cageList[cageNum - 1] = name
}

/**
 * Returns the number of monkeys found in the given cage list
 */
fun monkeyCount(cageList: List<String>): Int {
    var count: Int = 0
    for ((i,cage) in cageList.withIndex()) {
        if (cage != EMPTY)  count ++
    }

    return count    // REPLACE THIS WITH YOUR CODE!
}


/**
 * Returns the number of cages that are empty in the given cage list
 */
fun emptyCount(cageList: List<String>): Int {
    var count: Int = 0
    for ((i,cage) in cageList.withIndex()) {
        if (cage == EMPTY)  count ++
    }

    return count // REPLACE THIS WITH YOUR CODE!
}


/**
 * Show all cages from the given list, formatted as a horizontal table:
 *
 * +--------+--------+--------+--------+----
 * | Cage 1 | Cage 2 | Cage 3 | Cage 4 | Etc.
 * +--------+--------+--------+--------+----
 * | Kevin  | ---    | Samson | Pam    | Etc.
 * +--------+--------+--------+--------+----
 *
 * Tip: the String.padEnd(N) function will help you here
 */
fun showMonkeyCages(cageList: List<String>) {
    var textLength: Int = cageList.max().toString().length + 3
    println("+" + ("${"-".repeat(textLength-1)}+".padEnd(textLength, ' ')).repeat(cageList.size))
    for ((i) in cageList.withIndex()) {
        print("| Cage ${i+1}".padEnd(textLength, ' '))
    }
    print("|\n")
    println("+" + ("${"-".repeat(textLength-1)}+".padEnd(textLength, ' ')).repeat(cageList.size))
    for ((_,monkey) in cageList.withIndex()) {
        print("| $monkey".padEnd(textLength, ' '))
    }
    print("|\n")
    println("+" + ("${"-".repeat(textLength-1)}+".padEnd(textLength, ' ')).repeat(cageList.size))
}


/**
 * Make a given cage empty (if a monkey was in it, it's gone now!)
 */
fun clearCage(cageList: MutableList<String>, cageNum: Int) {
    println("--- Clearing cage $cageNum")

    if (cageNum < 1 || cageNum > NUMCAGES) {
        println("Cage $cageNum is out of bounds")
        return
    }
    // Now safe to do this
    cageList[cageNum-1] = EMPTY // REPLACE THIS WITH YOUR CODE!
}


/**
 * Swap the contents of two given cages.
 *
 * If one was full and the other empty, then the monkey just swaps
 * into the empty cage.
 */
fun swapCages(cageList: MutableList<String>, cageNum1: Int, cageNum2: Int) {
    println("<-> Swapping cages $cageNum1 and $cageNum2")
    if ((cageNum1 < 1 || cageNum1 > NUMCAGES) || (cageNum2 < 1 || cageNum2 > NUMCAGES)) {
        println("Cage $cageNum1 or $cageNum2 is out of bounds")
        return
    }

    // Now safe to do this
    var cage1 = cageList[cageNum1-1]
    cageList[cageNum1-1] = cageList[cageNum2 - 1]
    cageList[cageNum2-1] = cage1 // REPLACE THIS WITH YOUR CODE!
}

//fun CheckForCage(cageList: MutableList<String>, cageNum: Int): Any {
//    if (cageNum - 1 > cageList.size - 1) {
//        return if (cageList[cageList.size - 1] == EMPTY) {
//            cageList.size - 1
//        } else {
//            "error"
//        }
//    } else if ((cageNum - 1) < 0) {
//        return if (cageList[0] == EMPTY) {
//            0
//        } else {
//            "error"
//        }
//    } else {
//        return if (cageList[cageNum - 1] == EMPTY) {
//            cageNum - 1
//        } else {
//            "error"
//        }
//    }
//}



/**
 * ========================================================
 * ABOVE THIS COMMENT, PLACE A COPY OF ALL THE FUNCTIONS
 * YOU WORKED ON AND GOT WORKING IN TASK 2
 * ========================================================
 *
 * You will be modifying the following functions to add
 * error-checking:
 * - placeMonkeyInCage()
 * - clearCage()
 * - swapCages()
 *
 * If an error is detected, a suitable error message should
 * be shown such as:
 * - ERROR PLACING MONKEY: cage number 20 is out of range
 * - ERROR CLEARING CAGE: cage number 0 is out of range
 * - Etc.
 *
 * ========================================================
 */