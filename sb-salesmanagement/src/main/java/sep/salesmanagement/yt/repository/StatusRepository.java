package sep.salesmanagement.yt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sep.salesmanagement.yt.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    List<Status> findByStatusCustomerId(int statusCustomerId);
    Status findByStatusCustomerIdAndStatusId(int statusCustomerId, String statusId);
}
