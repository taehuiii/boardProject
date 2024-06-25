package boardProject.domain.exception

data class AlreadyDeletedException(
    override val message: String? = "already deleted item",
    val itemId: Long?,
) : RuntimeException("$message with id $itemId")