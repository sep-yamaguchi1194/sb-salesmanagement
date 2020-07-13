package sep.salesmanagement.yt.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderModifyForm implements Serializable{
    private int orderId;
    private int customerId;
    private String orderDate;
    private String orderSNumber;
    private String orderName;
    private int orderQuantity;
    private String unitName;
    private String deliverySpecifiedDate;
    private String deliveryDate;
    private String billingDate;
    private int quotePrice;
    private int orderPrice;
    private String statusId;
    private String orderRemarks;
}
