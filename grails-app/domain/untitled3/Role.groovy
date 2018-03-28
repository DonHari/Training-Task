package untitled3

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class Role implements Serializable {

    private static final long serialVersionUID = 1

    Long id
    String authority

    static constraints = {
        authority blank: false, unique: true
    }

    static mapping = {
        cache true
        version defaultValue: 1
    }

    static belongsTo = [User]

    static hasMany = [users: User]

}