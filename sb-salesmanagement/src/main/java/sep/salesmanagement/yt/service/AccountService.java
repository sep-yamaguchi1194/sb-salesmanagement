package sep.salesmanagement.yt.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sep.salesmanagement.yt.entity.LoginUser;
import sep.salesmanagement.yt.entity.User;
import sep.salesmanagement.yt.repository.UserRepository;

@Service
public class AccountService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public AccountService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    /**
     *
     * @param email メールアドレス
     * @param rawPassword 平文のパスワード
     * @param roles 権限(String[] のまま)
     */
    @Transactional
    public void register(String email, String rawPassword, String[] roles) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User storedUser = userRepository.saveAndFlush(User.of(email, encodedPassword, roles));
        authentication(storedUser, rawPassword);
    }

    public void authentication(User user, String rawPassword) {
        LoginUser loginUser = new LoginUser(user);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, rawPassword, loginUser.getAuthorities());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        if(auth.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            throw new RuntimeException("認証失敗");
        }
    }
}
