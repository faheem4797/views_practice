import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch {
//            delay(5000L)
            println("Inside coroutine after delay 1")
        }
        println("Inside runBlocking before coroutine")


        coroutineScope {launch {
//            delay(5000L)
            println("Inside coroutine after delay 2")
        }
        }


    }
    println("Outside runBlocking")
}