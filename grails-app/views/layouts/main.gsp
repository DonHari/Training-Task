<!doctype html>
<html lang="en" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title><g:layoutTitle default="Grails"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <asset:stylesheet src="application.css"/>
        <asset:javascript src="application.js"/>

        <g:layoutHead/>
    </head>
    <body>
        <div id="grailsLogo" role="banner">
            <a href="http://grails.org" style="width: 40%"><asset:image src="grails_logo.png" alt="Grails"/></a>
            <div style="width: 40%; display: inline-block">
                <a href="" style="width: 50%; margin: auto; display: inline-block">${message(code: "sign.up.label", default: "Sign up")}</a>
                <a href="" style="width: 50%; margin: auto; display: inline-block">${message(code: "sign.in.label", default: "Sign in")}</a>
            </div>
        </div>
        <g:layoutBody/>
        <div class="footer" role="contentinfo"></div>
        <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
    </body>
</html>
