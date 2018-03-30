package spring

import untitled3.ExceptionHandlerController
import untitled3.MessageService
import untitled3.RoleServiceImplService
import untitled3.UserDetailsService
import untitled3.UserServiceImplService

// Place your Spring DSL code here
beans = {
    messageService(MessageService) {
        ref('messageService')
    }
    userDetailsService(UserDetailsService)
    exceptionHandler(ExceptionHandlerController){
        // this is required so that calls to super work
        ['java.lang.AccessDeniedException': '/customError']
    }
    roleService(RoleServiceImplService){
        ref('roleService')
    }
    userService(UserServiceImplService){
        ref('userService')
    }
}
