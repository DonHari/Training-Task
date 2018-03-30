package untitled3

import grails.test.mixin.TestFor
import spock.lang.Specification


@TestFor(Message)
class MessageSpec extends Specification {


    void "should sort messages"() {
        setup:
            User user1 = new User(id: 1)
            User user2 = new User(id: 2)
            List<Message> messages = new LinkedList<>()
            messages.add(new Message(createdAt: new Date(2016, 1, 2), author: user1))
            messages.add(new Message(createdAt: new Date(2016, 1, 1), author: user2))
            messages.add(new Message(createdAt: new Date(2016, 1, 2), author: user2))
            messages.add(new Message(createdAt: new Date(2016, 1, 4), author: user1))

            List<Message> expectedMessages = new LinkedList<>()
            expectedMessages.add(new Message(createdAt: new Date(2016, 1, 4), author: user1))
            expectedMessages.add(new Message(createdAt: new Date(2016, 1, 2), author: user1))
            expectedMessages.add(new Message(createdAt: new Date(2016, 1, 2), author: user2))
            expectedMessages.add(new Message(createdAt: new Date(2016, 1, 1), author: user2))

        expect:
            messages.sort() == expectedMessages
    }

    void "should sort messages with same user"() {
        setup:
            User user = new User(id: 1)
            List<Message> messages = new LinkedList<>()
            messages.add(new Message(createdAt: new Date(2016, 1, 1), author: user))
            messages.add(new Message(createdAt: new Date(2016, 2, 1), author: user))
            messages.add(new Message(createdAt: new Date(2016, 5, 1), author: user))
            messages.add(new Message(createdAt: new Date(2016, 3, 1), author: user))
            messages.add(new Message(createdAt: new Date(2016, 4, 1), author: user))

            List<Message> expectedMessages = new LinkedList<>()
            expectedMessages.add(new Message(createdAt: new Date(2016, 5, 1), author: user))
            expectedMessages.add(new Message(createdAt: new Date(2016, 4, 1), author: user))
            expectedMessages.add(new Message(createdAt: new Date(2016, 3, 1), author: user))
            expectedMessages.add(new Message(createdAt: new Date(2016, 2, 1), author: user))
            expectedMessages.add(new Message(createdAt: new Date(2016, 1, 1), author: user))

        expect:
            messages.sort() == expectedMessages
    }
}
