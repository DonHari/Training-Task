package untitled3

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured(value = ['ROLE_ADMIN'])
@Transactional(readOnly = true)
class RoleController {

    RoleService roleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        List<Role> roles = roleService.index(max)
        respond(roles, model: [roleCount: roles.size()])
    }

    def show(Role role) {
        respond(role)
    }

    def create() {
        respond(new Role(params))
    }

    @Transactional
    def save(Role role) {

        Role localRole = roleService.save(role)

        redirect(controller: "role", action: "show", id: localRole.id, params: [role: localRole], status: CREATED)
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'role.label', default: 'Role'), role.id])
//                redirect role
//            }
//            '*' { respond role, [status: CREATED] }
//        }
    }

    protected void notFound() {
        redirect(controller: "role", action: "index", method: "GET", status: NOT_FOUND)
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.not.found.message', args: [message(code: 'role.label', default: 'Role'), params.id])
//                redirect action: "index", method: "GET"
//            }
//            '*' { render status: NOT_FOUND }
//        }
    }
}
