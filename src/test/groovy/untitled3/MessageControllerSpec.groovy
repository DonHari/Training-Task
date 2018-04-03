package untitled3

import grails.test.mixin.*
import spock.lang.*

@TestFor(MessageController)
@Mock([Message])
class MessageControllerSpec extends Specification {

    def "index should return correct model"() {
        given:
            User user = new User(id: 1, username: "test username", password: "test password")
            Message message1 = new Message(content: "test content 1", createdAt: new Date(1), author: user)
            Message message2 = new Message(content: "test content 2", createdAt: new Date(2), author: user)
            Message message3 = new Message(content: "test content 3", createdAt: new Date(3), author: user)
            List<Message> messages = [message1, message2, message3]
            controller.messageService = Stub(MessageService){
                index(_) >> messages
            }

        when:
            controller.index()

        then:
            model.messageList
            model.messageList.size() == 3
            model.messageList.get(0) == message1
            model.messageList.get(1) == message2
            model.messageList.get(2) == message3
            model.messageCount == 3
    }

    def "should return message"() {
        given:
            Message message = new Message(content: "test content", createdAt: new Date(1), author: new User(id: 1, username: "test", password: "test", name: "test"))
        when:
            controller.show(message)
        then:
            model.message == message
    }

    def "should create new message"() {
        when:
            controller.create()
        then:
            model.message
    }

    def "should save new message"() {
        given:
            String content = "test content"
            User user = new User(id: 1, username: "test username", password: "test password")
            Message message = new Message(content: content, createdAt: new Date(1), author: user, id: 1)
            controller.messageService = Stub(MessageService) {
                save(_) >> message
            }
        when:
            request.method = 'POST'
            controller.save(message)
        then:
            model.message == message
            view == '/message/show'
    }

    def "should return message to edit"() {
        given:
            Message message = new Message(content: "test content", createdAt: new Date(1), author: new User(id: 1, username: "test", password: "test", name: "test"))
        when:
            controller.edit(message)
        then:
            model.message == message
    }

    def "should update message"() {
        given:
            String newContent = "new content"
            controller.messageService = Mock(MessageService) {
                1 * save(_) >> new Message(content: newContent, createdAt: new Date(1), author: new User(id: 1, username: "test", password: "test", name: "test"), id: 1)
            }
        when:
            request.method = 'PUT'
            controller.update()
        then:
            model.message.content <=> newContent == 0
            view == '/message/show'
    }

    def "should delete given message"() {
        setup:
            Message message = new Message(content: "test content", createdAt: new Date(1), author: new User(id: 1, username: "test", password: "test", name: "test"))
            controller.messageService = Mock(MessageService)
        when:
            request.method = 'DELETE'
            controller.delete(message)
        then:
            1 * controller.messageService.delete(_)
    }
}
