package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    void save(User user);
    User getUserbyId(Long id);
    void delete(Long id);
}
