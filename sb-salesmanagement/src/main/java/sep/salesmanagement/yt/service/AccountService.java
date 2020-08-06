package sep.salesmanagement.yt.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import sep.salesmanagement.yt.entity.User;
import sep.salesmanagement.yt.repository.UserRepository;

public class AccountService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AccountService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void register(String name, String rawPassword, String[] roles) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User storedUser = userRepository.saveAndFlush(User.of(name, encodedPassword, roles));
    }
}
