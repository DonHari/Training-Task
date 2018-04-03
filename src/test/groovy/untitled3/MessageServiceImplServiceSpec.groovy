package untitled3

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import spock.lang.Specification

@Mock([Message, User])
@TestFor(MessageServiceImplService)
class MessageServiceImplServiceSpec extends Specification {

    def setup() {
        User authUser = new User(username: "auth", password: "auth", name: "auth").save()
        UserDetails userDetails = new UserDetails(
                authUser.username,
                authUser.password,
                authUser.enabled,
                !authUser.accountExpired,
                !authUser.passwordExpired,
                !authUser.accountLocked,
                new LinkedList<GrantedAuthority>(),
                authUser.id,
                authUser.name)
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null)
        SecurityContextHolder.getContext().setAuthentication(authentication)
    }


    void "should invoke index method"() {
        setup:
            User user = new User(username: "test", password: "test", name: "test").save()
            Message message1 = new Message(content: "some content", createdAt: new Date(3), author: user).save()
            Message message2 = new Message(content: "some content", createdAt: new Date(2), author: user).save()
            Message message3 = new Message(content: "some content", createdAt: new Date(1), author: user).save()
        when:
            List<Message> messages = service.index(0)
        then:
            messages.size() == 3
            messages[0] == message1
            messages[1] == message2
            messages[2] == message3
    }


    void "should invoke index method with max parameter"() {
        setup:
            int max = 2
            User user = new User(username: "test", password: "test", name: "test").save()
            Message message1 = new Message(content: "some content", createdAt: new Date(3), author: user).save()
            Message message2 = new Message(content: "some content", createdAt: new Date(2), author: user).save()
            Message message3 = new Message(content: "some content", createdAt: new Date(1), author: user).save()
        when:
            List<Message> messages = service.index(max)
        then:
            messages.size() == max
            messages[0] == message1
            messages[1] == message2
    }


    void "should save message"() {
        setup:
            User user = new User(username: "save test", password: "save test", name: "save test").save()
            Message message = new Message(content: "some content", createdAt: new Date(), author: user)
        when:
            service.save(message)
        then:
            Message.count == 1
            Message.get(1) == message
        cleanup:
            SecurityContextHolder.getContext().setAuthentication(null)
    }


    void "should delete message"() {
        setup:
            SecurityContextHolder.getContext().setAuthentication(null)//clear auth token
            User user = new User(username: "delete test", password: "test", name: "test").save()
            UserDetails userDetails = new UserDetails(
                    user.username,
                    user.password,
                    user.enabled,
                    !user.accountExpired,
                    !user.passwordExpired,
                    !user.accountLocked,
                    new LinkedList<GrantedAuthority>(),
                    user.id,
                    user.name)
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null)
            SecurityContextHolder.getContext().setAuthentication(authentication)
            Message message1 = new Message(content: "some content", createdAt: new Date(3), author: user).save()
            Message message2 = new Message(content: "some content", createdAt: new Date(2), author: user).save()
            Message message3 = new Message(content: "some content", createdAt: new Date(1), author: user).save()
        when:
            service.delete(message2)
        then:
            Message.count == 2
            Message.get(1) == message1
            Message.get(3) == message3
        cleanup:
            SecurityContextHolder.getContext().setAuthentication(null)

    }


    void "shouldn't delete message because request not from author"() {
        setup:
            User user = new User(username: "test", password: "test", name: "test").save()
            Message message1 = new Message(content: "some content", createdAt: new Date(3), author: user).save()
            Message message2 = new Message(content: "some content", createdAt: new Date(2), author: user).save()
            Message message3 = new Message(content: "some content", createdAt: new Date(1), author: user).save()
        when:
            service.delete(message2)
        then:
            thrown(AccessDeniedException)
        cleanup:
            SecurityContextHolder.getContext().setAuthentication(null)
    }
}
