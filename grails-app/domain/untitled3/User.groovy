package untitled3

import groovy.transform.ToString

@ToString
class User {
    static constraints = {
        login blank: false, unique: true
        password blank: false, password: true
    }

    static mapping = {
        table 'user'
        version false
    }

    Long id
    String login
    String password
}
