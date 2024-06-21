package boardProject.domain.exception

data class ModelNotFoundException(val modelName: String, val value: Long) : RuntimeException(
    "$modelName not found with $value"
)