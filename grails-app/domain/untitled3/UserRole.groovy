package untitled3

import grails.compiler.GrailsCompileStatic
import grails.gorm.DetachedCriteria
import groovy.transform.ToString
import org.codehaus.groovy.util.HashCodeHelper

@GrailsCompileStatic
@ToString(cache = true, includeNames = true, includePackage = false)
class UserRole implements Serializable {

    private static final long serialVersionUID = 1

    User user
    Role role

    @Override
    boolean equals(other) {
        if (other instanceof UserRole) {
            other.user?.id == user?.id && other.role?.id == role?.id
        }
    }

    @Override
    int hashCode() {
        int hashCode = HashCodeHelper.initHash()
        if (user) {
            hashCode = HashCodeHelper.updateHash(hashCode, user.id)
        }
        if (role) {
            hashCode = HashCodeHelper.updateHash(hashCode, role.id)
        }
        hashCode
    }

    static UserRole get(long userId, long roleId) {
        criteriaFor(userId, roleId).get()
    }

    static boolean exists(long userId, long roleId) {
        criteriaFor(userId, roleId).count()
    }

    private static DetachedCriteria criteriaFor(long userId, long roleId) {
        where {
            user == User.load(userId) &&
                    role == Role.load(roleId)
        }
    }

    static UserRole create(User user, Role role, boolean flush = false) {
        UserRole instance = new UserRole(user: user, role: role)
        instance.save(flush: flush)
        instance
    }

    static boolean remove(User u, Role r) {
        if (u != null && r != null) {
            where { user == u && role == r }.deleteAll()
        }
    }

    static int removeAll(User u) {
        u == null ? 0 : where { user == u }.deleteAll() as int
    }

    static int removeAll(Role r) {
        r == null ? 0 : where { role == r }.deleteAll() as int
    }

    static constraints = {
        role validator: { Role r, UserRole ur ->
            if (ur.user?.id) {
                withNewSession {
                    if (exists(ur.user.id, r.id)) {
                        return ['userRole.exists']
                    }
                }
            }
        }
    }

    static mapping = {
        id composite: ['user', 'role']
        version false
    }
}
