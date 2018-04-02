package untitled3

interface MessageService {
    List<Message> index(Integer max)
    Message save(Message message)
    def delete(Message message)
}
