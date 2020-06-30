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

    @PostMapping(value = "/salesmanagement/getstatuses")
    public String getStatuses(Model model, @RequestParam(name = "customerid", required = false) String customerId) {
        if(!customerId.isEmpty()) {
            List<Status> statusList = salesManagementService.showSpecificCustomerStatuses(Integer.parseInt(customerId));
            model.addAttribute("statusList", statusList);
        }

        return "salesmanagement/order_list::selectAjax";
    }
}
