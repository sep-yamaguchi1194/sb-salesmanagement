package sep.salesmanagement.yt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sep.salesmanagement.yt.entity.Customer;
import sep.salesmanagement.yt.entity.Order;
import sep.salesmanagement.yt.entity.Status;
import sep.salesmanagement.yt.form.OrderAddForm;
import sep.salesmanagement.yt.service.SalesManagementService;


@Controller
public class SalesManagementController {
    @Autowired
    SalesManagementService salesManagementService;

    @GetMapping(value = "/salesmanagement/order_list")
    public String displayOrderList(Model model) {

        List<Customer> customerList = salesManagementService.showCustomer();
        model.addAttribute("customerList", customerList);

        List<Order> orderList = salesManagementService.showOrder();
        model.addAttribute("orderList", orderList);
        return "salesmanagement/order_list";
    }

    /**
     * Ajax通信でselectboxのfragmentを書き換えるためのメソッド
     * @param model
     * @param customerId
     * @return
     */
    @PostMapping(value = "/salesmanagement/getstatuses")
    public String getStatuses(Model model, @RequestParam(name = "customerid", required = false) String customerId) {
        if(customerId != "0") {
            List<Status> statusList = salesManagementService.showSpecificCustomerStatuses(Integer.parseInt(customerId));
            model.addAttribute("statusList", statusList);
        }
        return "salesmanagement/order_list::selectAjax";
    }

    @GetMapping(value = "/salesmanagement/order_add")
    public String displayOrderAdd(Model model) {
    	//新規案件登録用フォーム
    	OrderAddForm orderAddForm = new OrderAddForm();
    	model.addAttribute("orderAddForm", orderAddForm);

        List<Customer> customerList = salesManagementService.showCustomer();
        model.addAttribute("customerList", customerList);
        return "salesmanagement/order_add";
    }

    /**
     * Ajax通信でselectboxのfragmentを書き換えるためのメソッド
     * @param model
     * @param customerId
     * @return
     */
    @PostMapping(value = "/salesmanagement/getstatuses_add")
    public String getStatusesAdd(Model model, @RequestParam(name = "customerid", required = false) int customerId) {
        if(customerId != 0) {
            List<Status> statusList = salesManagementService.showSpecificCustomerStatuses(customerId);
            model.addAttribute("statusList", statusList);
        }
        return "salesmanagement/order_add::selectAjax";
    }
}
