package sep.salesmanagement.yt.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderSearchForm implements Serializable {
	String orderCustomerId;
	String orderStatusId;
	String orderName;
}
