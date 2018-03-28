package untitled3

import groovy.transform.ToString

import java.time.LocalDateTime
import java.time.OffsetDateTime

@ToString(includeNames = true, excludes = "user")
class Message {

    Long id
    String content
    Date createdAt

    static belongsTo = [user: User]

    static constraints = {
        content(blank: false, nullable: false)
        createdAt(nullable: true)
        user(nullable: false)
    }
}
