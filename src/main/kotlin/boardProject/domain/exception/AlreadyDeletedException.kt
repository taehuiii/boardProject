package boardProject.domain.exception

data class AlreadyDeletedException(
    override val message: String? = "already deleted item",
    val value: Long?,
) : RuntimeException("$message with id $value")