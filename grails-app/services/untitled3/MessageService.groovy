package untitled3

import grails.transaction.Transactional
import grails.web.servlet.mvc.GrailsParameterMap

import static org.springframework.http.HttpStatus.NOT_FOUND

@Transactional
//todo interface
class MessageService {

    def serviceMethod() {

    }

    def index(Integer max, GrailsParameterMap params){
        params.max = Math.min(max ?: 10, 100)
        List<Message> messages = Message.list(params)
        messages.sort()
    }

    def save(Message message) {
        message.createdAt = new Date()

        message.save()
    }

    def delete(Message message){
        if (message == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        message.delete()
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'message.label', default: 'Message'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
