package untitled3

import grails.transaction.Transactional
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder

@Transactional
class MessageServiceImplService implements MessageService {

    SecurityService securityService

    List<Message> index(Integer max) {
        Integer localMax = Math.min(max ?: 10, 100)
        List<Message> messages = Message.findAll(max: localMax)
        messages.sort()
    }

    Message save(Message message) {
        message.createdAt = new Date()
        message.author = securityService.getAuthorizedUser()

        message.save()
    }

    def delete(Message message) {
        User authorizedUser = securityService.getAuthorizedUser()
        if (authorizedUser.id == message.authorId) {
            message.delete()
        } else {
            throw new AccessDeniedException("Only author can delete this message")
        }
    }
}
