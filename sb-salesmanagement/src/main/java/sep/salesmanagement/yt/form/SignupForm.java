package sep.salesmanagement.yt.form;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class SignupForm implements Serializable {
    @NotEmpty(message="メールアドレスは入力必須です")
    @Email(message="メールアドレスの形式で入力してください")
    private String email;

    @NotEmpty(message="パスワードは入力必須です")
    private String password;

    private String[] roles;


}
