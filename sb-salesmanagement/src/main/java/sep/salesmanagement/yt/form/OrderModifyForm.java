package sep.salesmanagement.yt.form;

import java.io.Serializable;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import sep.salesmanagement.yt.validation.CustomPattern;
import sep.salesmanagement.yt.validation.ExistsDate;

@Data
public class OrderModifyForm implements Serializable{

    public interface Group1 {}
    public interface Group2 {}

    @GroupSequence({Group1.class,Group2.class})
    public interface All {}

    private int orderId;
    private int customerId;

    @CustomPattern(groups = Group1.class, regex="\\d{4}/\\d{2}/\\d{2}", message = "日付は半角数字で[年(4桁)/月(2桁)/日(2桁)]の形式で入力してください。")
    @ExistsDate(groups = Group2.class, message = "[${validatedValue}]は存在しない日付です。")
    private String orderDate;
    private String orderSNumber;

    @NotEmpty(groups = Group1.class, message = "件名は必須項目です")
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
