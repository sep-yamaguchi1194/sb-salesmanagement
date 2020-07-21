package sep.salesmanagement.yt.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import sep.salesmanagement.yt.entity.Order;

public class OrderSpecifications {
	public Specification<Order> orderNameContains(String orderName) {
		return StringUtils.isEmpty(orderName) ? null : new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("orderName"), "%" + orderName + "%");
			}
		};
	}

	public Specification<Order> orderCustomerIdEquals(int orderCustomerId) {
		return StringUtils.isEmpty(orderCustomerId) ? null : new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("orderCustomerId"), orderCustomerId);
			}
		};
	}

	public Specification<Order> orderStatusIdEquals(String orderStatusId) {
		return StringUtils.isEmpty(orderStatusId) ? null : new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("orderStatusId"), orderStatusId);
			}
		};
	}
}
