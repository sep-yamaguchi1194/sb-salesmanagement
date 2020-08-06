package sep.salesmanagement.yt.entity;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import sep.salesmanagement.yt.util.UserRolesUtil;

public class LoginUser implements UserDetails, CredentialsContainer {

    private final String username;
    private String password;
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final User user;

    public LoginUser(User user) {
        if ((Objects.isNull(user.getName()) || "".equals(user.getName()))
                || (Objects.isNull(user.getPassword()) || "".equals(user.getPassword()))) {
            throw new IllegalArgumentException("Cannot pass null or empty values");
        }
        this.username = user.getName();
        this.password = user.getPassword();
        this.authorities = UserRolesUtil.toSet(user.getRoles());
        this.accountNonExpired = true;
        this.accountNonLocked = !user.getLockFlag();
        this.credentialsNonExpired = true;
        this.enabled = !user.getDisableFlag();
        this.user = user;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO 自動生成されたメソッド・スタブ
        return this.authorities;
    }

    @Override
    public String getPassword() {
        // TODO 自動生成されたメソッド・スタブ
        return this.password;
    }

    @Override
    public String getUsername() {
        // TODO 自動生成されたメソッド・スタブ
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO 自動生成されたメソッド・スタブ
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO 自動生成されたメソッド・スタブ
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO 自動生成されたメソッド・スタブ
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        // TODO 自動生成されたメソッド・スタブ
        return this.enabled;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public boolean equals(Object rhs) {
        if(!(rhs instanceof LoginUser)) {
            return false;
        }
        return this.username.equals(((LoginUser)rhs).username);
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

}
