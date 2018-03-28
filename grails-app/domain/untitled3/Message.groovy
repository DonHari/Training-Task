package untitled3

import groovy.transform.ToString

import java.time.LocalDateTime
import java.time.OffsetDateTime

@ToString(includeNames = true, excludes = "author")
class Message {

    Long id
    String content
    Date createdAt

    static belongsTo = [author: User]

    static constraints = {
        content(blank: false, nullable: false)
        createdAt(nullable: true)
        author(nullable: false)
    }
}
