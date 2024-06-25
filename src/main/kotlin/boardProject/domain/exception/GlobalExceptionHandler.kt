package boardProject.domain.exception

import boardProject.domain.exception.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {


    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFound(e: ModelNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(e.message))
    }

    @ExceptionHandler(ExistingNicknameException::class)
    fun handleInvalidRequest(e: ExistingNicknameException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse(e.message))
    }

    @ExceptionHandler(RecheckingPasswordFailedException::class)
    fun handleInvalidRequest(e: RecheckingPasswordFailedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(e.message))
    }

    @ExceptionHandler(InvalidNicknameException::class)
    fun handleInvalidRequest(e: InvalidNicknameException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(e.message))

    }

    @ExceptionHandler(InvalidPasswordException::class)
    fun handleInvalidRequest(e: InvalidPasswordException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(e.message))

    }

    @ExceptionHandler(AlreadyDeletedException::class)
    fun handleInvalidRequest(e: AlreadyDeletedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.GONE).body(ErrorResponse(e.message))
    }

    @ExceptionHandler(InvalidCredentialException::class)
    fun handleInvalidCredential(e: InvalidCredentialException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse(e.message))
    }


}
