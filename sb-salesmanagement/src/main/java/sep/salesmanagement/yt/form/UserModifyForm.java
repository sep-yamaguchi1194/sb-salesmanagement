package sep.salesmanagement.yt.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserModifyForm  implements Serializable{
    private String name;
    private String email;
    private String password;

    public UserModifyForm(String email) {
        this.email = email;
    }
}
