package untitled3

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    UserService userService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", subscribe: "POST"]

    @Secured(value = ['ROLE_USER'])
    def subscribe(User user) {
        User user1 = userService.subscribe(user)
        if (user1) {
            respond(user1, view: '/user/show', status: OK)
        } else {
            render(view: '/customError', model: ['errorMessage': "You can't subscribe yourself!"])
        }
    }

    @Secured(value = ['IS_AUTHENTICATED_ANONYMOUSLY'])
    def index(Integer max) {
        List<User> users = userService.index(max)
        respond(users, model: [userCount: users.size()])
    }

    @Secured(value = ['IS_AUTHENTICATED_ANONYMOUSLY'])
    def show(User user) {
        respond(user)
    }

    @Secured(value = ['IS_AUTHENTICATED_ANONYMOUSLY'])
    def create() {
        respond(new User(params))
    }

    @Transactional
    @Secured(value = ['IS_AUTHENTICATED_ANONYMOUSLY'])
    def save(User user) {
        User localUser = userService.save(user)

        respond(localUser, status: CREATED, view: "/user/show")
    }
}
