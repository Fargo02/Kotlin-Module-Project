import java.util.Scanner

fun main(args: Array<String>) {
    val storage = ArchiveStorage<String>()
    val logic = InputLogic()
    while (true) {
        when (storage.watch) { // Переменная storage.watch указывает в какой части программы находится пользователь
            1 -> {
                storage.displayTheScreenForArchive(storage.getArchives())
                logic.displayArchive(storage)
            }
            2 -> {
                logic.displayNote(storage)
            }
            else -> break
        }
    }
    println("До свидания!")
}

