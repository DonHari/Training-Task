package untitled3

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@ToString(includeNames = true)
@EqualsAndHashCode(includes = 'username')
class User implements Serializable{

    Long id
    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    String name

    static constraints = {
        username blank: false, unique: true
        password blank: false, password: true
        name nullable: false
        roles nullable: false
    }

    static mapping = {
        roles lazy: false
        messages lazy: false
    }

    static hasMany = [messages: Message, roles: Role]

}
