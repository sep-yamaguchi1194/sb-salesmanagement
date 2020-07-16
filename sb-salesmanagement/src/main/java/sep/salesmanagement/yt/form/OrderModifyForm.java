package sep.salesmanagement.yt.form;

import java.io.Serializable;

import lombok.Data;
import sep.salesmanagement.yt.form.OrderAddForm.Group1;
import sep.salesmanagement.yt.form.OrderAddForm.Group2;
import sep.salesmanagement.yt.validation.CustomPattern;
import sep.salesmanagement.yt.validation.ExistsDate;

@Data
public class OrderModifyForm implements Serializable{
    private int orderId;
    private int customerId;

    @CustomPattern(groups = Group1.class, regex="\\d{4}/\\d{2}/\\d{2}", message = "日付は半角数字で[年(4桁)/月(2桁)/日(2桁)]の形式で入力してください。")
    @ExistsDate(groups = Group2.class, message = "[${validatedValue}]は存在しない日付です。")
    private String orderDate;
    private String orderSNumber;
    private String orderName;
    private int orderQuantity;
    private String unitName;

    @CustomPattern(groups = Group1.class, regex="\\d{4}/\\d{2}/\\d{2}", message = "日付は半角数字で[年(4桁)/月(2桁)/日(2桁)]の形式で入力してください。")
    @ExistsDate(groups = Group2.class, message = "[${validatedValue}]は存在しない日付です。")
    private String deliverySpecifiedDate;

    @CustomPattern(groups = Group1.class, regex="\\d{4}/\\d{2}/\\d{2}", message = "日付は半角数字で[年(4桁)/月(2桁)/日(2桁)]の形式で入力してください。")
    @ExistsDate(groups = Group2.class, message = "[${validatedValue}]は存在しない日付です。")
    private String deliveryDate;

    @CustomPattern(groups = Group1.class, regex="\\d{4}/\\d{2}/\\d{2}", message = "日付は半角数字で[年(4桁)/月(2桁)/日(2桁)]の形式で入力してください。")
    @ExistsDate(groups = Group2.class, message = "[${validatedValue}]は存在しない日付です。")
    private String billingDate;
    private int quotePrice;
    private int orderPrice;
    private String statusId;
    private String orderRemarks;
}
