package untitled3

class UserController {
    //to get hib session
    def sessionFactory


    def index() {
        final session = sessionFactory.currentSession
        def sql = 'SHOW TABLES;'
        final sqlQuery = session.createSQLQuery(sql)
        final results = sqlQuery.list()
        println results
        println "UserController index"
        def users = User.getAll()
        users.each {user -> println user}
    }
}
