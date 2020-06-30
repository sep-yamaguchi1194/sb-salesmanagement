package sep.salesmanagement.yt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sep.salesmanagement.yt.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
