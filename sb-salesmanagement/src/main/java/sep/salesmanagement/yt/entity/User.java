package sep.salesmanagement.yt.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;
import sep.salesmanagement.yt.roles.UserRolesUtil;

@Entity
@Table(name = "tbl_sms_users")
@Data
@ToString(exclude = {"password"})
public class User {
    @Id
    @Column(name = "user_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_roles")
    private String roles;

    @Column(name = "user_lock_flag")
    private Boolean lockFlag;

    @Column(name = "user_disable_flag")
    private Boolean disableFlag;

    @Column(name = "user_create_at")
    private LocalDateTime createAt;

    @Column(name = "user_update_at")
    private LocalDateTime updateAt;

    @PrePersist
    private void prePersist() {
        this.lockFlag = Boolean.FALSE;
        this.disableFlag = Boolean.FALSE;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updateAt = LocalDateTime.now();
    }

    public static User of(String email, String encodedPassword, String[] roles) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        String joinedRoles = UserRolesUtil.joining(roles);
        user.setRoles(joinedRoles);
        return user;
    }
}
