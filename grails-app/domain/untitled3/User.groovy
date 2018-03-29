package untitled3

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@ToString(includeNames = true)
@EqualsAndHashCode(includes = 'username')
class User implements Serializable{

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    String name

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        username blank: false, unique: true
        password blank: false, password: true
        name nullable: false
    }

    static mapping = {
        messages lazy: false
    }

    static hasMany = [messages: Message]

}
