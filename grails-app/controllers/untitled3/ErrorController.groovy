package untitled3

import org.springframework.web.servlet.ModelAndView

import java.nio.file.AccessDeniedException

class ErrorController {

    def index() {}

    def accessDenied(AccessDeniedException exception) {
        println "access denied"
        new ModelAndView("/customError", [message: exception.message])
    }
}
