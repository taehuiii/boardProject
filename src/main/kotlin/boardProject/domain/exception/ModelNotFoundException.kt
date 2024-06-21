package boardProject.domain.exception

data class ModelNotFoundException(val modelName: String, val value: Any) : RuntimeException(
    "$modelName not found with $value"
)

