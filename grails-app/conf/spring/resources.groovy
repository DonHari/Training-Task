package spring

import untitled3.MessageServiceImplService
import untitled3.RoleServiceImplService
import untitled3.SecurityServiceImplService
import untitled3.UserDetailsService
import untitled3.UserServiceImplService

// Place your Spring DSL code here
beans = {
    messageService(MessageServiceImplService) {
        ref('messageService')
    }
    roleService(RoleServiceImplService){
        ref('roleService')
    }
    userService(UserServiceImplService){
        ref('userService')
    }
    securityService(SecurityServiceImplService){
        ref('securityService')
    }

    userDetailsService(UserDetailsService)
}
