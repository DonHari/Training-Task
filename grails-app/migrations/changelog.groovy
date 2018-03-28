databaseChangeLog = {

    changeSet(author: "sdoro (generated)", id: "1522242341288-1") {
        createTable(tableName: "message") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "messagePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "content", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "created_at", type: "timestamp")

            column(name: "author_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sdoro (generated)", id: "1522242341288-2") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "rolePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sdoro (generated)", id: "1522242341288-3") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "userPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sdoro (generated)", id: "1522242341288-5") {
        createTable(tableName: "user_roles") {
            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sdoro (generated)", id: "1522242341288-6") {
        addPrimaryKey(columnNames: "user_id, role_id", tableName: "user_roles")
    }

    changeSet(author: "sdoro (generated)", id: "1522242341288-7") {
        addUniqueConstraint(columnNames: "authority", constraintName: "UC_ROLEAUTHORITY_COL", tableName: "role")
    }

    changeSet(author: "sdoro (generated)", id: "1522242341288-8") {
        addUniqueConstraint(columnNames: "username", constraintName: "UC_USERUSERNAME_COL", tableName: "user")
    }

    changeSet(author: "sdoro (generated)", id: "1522242341288-9") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_roles", constraintName: "FK_5q4rc4fh1on6567qk69uesvyf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role")
    }

    changeSet(author: "sdoro (generated)", id: "1522242341288-10") {
        addForeignKeyConstraint(baseColumnNames: "author_id", baseTableName: "message", constraintName: "FK_f80s4splfik51j2ja555ygvws", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "sdoro (generated)", id: "1522242341288-11") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_roles", constraintName: "FK_g1uebn6mqk9qiaw45vnacmyo2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "Stas Dorozhko", id: "1-insert"){
        sql("""insert into role(authority, version) values('qwe', 1);""")
        sql("""insert into role(authority, version) values('asd', 1);""")
        sql("""insert into role(authority, version) values('zxc', 1);""")

        sql("""insert into user(version, account_expired, account_locked, enabled, name, password, password_expired, username)
                values(1, false, false, true, 'qwe', 'qwe', false, 'qwe');""")
        sql("""insert into user(version, account_expired, account_locked, enabled, name, password, password_expired, username)
                values(1, false, false, true, 'asd', 'asd', false, 'asd');""")
        sql("""insert into user(version, account_expired, account_locked, enabled, name, password, password_expired, username)
                values(1, false, false, true, 'zxc', 'zxc', false, 'zxc');""")

        sql("""insert into user_roles(user_id, role_id) values(1, 1);""")
        sql("""insert into user_roles(user_id, role_id) values(1, 2);""")
        sql("""insert into user_roles(user_id, role_id) values(2, 2);""")
        sql("""insert into user_roles(user_id, role_id) values(3, 2);""")
        sql("""insert into user_roles(user_id, role_id) values(3, 1);""")
        sql("""insert into user_roles(user_id, role_id) values(3, 3);""")

        sql("""insert into message(version, created_at, author_id, content) values(1, CURRENT_TIMESTAMP(), 1, 'qwe 1 message');""")
        sql("""insert into message(version, created_at, author_id, content) values(1, CURRENT_TIMESTAMP(), 1, 'qwe 2 message');""")
        sql("""insert into message(version, created_at, author_id, content) values(1, CURRENT_TIMESTAMP(), 1, 'qwe 3 message');""")
        sql("""insert into message(version, created_at, author_id, content) values(1, CURRENT_TIMESTAMP(), 2, 'asd 1 message');""")
        sql("""insert into message(version, created_at, author_id, content) values(1, CURRENT_TIMESTAMP(), 2, 'asd 2 message');""")
    }
}
