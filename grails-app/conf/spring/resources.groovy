package spring

import untitled3.MessageService
import untitled3.UserDetailsService

// Place your Spring DSL code here
beans = {
    messageService(MessageService) {
        ref('messageService')
    }
    userDetailsService(UserDetailsService)
}
