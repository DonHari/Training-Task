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

        respond(localRole, status: CREATED, view: "/role/show")
    }
}
