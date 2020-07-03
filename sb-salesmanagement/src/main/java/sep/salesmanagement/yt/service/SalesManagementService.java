package sep.salesmanagement.yt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sep.salesmanagement.yt.entity.Customer;
import sep.salesmanagement.yt.entity.Order;
import sep.salesmanagement.yt.entity.Status;
import sep.salesmanagement.yt.form.OrderAddForm;
import sep.salesmanagement.yt.repository.CustomerRepository;
import sep.salesmanagement.yt.repository.OrderRepository;
import sep.salesmanagement.yt.repository.StatusRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class SalesManagementService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    StatusRepository statusRepository;

    public List<Order> showOrder() {
        return orderRepository.findAll();
    }

    public List<Customer> showCustomer() {
        return customerRepository.findAll();
    }

    public List<Status> showStatus() {
        return statusRepository.findAll();
    }

    public List<Status> showSpecificCustomerStatuses(int customerId) {
        return statusRepository.findByStatusCustomerId(customerId);
    }

    /**
     * Customer Entity 取得
     * @param customerId
     * @return
     */
    public Customer showSelectedCustomer(int customerId) {
    	return customerRepository.findById(customerId).get();
    }

    /**
     * Status Entity 取得
     * @param statusCustomerId
     * @param statusId
     * @return
     */
    public Status showSelectedStatus(int statusCustomerId, String statusId) {
    	return statusRepository.findByStatusCustomerIdAndStatusId(statusCustomerId, statusId);

    }

    public void createOrder(OrderAddForm orderAddForm) {
    	Order order = new Order();
    	order.setOrderCustomerId(orderAddForm.getCustomerId());
    	order.setOrderDate(orderAddForm.getOrderDate());
    	order.setOrderSNumber(orderAddForm.getOrderSNumber());
    	order.setOrderName(orderAddForm.getOrderName());
    	order.setOrderStatusId(orderAddForm.getStatusId());
    	order.setOrderQuantity(orderAddForm.getOrderQuantity());
    	order.setOrderUnitName(orderAddForm.getUnitName());
    	order.setOrderDeliverySpecifiedDate(orderAddForm.getDeliverySpecifiedDate());
    	order.setOrderDeliveryDate(orderAddForm.getDeliveryDate());
    	order.setOrderBillingDate(orderAddForm.getBillingDate());
    	order.setOrderQuotePrice(orderAddForm.getQuotePrice());
    	order.setOrderPrice(orderAddForm.getOrderPrice());
    	order.setOrderRemarks(orderAddForm.getOrderRemarks());
    	order.setOrderIsDeleted("0");
        orderRepository.save(order);
    }
}
