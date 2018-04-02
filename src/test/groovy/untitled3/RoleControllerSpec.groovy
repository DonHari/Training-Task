package untitled3

import grails.test.mixin.*
import spock.lang.*

@TestFor(RoleController)
@Mock(Role)
class RoleControllerSpec extends Specification {

    def "should return list of roles"() {
        given:
            Role role1 = new Role(authority: "ROLE_USER")
            Role role2 = new Role(authority: "ROLE_ADMIN")
            Role role3 = new Role(authority: "ROLE_MANAGER")
            Role role4 = new Role(authority: "ROLE_ONE_MORE")
            controller.roleService = Stub(RoleService) {
                index(_) >> [role1, role2, role3, role4]
            }
        when:
            controller.index()
        then:
            model.roleList
            model.roleList.size() == 4
            model.roleList.get(0) == role1
            model.roleList.get(1) == role2
            model.roleList.get(2) == role3
            model.roleList.get(3) == role4
            model.roleCount == 4
    }

    def "should return role"() {
        given:
            Role role = new Role(authority: "ROLE_ADMIN")
        when:
            controller.show(role)
        then:
            model.role == role
    }

    def "should create new role"() {
        when:
            controller.create()
        then:
            model.role
    }

    def "should save given role"() {
        given:
            Role role = new Role(authority: "ROLE_ADMIN", id: 1)
            controller.roleService = Stub(RoleService) {
                save(_) >> role
            }
        when:
            request.method = "POST"
            controller.save(role)
        then:
            model.role == role
            view == '/role/show'
    }
}
