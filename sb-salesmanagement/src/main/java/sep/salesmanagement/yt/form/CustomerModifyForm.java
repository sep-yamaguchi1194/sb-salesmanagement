package sep.salesmanagement.yt.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class CustomerModifyForm implements Serializable{
	private int customerId;

	@NotEmpty(message = "顧客名は入力必須です。")
	private String customerName;
}
