package untitled3

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@ToString(includeNames = true, includes = "id,username" /*excludes = "subscribers"*/)
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
        subscribers lazy: false, joinTable: [name: 'user_subscriber', column: 'subscriber_id', key: 'user_id']
    }

    static hasMany = [messages: Message, subscribers: User]

//    static belongsTo = [User]

}
