package untitled3

import grails.transaction.Transactional
import org.springframework.security.core.context.SecurityContextHolder

@Transactional
class UserServiceImplService implements UserService {

    @Override
    List<User> index(Integer max) {
        Integer localMax = Math.min(max ?: 10, 100)
        User.findAll(max: localMax)
    }

    @Override
    User save(User user) {
        if (user) {
            if (User.findAllByUsername(user.username)) {
                throw new AlreadyExistsException("This username is not available")
            }
            User localUser = user.save()
            UserRole userRole = new UserRole()
            userRole.user = localUser
            userRole.role = Role.findByAuthority("ROLE_USER")
            userRole.save()
            return localUser
        }
    }

    @Override
    User subscribe(User subscriber) {
        UserDetails userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal() as UserDetails
        if (subscriber && (subscriber.id != userDetails.id as Long)) {
            User user = User.get(userDetails.id as Long)
            User localSubscriber = User.get(subscriber.id)
            localSubscriber.addToSubscribers(user)
            //without flush it doesn't work. IDK why
            localSubscriber = localSubscriber.save(flush: true)
            return localSubscriber
        }
        return null
    }
}
