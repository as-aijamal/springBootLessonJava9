package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.User;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("User with id: " + id + " is not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(Long id, User user) {
        User user1 = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("User with id: " + id + " is not found"));
        user1.setFirstName(user.getFirstName());
        user1.setLastname(user.getLastname());
        user1.setAge(user.getAge());
        user1.setEmail(user.getEmail());
        userRepository.save(user1);
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
        else throw new NullPointerException("User with id: " + id + " is not found");
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserWithEmail(email);
    }
}
