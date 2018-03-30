package untitled3

import grails.transaction.Transactional
import grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder


import static org.springframework.http.HttpStatus.NOT_FOUND

@Transactional
//todo interface
class MessageService {

    def index(Integer max){
        Integer localMax = Math.min(max ?: 10, 100)
        List<Message> messages = Message.findAll(max: localMax)
        messages.sort()
    }

    def save(Message message) {
        message.createdAt = new Date()
        UserDetails userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal() as UserDetails
        message.author = User.get(userDetails.id as Long)

        message.save()
    }

    def delete(Message message){
        if (message == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        UserDetails authorizedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal() as UserDetails
        if(authorizedUser.id == message.authorId) {
            message.delete()
        } else {
            throw new AccessDeniedException("You can't delete this message")
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
