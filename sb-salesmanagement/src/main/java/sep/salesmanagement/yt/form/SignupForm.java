package sep.salesmanagement.yt.form;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class SignupForm implements Serializable {
    @NotEmpty
    @Email(message="メールアドレスの形式で入力してください")
    private String email;

    @NotEmpty
    private String password;

    private String[] roles;


}
