package sep.salesmanagement.yt.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "tbl_sms_order_details")
@Entity
@Data
public class OrderDetail implements Serializable {
	@Id
	@Column(name = "detail_s_number")
	private String detailSNumber;

	@Column(name = "detail_quantity")
	private int detailQuantity;

	@Column(name = "detail_unit_name")
	private String detailUnitName;

	@Column(name = "detail_delivery_specified_date")
	private Date detailDeliverySpecifiedDate;

	@Column(name = "detail_delivery_date")
	private Date detailDeliveryDate;

	@Column(name = "detail_billing_date")
	private Date detailBillingDate;

	@Column(name = "detail_quote_price")
	private int detailQuotePrice;

	@Column(name = "detail_order_price")
	private int detailOrderPrice;

	@Column(name = "detail_remarks")
	private String detailRemarks;
}
