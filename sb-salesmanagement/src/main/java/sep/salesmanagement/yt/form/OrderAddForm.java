package sep.salesmanagement.yt.form;

import java.io.Serializable;

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
    private String orderDate;

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
    private String deliverySpecifiedDate;

    /**
     * 納入日
     */
    private String deliveryDate;

    /**
     * 請求日
     */
    private String billingDate;

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
