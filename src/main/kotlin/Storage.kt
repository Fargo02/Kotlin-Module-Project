interface Storage<T> {
    fun addArchive (archiveName: String): Archive
    fun addNote(note: Note, currentArchive: Archive)
    fun getAllNotesFromArchive(currentArchive: Archive): MutableList<Note>
    fun getArchives(): MutableList<Archive>
}