package sep.salesmanagement.yt.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sep.salesmanagement.yt.entity.LoginUser;
import sep.salesmanagement.yt.entity.User;
import sep.salesmanagement.yt.repository.UserRepository;

@Service
public class SalesManagementUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public SalesManagementUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO 自動生成されたメソッド・スタブ
        User user = userRepository.findByName(username).get();
        LoginUser loginUser = new LoginUser(user);
        return loginUser;
    }

}
