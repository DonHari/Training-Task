package untitled3

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MessageController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Message.list(params), model: [messageCount: Message.count()]
    }

    def show(Message message) {
        respond message
    }

    def create() {
        respond new Message(params)
    }

    @Transactional
    def save(Message userMessage) {
        userMessage.createdAt = new Date()
        if (userMessage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userMessage.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userMessage.errors, view: 'create'
            return
        }

        userMessage.save flush: true


        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'message.label', default: 'Message'), userMessage.id])
                redirect userMessage
            }
            '*' { respond userMessage, [status: CREATED] }
        }
    }

    def edit(Message message) {
        respond message
    }

    @Transactional
    def update(Message userMessage) {
        if (userMessage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userMessage.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userMessage.errors, view: 'edit'
            return
        }

        userMessage.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'message.label', default: 'Message'), userMessage.id])
                redirect message
            }
            '*' { respond message, [status: OK] }
        }
    }

    @Transactional
    def delete(Message userMessage) {

        if (userMessage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        userMessage.delete flush: true

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
