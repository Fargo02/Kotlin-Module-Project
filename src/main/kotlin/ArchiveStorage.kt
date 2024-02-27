class ArchiveStorage<T> : Storage<T> {
    var watch = Watch.ARCHIVE // переменная для понимания в какой части программы работает пользователь
    private val archives = mutableListOf<Archive>()
    override fun addArchive(archiveName: String): Archive {
        archives.add(Archive(archiveName, mutableListOf()))
        return Archive(archiveName, mutableListOf())
    }

    override fun addNote(note: Note, currentArchive: Archive) {
        currentArchive.notes.add(note)
    }

    override fun getAllNotesFromArchive(currentArchive: Archive): MutableList<Note> {
        return currentArchive.notes
    }

    override fun getArchives(): MutableList<Archive> {
        return archives
    }
    internal fun <E> listSize(list: MutableList<E>):Int {
        return list.size + 1 // +1, потому что в интерфейсе всегда на 1 больше элементов, чем размер списка
    }
    /*
    Хотел записать все в один метод, но что-то не получается :(
    fun displayTheScreen(list: MutableList<T> ) {
        when (list) {
            is MutableList<Archive> -> println("Список архивов:\n0. Создать архив")
            is MutableList<Note> -> println("Список архивов:\n0. Создать заметку")
        }
        list.forEachIndexed { index, element -> println("$index. $element") }
        println("${list.size + 1}. Выход")
    }
     */
    fun displayTheScreenForArchive(list: MutableList<Archive> ) {
        println("Список архивов:\n0. Создать архив")
        list.forEachIndexed { index, element -> println("${index + 1}. ${element.name}") }
        println("${list.size + 1}. Выход")
    }
    fun displayTheScreenForNote(list: MutableList<Note>, currentArchive: Archive) {
        println("Список заметок в архиве ${currentArchive.name}:\n0. Создать заметку")
        list.forEachIndexed { index, element -> println("${index + 1}. ${element.title}") }
        println("${list.size + 1}. Выход")
    }
}
