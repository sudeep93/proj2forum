package proj2.backend.implementation;

import proj2.backend.model.User;

public interface UserDao {
User authenticate(User user);
void updateUser(User user);
User registerUser(User user);
}