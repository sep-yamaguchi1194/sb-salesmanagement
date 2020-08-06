package sep.salesmanagement.yt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sep.salesmanagement.yt.entity.Customer;
import sep.salesmanagement.yt.entity.Order;
import sep.salesmanagement.yt.entity.Status;
import sep.salesmanagement.yt.form.OrderAddForm;
import sep.salesmanagement.yt.form.OrderModifyForm;
import sep.salesmanagement.yt.form.OrderSearchForm;
import sep.salesmanagement.yt.form.SignupForm;
import sep.salesmanagement.yt.repository.UserRepository;
import sep.salesmanagement.yt.service.AccountService;
import sep.salesmanagement.yt.service.SalesManagementService;
import sep.salesmanagement.yt.wrapper.PageWrapper;

@Controller
public class SalesManagementController {
    @Autowired
    SalesManagementService salesManagementService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    //問題個所
    AccountService accountService;

    //ログイン画面
    @GetMapping(value = "/salesmanagement/login")
    public String displayLogin(Model model) {
        return "salesmanagement/login";
    }

    //ユーザー新規登録画面
    @GetMapping(value = "/salesmanagement/signup")
    public String displaySignup(Model model) {
        SignupForm signupForm = new SignupForm();
        model.addAttribute("signupForm", signupForm);
        return "salesmanagement/signup";
    }

    @PostMapping(value = "/salesmanagement/signup_confirm")
    public String createNewAccount(@ModelAttribute(name = "signupForm")SignupForm signupForm, Model model) {
        /**
         * 問題個所 Null Pointer Exception
         */
    	accountService = new AccountService(userRepository, passwordEncoder, authenticationManager);
        accountService.register(signupForm.getUsername(), signupForm.getPassword(), signupForm.getRoles());
        return "redirect:/salesmanagement/login";
    }

    @GetMapping(value = "/salesmanagement/order_list")
    public String displayOrderList(@ModelAttribute("orderSearchForm") OrderSearchForm orderSearchForm,
            @PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {

        /*
         * ,
            @RequestParam(name = "orderName", required = false) String orderName,
            @RequestParam(name = "orderCustomerId", required = false) String orderCustomerId,
            @RequestParam(name = "orderStatusId", required = false) String orderStatusId
        */

        //Page<T> class, PageWrapper<T> class
        Page<Order> orderPage;
        PageWrapper<Order> page;

        //orderPage = salesManagementService.showOrders(pageable);
        orderPage = salesManagementService.searchOrders(orderSearchForm.getOrderName(),
                orderSearchForm.getOrderCustomerId(), orderSearchForm.getOrderStatusId(), pageable);
        page = new PageWrapper<Order>(orderPage, "/salesmanagement/order_list", orderSearchForm.getOrderName(),
                orderSearchForm.getOrderCustomerId(), orderSearchForm.getOrderStatusId());

        model.addAttribute("page", page);
        model.addAttribute("orders", page.getContent());

        //Customer Entity List
        List<Customer> customerList = salesManagementService.showCustomer();
        model.addAttribute("customerList", customerList);

        //Status Entity List
        List<Status> statusList = salesManagementService.showStatus();
        model.addAttribute("statusList", statusList);

        /*
        //Order Entity List
        List<Order> orderList = salesManagementService.showOrder();
        model.addAttribute("orderList", orderList);
        */
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
        if (customerId != "0") {
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

        //Customer Entity List
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
    public String getStatusesAdd(@ModelAttribute("orderAddForm") OrderAddForm orderAddForm, Model model,
            @RequestParam(name = "customerid", required = false) int customerId) {
        if (customerId != 0) {
            List<Status> statusList = salesManagementService.showSpecificCustomerStatuses(customerId);
            model.addAttribute("statusList", statusList);
        }
        return "salesmanagement/order_add::selectAjax";
    }

    @PostMapping(value = "/salesmanagement/order_add_confirm")
    public String checkOrderAdd(
            @ModelAttribute("orderAddForm") @Validated(OrderAddForm.All.class) OrderAddForm orderAddForm,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            //Customer Entity List
            List<Customer> customerList = salesManagementService.showCustomer();
            model.addAttribute("customerList", customerList);
            model.addAttribute("orderAddForm", orderAddForm);
            return "salesmanagement/order_add";
        }
        //Customer Entity
        Customer customer = salesManagementService.showSelectedCustomer(orderAddForm.getCustomerId());
        model.addAttribute("customer", customer);

        //Status Entity
        Status status = salesManagementService.showSelectedStatus(orderAddForm.getCustomerId(),
                orderAddForm.getStatusId());
        model.addAttribute("status", status);
        return "salesmanagement/order_add_confirm";
    }

    @PostMapping(value = "/salesmanagement/create_order")
    public String createNewOrder(@ModelAttribute("orderAddForm") OrderAddForm orderAddForm, Model model) {
        salesManagementService.createOrder(orderAddForm);
        return "redirect:/salesmanagement/order_list";
    }

    @RequestMapping(value = "/salesmanagement/order_modify", method = RequestMethod.POST)
    public String displayOrderModify(Model model, @RequestParam(name = "orderId") int orderId) {
        Order order = salesManagementService.showSelectedOrder(orderId);
        OrderModifyForm orderModifyForm = new OrderModifyForm();
        orderModifyForm.setOrderId(order.getOrderId());
        orderModifyForm.setCustomerId(order.getOrderCustomerId());
        orderModifyForm.setOrderDate(salesManagementService.convertDateIntoString(order.getOrderDate()));
        orderModifyForm.setOrderSNumber(order.getOrderSNumber());
        orderModifyForm.setOrderName(order.getOrderName());
        orderModifyForm.setOrderQuantity(order.getOrderQuantity());
        orderModifyForm.setUnitName(order.getOrderUnitName());
        orderModifyForm.setDeliverySpecifiedDate(
                salesManagementService.convertDateIntoString(order.getOrderDeliverySpecifiedDate()));
        orderModifyForm.setDeliveryDate(salesManagementService.convertDateIntoString(order.getOrderDeliveryDate()));
        orderModifyForm.setBillingDate(salesManagementService.convertDateIntoString(order.getOrderBillingDate()));
        orderModifyForm.setQuotePrice(order.getOrderQuotePrice());
        orderModifyForm.setOrderPrice(order.getOrderPrice());
        orderModifyForm.setStatusId(order.getOrderStatusId());
        orderModifyForm.setOrderRemarks(order.getOrderRemarks());

        model.addAttribute("orderModifyForm", orderModifyForm);

        //CustomerEntity
        Customer customer = salesManagementService.showSelectedCustomer(order.getOrderCustomerId());
        model.addAttribute("customer", customer);

        //Status Entity List
        List<Status> statusList = salesManagementService.showSpecificCustomerStatuses(order.getOrderCustomerId());
        model.addAttribute("statusList", statusList);

        return "salesmanagement/order_modify";
    }

    //405Error 修正対象箇所
    @RequestMapping(value = "/salesmanagement/order_modify_confirm", method = RequestMethod.POST)
    public String checkOrderModify(
            @ModelAttribute("orderModifyForm") @Validated(OrderModifyForm.All.class) OrderModifyForm orderModifyForm,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orderModifyForm", orderModifyForm);

            //CustomerEntity
            Customer customer = salesManagementService.showSelectedCustomer(orderModifyForm.getCustomerId());
            model.addAttribute("customer", customer);

            //Status Entity List
            List<Status> statusList = salesManagementService
                    .showSpecificCustomerStatuses(orderModifyForm.getCustomerId());
            model.addAttribute("statusList", statusList);

            return "salesmanagement/order_modify";
        }
        //Customer Entity
        Customer customer = salesManagementService.showSelectedCustomer(orderModifyForm.getCustomerId());
        model.addAttribute("customer", customer);

        //Status Entity
        Status status = salesManagementService.showSelectedStatus(orderModifyForm.getCustomerId(),
                orderModifyForm.getStatusId());
        model.addAttribute("status", status);
        return "salesmanagement/order_modify_confirm";
    }

    @PostMapping(value = "/salesmanagement/update_order")
    public String updateOrder(@ModelAttribute("orderModifyForm") OrderModifyForm orderModifyForm, Model model) {
        salesManagementService.updateOrder(orderModifyForm);
        return "redirect:/salesmanagement/order_list";
    }

    @PostMapping(value = "/salesmanagement/order_delete_confirm")
    public String displayOrderDeleteConfirm(Model model, @RequestParam(name = "orderId") int orderId) {
        Order order = salesManagementService.showSelectedOrder(orderId);
        model.addAttribute("order", order);

        return "salesmanagement/order_delete_confirm";
    }

    @PostMapping(value = "/salesmanagement/delete_order")
    public String deleteOrder(Model model, @RequestParam(name = "orderId") int orderId) {
        salesManagementService.logicalDeleteOrder(orderId);
        return "redirect:/salesmanagement/order_list";
    }
}
