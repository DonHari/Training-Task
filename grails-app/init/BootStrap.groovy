import grails.plugin.springsecurity.SpringSecurityUtils

class BootStrap {

    def init = { SpringSecurityUtils.getSecurityConfig().logout.postOnly = false
    }
    def destroy = {
    }
}
