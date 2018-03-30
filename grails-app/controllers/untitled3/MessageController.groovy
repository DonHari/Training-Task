package untitled3

import grails.plugin.springsecurity.annotation.Secured
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional
class MessageController {

    MessageService messageService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        def messages = messageService.index(max)
        respond messages, model: [messageCount: messages.size()]
    }

    def show(Message message) {
        respond message
    }

    @Secured(value = ["ROLE_USER"])
    def create() {
        respond new Message(params)
    }

    @Secured(value = ['ROLE_USER'])
    def save(Message userMessage) {
        Message localMessage = messageService.save(userMessage)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'message.label', default: 'Message'), localMessage.id])
                redirect localMessage
            }
            '*' { respond localMessage, [status: CREATED] }
        }
    }

    @Secured(value = ["ROLE_USER", "ROLE_ADMIN"])
    def edit(Message message) {
        respond message
    }

    def update(Message userMessage) {
        Message localMessage = messageService.save(userMessage)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'message.label', default: 'Message'), localMessage.id])
                redirect localMessage
            }
            '*' { respond localMessage, [status: OK] }
        }
    }

    @Secured(value = ["ROLE_USER", "ROLE_ADMIN"])
    def delete(Message userMessage) {

        messageService.delete(userMessage)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'message.label', default: 'Message'), userMessage.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
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
