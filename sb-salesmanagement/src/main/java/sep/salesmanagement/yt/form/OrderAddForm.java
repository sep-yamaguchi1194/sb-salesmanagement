package sep.salesmanagement.yt.form;

import java.io.Serializable;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import sep.salesmanagement.yt.validation.CustomPattern;
import sep.salesmanagement.yt.validation.ExistsDate;

@Data
public class OrderAddForm implements Serializable {

    public interface Group1 {}
    public interface Group2 {}

    @GroupSequence({Group1.class,Group2.class})
    public interface All {}

    /**
     * 顧客ID
     */
    private int customerId;

    /**
     * 受注日
     */
    @CustomPattern(groups = Group1.class, regex="\\d{4}/\\d{2}/\\d{2}", message = "日付は半角数字で[年(4桁)/月(2桁)/日(2桁)]の形式で入力してください。")
    @ExistsDate(groups = Group2.class, message = "[${validatedValue}]は存在しない日付です。")
    private String orderDate;

    /**
     * S番号
     */
    private String orderSNumber;

    /**
     * 件名
     */
    @NotEmpty(groups = Group1.class, message = "件名は必須項目です")
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
    @CustomPattern(groups = Group1.class, regex="\\d{4}/\\d{2}/\\d{2}", message = "日付は半角数字で[年(4桁)/月(2桁)/日(2桁)]の形式で入力してください。")
    @ExistsDate(groups = Group2.class, message = "[${validatedValue}]は存在しない日付です。")
    private String deliveryDate;

    /**
     * 請求日
     */
    @CustomPattern(groups = Group1.class, regex="\\d{4}/\\d{2}/\\d{2}", message = "日付は半角数字で[年(4桁)/月(2桁)/日(2桁)]の形式で入力してください。")
    @ExistsDate(groups = Group2.class, message = "[${validatedValue}]は存在しない日付です。")
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
