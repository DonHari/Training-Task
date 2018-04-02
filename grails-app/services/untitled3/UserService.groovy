package untitled3

interface UserService {

    List<User> index(Integer max)
    User save(User user)
    User subscribe(User user)
}