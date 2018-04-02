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
            redirect(controller: "user", action: "show", id: user1.id, params: [user: user1])
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

    //todo check if user unauthorized and put this code into service
    @Secured(value = ['IS_AUTHENTICATED_ANONYMOUSLY'])
    def create() {
        respond(new User(params))
    }

    @Transactional
    def save(User user) {
        userService.save(user)

        redirect(controller: "user", action: "show", id: user.id, params: [user: user])
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
//                redirect user
//            }
//            '*' { respond user, [status: CREATED] }
//        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
