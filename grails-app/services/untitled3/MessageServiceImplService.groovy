package untitled3

import grails.transaction.Transactional
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder

@Transactional
class MessageServiceImplService implements MessageService {

    List<Message> index(Integer max) {
        Integer localMax = Math.min(max ?: 10, 100)
        List<Message> messages = Message.findAll(max: localMax)
        messages.sort()
    }

    Message save(Message message) {
        message.createdAt = new Date()
        UserDetails userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal() as UserDetails
        message.author = User.get(userDetails.id as Long)

        message.save()
    }

    def delete(Message message) {
        UserDetails authorizedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal() as UserDetails
        if (authorizedUser.id == message.authorId) {
            message.delete()
        } else {
            throw new AccessDeniedException("Only author can delete this message")
        }
    }
}
