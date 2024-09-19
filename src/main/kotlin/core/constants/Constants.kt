package core.constants

object Constants {

    fun String.capFirstWord(): String {
        val text = drop(1).lowercase()
        return take(1) + text
    }
}