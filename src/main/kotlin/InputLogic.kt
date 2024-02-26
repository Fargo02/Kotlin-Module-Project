import java.util.Scanner
class InputLogic {
    private var input = 0
    private var currentElement: Archive = Archive("name", mutableListOf<Note>()) // создаю архив пустышку
    fun displayArchive(storage: ArchiveStorage<String>) {
        while (true) {
            checkingIntInput()
            when (input) {
                0 -> {
                    println("Введите название архива")
                    val nameInput = checkingStrInput()
                    storage.addArchive(nameInput)
                    storage.displayTheScreenForArchive(storage.getArchives())
                }
                storage.archiveSize(storage.getArchives()) -> {
                    storage.watch = 0
                    break
                }
                else -> {
                    storage.watch = 2
                    currentElement = storage.getArchives().get(input - 1)
                    storage.displayTheScreenForNote(storage.getAllNotesFromArchive(currentElement), currentElement)
                    break
                }
            }
        }
    }

    fun displayNote(storage: ArchiveStorage<String>) {
        while (true) {
            checkingIntInput()
            when (input) {
                0 -> {
                    println("Введите название заметки")
                    val nameInput = checkingStrInput()
                    println("Введите содержимое заметки")
                    val contentInput = checkingStrInput()

                    val note = Note(nameInput, contentInput)
                    storage.addNote(note, currentElement)
                    storage.displayTheScreenForNote(storage.getAllNotesFromArchive(currentElement), currentElement)
                }
                storage.archiveSize(currentElement.notes) -> { //сравниваю с текущим архивом
                    storage.watch = 1
                    break
                }
                else -> {
                    val notes = storage.getAllNotesFromArchive(currentElement)
                    val currentNote = notes.get(input - 1)
                    println("Заметка ${currentNote.title}")
                    println(currentNote.content)
                    storage.displayTheScreenForNote(storage.getAllNotesFromArchive(currentElement), currentElement)
                }
            }
        }
    }
    private fun checkingIntInput() {
        while (true) {
            val inputStr = Scanner(System.`in`).nextLine()

            if (inputStr.isNullOrEmpty() || inputStr.all { it.isWhitespace() }) {
                println("Некорректный ввод: введите число")
                continue
            }

            if (inputStr.toIntOrNull() == null) {
                println("Некорректный ввод: введите число")
                continue
            }
            input = inputStr.toInt()

            if (input < 0) {
                println("Некорректный ввод: число должно быть неотрицательным")
                continue
            }
            break
        }
    }
    private fun checkingStrInput(): String {
        while (true) {
            val inputStr = Scanner(System.`in`).nextLine()

            if (inputStr.isNullOrEmpty() || inputStr.all { it.isWhitespace() }) {
                println("Некорректный ввод")
                continue
            } else {
                return inputStr
            }
        }
    }
}