package sep.salesmanagement.yt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String getStatusesAdd(@ModelAttribute("orderAddForm")OrderAddForm orderAddForm, Model model, @RequestParam(name = "customerid", required = false) int customerId) {
        if(customerId != 0) {
            List<Status> statusList = salesManagementService.showSpecificCustomerStatuses(customerId);
            model.addAttribute("statusList", statusList);
        }
        return "salesmanagement/order_add::selectAjax";
    }

    @PostMapping(value = "/salesmanagement/order_add_confirm")
    public String checkOrderAdd(@ModelAttribute("orderAddForm")OrderAddForm orderAddForm, Model model) {
    	//Customer Entity
    	Customer customer = salesManagementService.showSelectedCustomer(orderAddForm.getCustomerId());
    	model.addAttribute("customer", customer);

    	//Status Entity
    	Status status = salesManagementService.showSelectedStatus(orderAddForm.getCustomerId(), orderAddForm.getStatusId());
    	model.addAttribute("status", status);
        return "salesmanagement/order_add_confirm";
    }

    @PostMapping(value = "/salesmanagement/create_order")
    public String createNewOrder(@ModelAttribute("orderAddForm")OrderAddForm orderAddForm, Model model) {
    	salesManagementService.createOrder(orderAddForm);
    	return "redirect:/salesmanagement/order_list";
    }


}
