package sep.salesmanagement.yt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sep.salesmanagement.yt.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    public Page<Order> findByOrderIsDeleted(String orderIsDeleted, Pageable pageable);
}
