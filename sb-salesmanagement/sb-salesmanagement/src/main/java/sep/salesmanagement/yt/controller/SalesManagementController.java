package sep.salesmanagement.yt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sep.salesmanagement.yt.entity.Order;
import sep.salesmanagement.yt.service.SalesManagementService;


@Controller
public class SalesManagementController {
	@Autowired
	SalesManagementService salesManagementService;

    @GetMapping(value = "/salesmanagement/order_list")
    public String displayOrderList(Model model) {
		List<Order> orderList = salesManagementService.showOrder();
		model.addAttribute("orderList", orderList);
    	return "salesmanagement/order_list";
    }
}
