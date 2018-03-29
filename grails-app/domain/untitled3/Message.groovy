package untitled3

import groovy.transform.ToString

@ToString(includeNames = true, excludes = "author")
class Message implements Comparable<Message> {

    String content
    Date createdAt

    static belongsTo = [author: User]

    static constraints = {
        content(blank: false, nullable: false)
        createdAt(nullable: true)
        author(nullable: false)
    }

    @Override
    int compareTo(Message message) {
        authorId <=> message.authorId ?:
                message.createdAt <=> createdAt//for correct order (DESC createdAt)
    }
}
