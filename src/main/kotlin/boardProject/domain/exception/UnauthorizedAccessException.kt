package boardProject.domain.exception

data class UnauthorizedAccessException(
    override val message: String? = "unauthorized",
    val itemId: Long,
    val memberId: Any? = "access",
) : RuntimeException(
    "$message : $memberId is not authorized for $itemId"
)
