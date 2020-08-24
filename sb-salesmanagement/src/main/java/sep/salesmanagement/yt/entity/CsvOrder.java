package sep.salesmanagement.yt.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonPropertyOrder({ "顧客名", "受注日", "S番号", "案件名", "数量", "単位",
        "納入指定日", "納入日", "請求日", "見積金額", "受注金額", "ステータス名", "備考" })

//CSV形式用案件Entity
@Data
public class CsvOrder {
    @JsonProperty("顧客名")
    private String orderCustomerName;

    @JsonProperty("受注日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @JsonProperty("S番号")
    private String orderSNumber;

    @JsonProperty("案件名")
    private String orderName;

    @JsonProperty("数量")
    private int orderQuantity;

    @JsonProperty("単位")
    private String orderUnitName;

    @JsonProperty("納入指定日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDeliverySpecifiedDate;

    @JsonProperty("納入日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDeliveryDate;

    @JsonProperty("請求日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderBillingDate;

    @JsonProperty("見積金額")
    private int orderQuotePrice;

    @JsonProperty("受注金額")
    private int orderPrice;

    @JsonProperty("ステータス名")
    private String orderStatusName;

    @JsonProperty("備考")
    private String orderRemarks;

    public CsvOrder(String orderCustomerName, Date orderDate, String orderSNumber, String orderName,
            int orderQuantity, String orderUnitName, Date orderDeliverySpecifiedDate, Date orderDeliveryDate,
            Date orderBillingDate, int orderQuotePrice, int orderPrice, String orderStatusName, String orderRemarks) {
        this.orderCustomerName = orderCustomerName;
        this.orderDate = orderDate;
        this.orderSNumber = orderSNumber;
        this.orderName = orderName;
        this.orderQuantity = orderQuantity;
        this.orderUnitName = orderUnitName;
        this.orderDeliverySpecifiedDate = orderDeliverySpecifiedDate;
        this.orderDeliveryDate = orderDeliveryDate;
        this.orderBillingDate = orderBillingDate;
        this.orderQuotePrice = orderQuotePrice;
        this.orderPrice = orderPrice;
        this.orderStatusName = orderStatusName;
        this.orderRemarks = orderRemarks;

    }
}
