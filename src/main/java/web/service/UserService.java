package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public void saveUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long id);
    public User showId(Long id);
}
