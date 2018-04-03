package untitled3

import grails.transaction.Transactional
import org.springframework.security.core.context.SecurityContextHolder

@Transactional
class SecurityServiceImplService implements SecurityService {

    UserService userService

    @Override
    User getAuthorizedUser() {
        UserDetails userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal() as UserDetails
        userService.get(userDetails.id as Long)
    }
}
