<%@ page import="untitled3.Role; org.springframework.validation.FieldError" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-user" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.user}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.user}" var="error">
                <li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save">
                <fieldset class="form">
                    <div class="fieldcontain required">
                        <label for="username" >Username</label>
                        <g:textField name="username" value="${username}" required="true"/>
                    </div>
                    <div class="fieldcontain required">
                        <label for="password">Password</label>
                        <g:passwordField name="password" value="${password}" required="true"/>
                    </div>
                    <div class="fieldcontain required">
                        <label for="name">Name</label>
                        <g:textField name="name" value="${name}" required="true"/>
                    </div>
                    %{--<div class="fieldcontain required">--}%
                        %{--<label for="roles">Roles</label>--}%
                        %{--<% List<Role> roles = Role.getAll()%>--}%
                        %{--<g:select name="roles" from="${roles}" optionKey="id" optionValue="authority" multiple="true" required="true" value="true"/>--}%
                    %{--</div>--}%
                    %{--<f:all bean="user" except="accountExpired,enabled,accountLocked,passwordExpired,messages"/>--}%
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
