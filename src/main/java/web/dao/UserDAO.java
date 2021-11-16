package web.dao;

import web.model.User;
import java.util.List;


public interface UserDAO {
    List<User> getUsers();
    User save(User user);
    User getUserbyId(Long id);
    void delete(Long id);
}
