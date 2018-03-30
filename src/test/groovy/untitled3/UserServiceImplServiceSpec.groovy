package untitled3

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@Mock([User])
@TestFor(UserServiceImplService)
class UserServiceImplServiceSpec extends Specification {

    void "should invoke index method"() {
        setup:
            User user1 = new User(username: "test user 1", password: "test", name: "test").save()
            User user2 = new User(username: "test user 2", password: "test", name: "test").save()
            User user3 = new User(username: "test user 3", password: "test", name: "test").save()
        when:
            List<User> users = service.index(0)
        then:
            users.size() == 3
            users[0] == user1
            users[1] == user2
            users[2] == user3
    }

    void "should invoke index method with max parameter"() {
        setup:
            int max = 2
            User user1 = new User(username: "test user 1", password: "test", name: "test").save()
            User user2 = new User(username: "test user 2", password: "test", name: "test").save()
            User user3 = new User(username: "test user 3", password: "test", name: "test").save()
        when:
            List<User> users = service.index(max)
        then:
            users.size() == max
            users[0] == user1
            users[1] == user2
    }

    void "should save user"() {
        setup:
            User user = new User(username: "save test", password: "test", name: "test")
        when:
            service.save(user)
        then:
            User.count == 1
            User.get(1) == user
    }

    void "should save user with existing username"() {
        setup:
            User user1 = new User(username: "test user 1", password: "test", name: "test").save()
            User user = new User(username: "test user 1", password: "test", name: "test")
        when:
            service.save(user)
        then:
            User.count == 1
    }
}
