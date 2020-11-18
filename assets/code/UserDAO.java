public interface UserDAO
{
    void insertUser(User user);

    void updateUser(User user);
    void updateUserDetails(User user);
    void updateUserPassword(int id, String password);

    void deleteUser(int id);

	Optional<User> getUser(int id);
    Optional<User> getUser(String username, String password);
    Optional<User> getUserByEmail(String email);

    List<User> getUsers();
}