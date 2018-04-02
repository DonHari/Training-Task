<%@ page import="untitled3.User; untitled3.UserRole" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'role.label', default: 'Role')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-role" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-role" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list message">
                <li class="fieldcontain">
                    <span class="property-label">Authority</span>
                    <div class="property-value">${role.authority}</div>
                </li>
                <li class="fieldcontain">
                    <span class="property-label">Users</span>
                    <div class="property-value">
                        <%
                            List<User> users = new LinkedList<>()
                            List<UserRole> userRoles = UserRole.findAllByRole(role).each {
                                users << it.user
                            }
                        %>
                        <g:each in="${users}" var="user">
                            <ul>
                                <li style="list-style: none">
                                    <a href="/user/show/${user.id}">
                                        ${user.username}
                                    </a>
                                </li>
                            </ul>
                        </g:each>
                    </div>
                </li>
            </ol>
        </div>
    </body>
</html>
