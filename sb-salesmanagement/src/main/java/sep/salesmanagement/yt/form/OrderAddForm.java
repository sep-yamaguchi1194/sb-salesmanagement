package sep.salesmanagement.yt.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
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
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
    private Date deliverySpecifiedDate;

    /**
     * 納入日
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    /**
     * 請求日
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
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
