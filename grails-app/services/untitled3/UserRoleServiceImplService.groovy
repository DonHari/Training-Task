package untitled3

import grails.transaction.Transactional

@Transactional
class UserRoleServiceImplService implements UserRoleService {

    @Override
    UserRole save(UserRole userRole) {
        if(userRole){
            userRole.save()
        }
    }
}
