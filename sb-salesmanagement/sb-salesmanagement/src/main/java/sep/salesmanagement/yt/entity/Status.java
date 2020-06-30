package sep.salesmanagement.yt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "tbl_sms_statuses")
@Entity
@Data
public class Status implements Serializable {
	@Id
	@Column(name = "status_index")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int statusIndex;

	@Column(name = "status_customer_id")
	private int statusCustomerId;

	@Column(name = "status_id")
	private String statusId;

	@Column(name = "status_name")
	private String statusName;

	@Column(name = "status_description")
	private String statusDescription;
}
