package sep.salesmanagement.yt.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class CustomerAddForm implements Serializable{
	@NotEmpty(message = "顧客名は必須です。")
	private String customerName;
}
