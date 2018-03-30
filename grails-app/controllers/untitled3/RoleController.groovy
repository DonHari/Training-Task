package untitled3

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RoleController {

    RoleService roleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(value = ['ROLE_ADMIN'])
    def index(Integer max) {
        roleService.index(max)
    }

    def show(Role role) {
        respond role
    }

    def create() {
        respond new Role(params)
    }

    @Transactional
    def save(Role role) {

        roleService.save(role)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'role.label', default: 'Role'), role.id])
                redirect role
            }
            '*' { respond role, [status: CREATED] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'role.label', default: 'Role'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
