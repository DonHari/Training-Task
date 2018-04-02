package untitled3

import groovy.transform.ToString

@ToString(includeNames = true, excludes = "author")
class Message implements Comparable<Message> {

    String content
    Date createdAt

    static belongsTo = [User]

    static hasOne = [author: User]

//    static hasMany = [subscribers: User]

    static constraints = {
        content(blank: false, nullable: false)
        createdAt(nullable: true)
        author(nullable: false)
    }

    @Override
    int compareTo(Message message) {
        message.authorId <=> authorId ?:
                message.createdAt <=> createdAt//for correct order (DESC createdAt)
    }
}
