<%@ page import="untitled3.Role; untitled3.UserRole" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-user" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list message">
                <li class="fieldcontain">
                    <span class="property-label">Username</span>
                    <div class="property-value">${user.username}</div>
                </li>
                <li class="fieldcontain">
                    <span class="property-label">Name</span>
                    <div class="property-value">${user.name}</div>
                </li>
                <li class="fieldcontain">
                    <span class="property-label">Roles</span>
                    <div class="property-value">
                        <%
                            List<Role> roles = new LinkedList<>()
                            UserRole.findAllByUser(user).each {
                                roles.add(it.getRole())
                            }
                        %>
                        <g:each in="${roles}" var="role">
                            <a href="/role/show/${role.id}">
                                ${role.authority}
                            </a>
                        </g:each>
                    </div>
                </li>
                <li class="fieldcontain">
                    <span class="property-label">Account expired</span>
                    <div class="property-value">${user.accountExpired}</div>
                </li>
                <li class="fieldcontain">
                    <span class="property-label">Account locked</span>
                    <div class="property-value">${user.accountLocked}</div>
                </li>
                <li class="fieldcontain">
                    <span class="property-label">Enabled</span>
                    <div class="property-value">${user.enabled}</div>
                </li>
                <li class="fieldcontain">
                    <span class="property-label">Password expired</span>
                    <div class="property-value">${user.passwordExpired}</div>
                </li>
                <li class="fieldcontain">
                    <span class="property-label">Messages</span>
                    <div class="property-value">
                        <g:each in="${user.messages}" var="userMessage">
                            <ul>
                                <li style="list-style: none">
                                    <a href="/message/show/${userMessage.id}">
                                        ${userMessage.content}
                                    </a>
                                </li>
                            </ul>
                        </g:each>
                    </div>
                </li>
                <li class="fieldcontain">
                    <span class="property-label">Subscribers</span>
                    <div class="property-value">
                        <g:each in="${user.subscribers}" var="subscriber">
                            <ul>
                                <li style="list-style: none">
                                    <a href="/user/show/${subscriber.id}">
                                        ${subscriber.username}
                                    </a>
                                </li>
                            </ul>
                        </g:each>
                    </div>
                </li>
                <g:form method="POST" action="subscribe" controller="user" id="${user.id}">
                    <fieldset class="buttons">
                        %{--<g:link class="edit" action="edit" resource="${this.message}"><g:message code="default.button.edit.label" default="Edit" /></g:link>--}%
                        <input type="submit" value="${message(code: 'default.button.subscribe.label', default: 'Subscribe')}"/>
                    </fieldset>
                </g:form>
                %{--<g:form controller="user" action="subscribe" method="POST">--}%
                    %{--<g:actionSubmit value="${message(code: 'default.button.subscribe.label', default: 'Subscribe')}" action="subscribe"/>--}%
                %{--</g:form>--}%
            </ol>
        </div>
    </body>
</html>
