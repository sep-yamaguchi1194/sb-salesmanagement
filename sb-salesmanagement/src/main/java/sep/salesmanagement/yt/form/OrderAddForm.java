package sep.salesmanagement.yt.form;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class OrderAddForm implements Serializable {
	/**
	 * 顧客ID
	 */
	private int customerId;

	/**
	 * 受注日
	 */
	private Date orderDate;

	/**
	 * S番号
	 */
	private String orderSNumber;

	/**
	 * 件名
	 */
	private String orderName;

	/**
	 * 数量
	 */
	private int orderQuantity;

	/**
	 * 単位
	 */
	private String unitName;

	/**
	 * 納入指定日
	 */
	private Date deliverySpecifiedDate;

	/**
	 * 納入日
	 */
	private Date deliveryDate;

	/**
	 * 請求日
	 */
	private Date billingDate;

	/**
	 * 見積金額
	 */
	private int quotePrice;

	/**
	 * 受注金額
	 */
	private int orderPrice;

	/**
	 * ステータスID
	 */
	private String statusId;

	/**
	 * 備考
	 */
	private String orderRemarks;
}
