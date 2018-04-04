package untitled3

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder
import spock.lang.Specification

@Mock([Message, User])
@TestFor(MessageServiceImplService)
class MessageServiceImplServiceSpec extends Specification {

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
            Message message = new Message(content: "some content")
            User user = new User(username: "test", name: "test", password: "test", id: 23)
            service.securityService = Mock(SecurityService) {
                1 * getAuthorizedUser() >> user
            }
        when:
            service.save(message)
        then:
            Message.count == 1
            Message.get(1) == message
            Message.get(1).author == user
    }

    void "should delete message"() {
        setup:
            User user = new User(username: "delete test", password: "test", name: "test").save()
            Message message1 = new Message(content: "some content", createdAt: new Date(3), author: user).save()
            Message message2 = new Message(content: "some content", createdAt: new Date(2), author: user).save()
            Message message3 = new Message(content: "some content", createdAt: new Date(1), author: user).save()
            service.securityService = Mock(SecurityService) {
                1 * getAuthorizedUser() >> user
            }
        when:
            service.delete(message2)
        then:
            Message.count == 2
            Message.get(1) == message1
            Message.get(3) == message3
    }

    void "shouldn't delete message because request not from author"() {
        setup:
            User user = new User(username: "test", password: "test", name: "test").save()
            User author = new User(username: "author", password: "author", name: "author").save()
            Message message1 = new Message(content: "some content", createdAt: new Date(3), author: author).save()
            Message message2 = new Message(content: "some content", createdAt: new Date(2), author: author).save()
            Message message3 = new Message(content: "some content", createdAt: new Date(1), author: author).save()
            service.securityService = Mock(SecurityService) {
                1 * getAuthorizedUser() >> user
            }
        when:
            service.delete(message2)
        then:
            thrown(AccessDeniedException)
    }
}
