import java.util.Scanner
class InputLogic {
    private var scanner = Scanner(System.`in`)
    private var input = 0
    private var currentElement: Archive = Archive("name", mutableListOf<Note>()) // создаю архив пустышку
    fun displayArchive(storage: ArchiveStorage<String>) {
        while (true) {
            checkingIntInput(storage)
            when (input) {
                0 -> {
                    println("Введите название архива")
                    val nameInput = checkingStrInput()
                    storage.addArchive(nameInput)
                    storage.displayTheScreenForArchive(storage.getArchives())
                }
                storage.listSize(storage.getArchives()) -> {
                    storage.watch = Watch.CLOSE
                    break
                }
                else -> {
                    storage.watch = Watch.NOTES
                    currentElement = storage.getArchives().get(input - 1)
                    storage.displayTheScreenForNote(storage.getAllNotesFromArchive(currentElement), currentElement)
                    break
                }
            }
        }
    }

    fun displayNote(storage: ArchiveStorage<String>) {
        while (true) {
            checkingIntInput(storage)
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
                storage.listSize(currentElement.notes) -> { //сравниваю с текущим архивом
                    storage.watch = Watch.ARCHIVE
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
    private fun checkingIntInput(storage: ArchiveStorage<String>) {
        while (true) {
            val inputStr = scanner.nextLine()

            when {
                inputStr.isNullOrEmpty() || inputStr.all { it.isWhitespace() } -> {
                    println("Некорректный ввод: введите число")
                    continue
                }

                inputStr.toIntOrNull() == null -> {
                    println("Некорректный ввод: введите число")
                    continue
                }
            }

            input = inputStr.toInt()

            when {
                input < 0 -> {
                    println("Некорректный ввод: число должно быть неотрицательным")
                    continue
                }
                input > storage.listSize(storage.getArchives()) && storage.watch == Watch.ARCHIVE -> {
                    println("Некорректный ввод: число не должно быть больше чем количество пунктов")
                    continue
                }
                input > storage.listSize(currentElement.notes) && storage.watch == Watch.NOTES -> {
                    println("Некорректный ввод: число не должно быть больше чем количество пунктов")
                    continue
                }
                else -> {
                    break
                }
            }
        }
    }
    private fun checkingStrInput(): String {
        while (true) {
            val inputStr = scanner.nextLine()
            when {
                (inputStr.isNullOrEmpty() || inputStr.all { it.isWhitespace() }) -> {
                    println("Некорректный ввод")
                    continue
                }
                else -> return inputStr
            }
        }
    }
}