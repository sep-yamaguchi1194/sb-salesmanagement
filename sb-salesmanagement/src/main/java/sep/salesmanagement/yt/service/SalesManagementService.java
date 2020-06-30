package sep.salesmanagement.yt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sep.salesmanagement.yt.entity.Customer;
import sep.salesmanagement.yt.entity.Order;
import sep.salesmanagement.yt.entity.Status;
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
}
