databaseChangeLog = {

    changeSet(author: "sdoro (generated)", id: "1522161566862-1") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "userPK")
            }

            column(name: "login", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sdoro (generated)", id: "1522161566862-2") {
        addUniqueConstraint(columnNames: "login", constraintName: "UC_USERLOGIN_COL", tableName: "user")
    }
}
