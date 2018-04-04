package untitled3

import grails.test.mixin.*
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import spock.lang.*

@TestFor(UserController)
@Mock(User)
class UserControllerSpec extends Specification {

    def "should return list of users"() {
        given:
            User user1 = new User(username: "test1", password: "test1", name: "test1")
            User user2 = new User(username: "test2", password: "test2", name: "test2")
            User user3 = new User(username: "test3", password: "test3", name: "test3")
            controller.userService = Mock(UserService) {
                1 * index(_) >> [user1, user2, user3]
            }
        when:
            controller.index()
        then:
            model.userList
            model.userList.size() == 3
            model.userList.get(0) == user1
            model.userList.get(1) == user2
            model.userList.get(2) == user3
            model.userCount == 3
    }

    def "should return given user"() {
        given:
            User user = new User(username: "test1", password: "test1", name: "test1")
        when:
            controller.show(user)
        then:
            model.user == user
    }

    def "should return new user"() {
        when:
            controller.create()
        then:
            model.user
    }

    def "should save given user"() {
        given:
            User user = new User(username: "test1", password: "test1", name: "test1", id: 1)
            controller.userService = Mock(UserService) {
                1 * save(_) >> user
            }
        when:
            request.method = 'POST'
            controller.save(user)
        then:
            model.user == user
            view == '/user/show'
    }

    def "should subscribe to given user"() {
        given:
            User user = new User(username: "subscriber", password: "subscriber", name: "subscriber", id: 1)
            controller.userService = Mock(UserService) {
                1 * subscribe(_) >> user
            }
        when:
            request.method = 'POST'
            controller.subscribe(user)
        then:
            model.user == user
            view == '/user/show'
    }

    def "should return error on subscription fail"() {
        given:
            User user = new User(username: "subscriber", password: "subscriber", name: "subscriber", id: 1)
            controller.userService = Mock(UserService) {
                1 * subscribe(_) >> null
            }
        when:
            request.method = 'POST'
            controller.subscribe(user)
        then:
            model.errorMessage == "You can't subscribe yourself!"
            view == '/customError'
    }
}
