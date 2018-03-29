<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-user" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Name</th>
                        <th>Roles</th>
                        <th>Account expired</th>
                        <th>Account locked</th>
                        <th>Enabled</th>
                        <th>Password expired</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${userList}" var="user">
                        <tr>
                            <td>
                                <a href="show/${user.id}">
                                    ${user.username}
                                </a>
                            </td>
                            <td>${user.name}</td>
                            <td>
                                <ul>
                                    <g:each in="${user.roles}" var="role">
                                        <li>
                                            <a href="/role/show/${role.id}">
                                                ${role.authority}
                                            </a>
                                        </li>
                                    </g:each>
                                </ul>
                            </td>
                            <td>${user.accountExpired}</td>
                            <td>${user.accountLocked}</td>
                            <td>${user.enabled}</td>
                            <td>${user.passwordExpired}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${userCount ?: 0}" />
            </div>
        </div>
    </body>
</html>