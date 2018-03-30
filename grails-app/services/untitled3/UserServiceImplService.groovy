package untitled3

import grails.transaction.Transactional

@Transactional
class UserServiceImplService implements UserService {

    @Override
    def index(Integer max) {
        Integer localMax = Math.min(max ?: 10, 100)
        User.findAll(max: localMax)
    }

    @Override
    def save(User user) {
        if(user){
            if(User.findAllByUsername(user.username)){
                //show message that username already exists
            }
            user.save()
        }
    }
}
