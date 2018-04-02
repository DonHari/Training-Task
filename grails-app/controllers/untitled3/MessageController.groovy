package untitled3

import grails.plugin.springsecurity.annotation.Secured

import java.nio.file.AccessDeniedException

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional
class MessageController {

    MessageService messageService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        List<Message> messages = messageService.index(max)
        respond(messages, model: [messageCount: messages.size()])
    }

    def show(Message message) {
        respond(message)
    }

    @Secured(value = ["ROLE_USER"])
    def create() {
        respond(new Message(params))
    }

    @Secured(value = ['ROLE_USER'])
    def save(Message userMessage) {
        Message localMessage = messageService.save(userMessage)

        redirect(controller: "message", action: "show", params: [message: localMessage], status: CREATED, id: localMessage.id)
    }

    @Secured(value = ["ROLE_USER", "ROLE_ADMIN"])
    def edit(Message message) {
        respond(message)
    }

    @Secured(value = ["ROLE_USER"])
    def update(Message userMessage) {
        Message localMessage = messageService.save(userMessage)

        redirect(controller: "message", action: "show", params: [message: localMessage], status: OK, id: localMessage.id)
    }

    @Secured(value = ["ROLE_USER", "ROLE_ADMIN"])
    def delete(Message userMessage) {
        messageService.delete(userMessage)

        redirect(controller: "message", action: "index", method: "GET", status: NO_CONTENT)
    }

    protected void notFound() {
        redirect(controller: "message", action: "index", method: "GET", status: NOT_FOUND)
    }

    //todo figure out why this doesn't work?
    def accessDeniedException(final AccessDeniedException exception) {
        render(view: 'customError', model: [message: exception.message])
    }

}
