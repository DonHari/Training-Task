package untitled3

import grails.transaction.Transactional

@Transactional
class RoleServiceImplService implements RoleService {

    @Override
    List<Role> index(Integer max) {
        Integer localMax = Math.min(max ?: 10, 100)
        Role.findAll(max: localMax)
    }

    @Override
    Role save(Role role) {
        if (role) {
            if(Role.findAllByAuthority(role.authority).size() != 1) {
                role.save()
            } else {
                throw new AlreadyExistsException("This role already exists")
            }
        }
    }
}
