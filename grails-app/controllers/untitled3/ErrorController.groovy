package untitled3

import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException

class ErrorController {

    def index() {
        Exception exception = request.exception.cause

        String message = exception.message ?: "There are some errors. Try again later"
        HttpStatus responseStatus
        if (exception instanceof AccessDeniedException) {
            responseStatus = HttpStatus.FORBIDDEN
        } else if (exception instanceof AlreadyExistsException) {
            responseStatus = HttpStatus.BAD_REQUEST
        } else {
            responseStatus = HttpStatus.INTERNAL_SERVER_ERROR
        }
//        response.sendError(responseStatus.value())
        render(view: "/customError", model: [errorMessage: message], status: responseStatus)
    }
}
