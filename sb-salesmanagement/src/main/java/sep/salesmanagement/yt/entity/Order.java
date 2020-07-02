package sep.salesmanagement.yt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "tbl_sms_orders")
@Entity
@Data
public class Order implements Serializable {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "order_customer_id")
    private int orderCustomerId;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "order_s_number")
    private String orderSNumber;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_status_id")
    private String orderStatusId;

    @Column(name = "order_quantity")
    private int orderQuantity;

    @Column(name = "order_unit_name")
    private String orderUnitName;

    @Column(name = "order_delivery_specified_date")
    private Date orderDeliverySpecifiedDate;

    @Column(name = "order_delivery_date")
    private Date orderDeliveryDate;

    @Column(name = "order_billing_date")
    private Date orderBillingDate;

    @Column(name = "order_quote_price")
    private int orderQuotePrice;

    @Column(name = "order_price")
    private int orderPrice;

    @Column(name = "order_remarks")
    private String orderRemarks;

    @Column(name = "order_is_deleted")
    private String orderIsDeleted;

    /**
     * Join Customer Entity(顧客エンティティ)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    /**
     * Join Status Entity(ステータスエンティティ)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name="order_customer_id", referencedColumnName="status_customer_id", insertable = false, updatable = false),
        @JoinColumn(name="order_status_id", referencedColumnName="status_id", insertable = false, updatable = false)
    })
    private Status status;

}
