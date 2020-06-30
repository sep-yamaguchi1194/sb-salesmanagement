package sep.salesmanagement.yt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sep.salesmanagement.yt.entity.Order;
import sep.salesmanagement.yt.repository.OrderRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class SalesManagementService {
	@Autowired
	OrderRepository orderRepository;

	public List<Order> showOrder() {
		return orderRepository.findAll();
	}

}
