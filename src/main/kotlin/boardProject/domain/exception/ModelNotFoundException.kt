import java.io.Serial

class ModelNotFoundException : RuntimeException {

    @Serial
    private val serialVersionUID: Long = 12345678912345678L

    val modelName: String
    val value: Any?

    constructor(modelName: String, value: Any?) : super("$modelName not found with $value") {
        this.modelName = modelName
        this.value = value
    }

    constructor(modelName: String, value: Any?, cause: Throwable) : super("$modelName not found with $value", cause) {
        this.modelName = modelName
        this.value = value
    }

    constructor(
        modelName: String,
        value: Any?,
        cause: Throwable,
        enableSuppression: Boolean, //예외 억제 여부
        writableStackTrace: Boolean //예외 발생 위치 및 경로 표시 여부
    )
            : super("$modelName not found with $value", cause, enableSuppression, writableStackTrace) {
        this.modelName = modelName
        this.value = value
    }

    constructor(cause: Throwable) : super(cause) {
        this.modelName = ""
        this.value = null
    }

    constructor() : super() {
        this.modelName = ""
        this.value = null
    }
}