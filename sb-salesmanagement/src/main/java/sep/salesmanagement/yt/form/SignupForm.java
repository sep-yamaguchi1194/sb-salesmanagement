package sep.salesmanagement.yt.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class SignupForm implements Serializable {
    private String username;

    private String password;

    private String[] roles;


}
