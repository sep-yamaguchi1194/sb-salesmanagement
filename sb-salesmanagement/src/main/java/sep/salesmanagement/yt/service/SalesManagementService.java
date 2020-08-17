package sep.salesmanagement.yt.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import sep.salesmanagement.yt.entity.Customer;
import sep.salesmanagement.yt.entity.Order;
import sep.salesmanagement.yt.entity.Status;
import sep.salesmanagement.yt.entity.User;
import sep.salesmanagement.yt.form.OrderAddForm;
import sep.salesmanagement.yt.form.OrderModifyForm;
import sep.salesmanagement.yt.repository.CustomerRepository;
import sep.salesmanagement.yt.repository.OrderRepository;
import sep.salesmanagement.yt.repository.StatusRepository;
import sep.salesmanagement.yt.repository.UserRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class SalesManagementService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    UserRepository userRepository;

    public Page<Order> showOrders(Pageable pageable) {
        return orderRepository.findByOrderIsDeleted("0", pageable);
    }

    //検索用メソッド
    public Page<Order> searchOrders(String orderName, String orderCustomerId, String orderStatusId, Pageable pageable) {
        Specification<Order> spec = Specification.where(orderNameContains(orderName))
                .and(orderCustomerIdEquals(orderCustomerId))
                .and(orderStatusIdEquals(orderStatusId))
                .and(orderIsDeletedEquals("0"));
        return orderRepository.findAll(spec, pageable);
    }

    //Specification
    private Specification<Order> orderNameContains(String orderName) {
        return StringUtils.isEmpty(orderName) ? null : new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("orderName"), "%" + orderName + "%");
            }
        };
    }

    private Specification<Order> orderCustomerIdEquals(String orderCustomerId) {
        return StringUtils.isEmpty(orderCustomerId) ? null : new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("orderCustomerId"), orderCustomerId);
            }
        };
    }

    private Specification<Order> orderStatusIdEquals(String orderStatusId) {
        return StringUtils.isEmpty(orderStatusId) ? null : new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("orderStatusId"), orderStatusId);
            }
        };
    }

    private Specification<Order> orderIsDeletedEquals(String orderIsDeleted) {
        return StringUtils.isEmpty(orderIsDeleted) ? null : new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("orderIsDeleted"), orderIsDeleted);
            }
        };
    }

    public List<Order> showOrder() {
        return orderRepository.findAll();
    }

    public Order showSelectedOrder(int orderId) {
        return orderRepository.findById(orderId).get();
    }

    public List<Customer> showCustomer() {
        return customerRepository.findAll();
    }

    public List<Status> showStatus() {
        return statusRepository.findAll();
    }

    public List<User> showUser() {
    	return userRepository.findAll();
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
        order.setOrderDate(convertStringIntoDate(orderAddForm.getOrderDate()));
        if(orderAddForm.getOrderSNumber().isEmpty()) {
            orderAddForm.setOrderSNumber(null);
        }
        order.setOrderSNumber(orderAddForm.getOrderSNumber());
        order.setOrderName(orderAddForm.getOrderName());
        order.setOrderQuantity(orderAddForm.getOrderQuantity());
        order.setOrderUnitName(orderAddForm.getUnitName());
        order.setOrderDeliverySpecifiedDate(convertStringIntoDate(orderAddForm.getDeliverySpecifiedDate()));
        order.setOrderDeliveryDate(convertStringIntoDate(orderAddForm.getDeliveryDate()));
        order.setOrderBillingDate(convertStringIntoDate(orderAddForm.getBillingDate()));
        order.setOrderQuotePrice(orderAddForm.getQuotePrice());
        order.setOrderPrice(orderAddForm.getOrderPrice());
        order.setOrderStatusId(orderAddForm.getStatusId());
        order.setOrderRemarks(orderAddForm.getOrderRemarks());
        order.setOrderIsDeleted("0");
        orderRepository.save(order);
    }

    public void updateOrder(OrderModifyForm orderModifyForm) {
        Order order = showSelectedOrder(orderModifyForm.getOrderId());
        order.setOrderDate(convertStringIntoDate(orderModifyForm.getOrderDate()));
        if(orderModifyForm.getOrderSNumber().isEmpty()) {
            orderModifyForm.setOrderSNumber(null);
        }
        order.setOrderSNumber(orderModifyForm.getOrderSNumber());
        order.setOrderName(orderModifyForm.getOrderName());
        order.setOrderQuantity(orderModifyForm.getOrderQuantity());
        order.setOrderUnitName(orderModifyForm.getUnitName());
        order.setOrderDeliverySpecifiedDate(convertStringIntoDate(orderModifyForm.getDeliverySpecifiedDate()));
        order.setOrderDeliveryDate(convertStringIntoDate(orderModifyForm.getDeliveryDate()));
        order.setOrderBillingDate(convertStringIntoDate(orderModifyForm.getBillingDate()));
        order.setOrderQuotePrice(orderModifyForm.getQuotePrice());
        order.setOrderPrice(orderModifyForm.getOrderPrice());
        order.setOrderStatusId(orderModifyForm.getStatusId());
        order.setOrderRemarks(orderModifyForm.getOrderRemarks());
        orderRepository.saveAndFlush(order);
    }

    public void logicalDeleteOrder(int orderId) {
        Order order = showSelectedOrder(orderId);
        order.setOrderIsDeleted("1");
        orderRepository.save(order);
    }

    public Date convertStringIntoDate(String date) {
        Date convertedDate = null;
        if(date != null) {
            try {
                convertedDate = new SimpleDateFormat("yyyy/MM/dd").parse(date);
            } catch (ParseException e) {
                convertedDate = null;
                return convertedDate;
            }
        }
        return convertedDate;
    }

    public String convertDateIntoString(Date date) {
        String convertedString = null;
        if(date != null) {
            convertedString = new SimpleDateFormat("yyyy/MM/dd").format(date);
        }

        return convertedString;
    }
}
