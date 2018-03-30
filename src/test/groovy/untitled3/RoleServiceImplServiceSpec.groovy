package untitled3

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@Mock([Role])
@TestFor(RoleServiceImplService)
class RoleServiceImplServiceSpec extends Specification {

    void "should invoke index method"() {
        setup:
            Role role1 = new Role(authority: "ROLE_ADMIN").save()
            Role role2 = new Role(authority: "ROLE_USER").save()
            Role role3 = new Role(authority: "ROLE_SUPERUSER").save()
        when:
            List<Role> roles = service.index(0)
        then:
            roles.size() == 3
            roles[0] == role1
            roles[1] == role2
            roles[2] == role3
    }

    void "should invoke index method with max parameter"() {
        setup:
            int max = 2
            Role role1 = new Role(authority: "ROLE_ADMIN").save()
            Role role2 = new Role(authority: "ROLE_USER").save()
            Role role3 = new Role(authority: "ROLE_SUPERUSER").save()
        when:
            List<Role> roles = service.index(max)
        then:
            roles.size() == max
            roles[0] == role1
            roles[1] == role2
    }

    void "should save role"() {
        setup:
            Role newRole = new Role(authority: "ROLE_NEW")
        when:
            service.save(newRole)
        then:
            Role.count == 1
            Role.get(1) == newRole
    }

    void "should save role with existing authority"() {
        setup:
            Role role1 = new Role(authority: "ROLE_ADMIN").save()
            Role role2 = new Role(authority: "ROLE_USER").save()
            Role role3 = new Role(authority: "ROLE_SUPERUSER").save()
            Role newRole = new Role(authority: "ROLE_ADMIN")
        when:
            service.save(newRole)
        then:
            Role.count == 3
    }
}
