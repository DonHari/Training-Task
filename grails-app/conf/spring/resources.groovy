package spring

import untitled3.MessageService
import untitled3.RoleServiceImplService
import untitled3.UserDetailsService
import untitled3.UserServiceImplService

// Place your Spring DSL code here
beans = {
    messageService(MessageService) {
        ref('messageService')
    }
    roleService(RoleServiceImplService){
        ref('roleService')
    }
    userService(UserServiceImplService){
        ref('userService')
    }

    userDetailsService(UserDetailsService)
}
