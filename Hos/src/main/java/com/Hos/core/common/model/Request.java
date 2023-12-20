package com.Hos.core.common.model;

import com.Hos.core.common.util.Constants;
import com.Hos.core.common.util.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = Constants.REQUEST)
public class Request  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = Constants.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = Constants.USERNAME)
    private String username;

    @Column(name = Constants.TYPE)
    private String type;

    @Column(name = Constants.CATEGORY)
    private String category;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "request_city", joinColumns = {
			@JoinColumn(name = "request_id")}, inverseJoinColumns = {
			@JoinColumn(name = "city_id")})
	private List<City> cities;

    @Column(name = Constants.CREATED_BY, updatable = false, nullable = false)
    private Long createdBy;

    @Column(name = Constants.UPDATED_BY, nullable = true)
    private Long updatedBy ;

    @Column(name = Constants.CREATED_AT, columnDefinition = Constants.TIMESTAMP, nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createdAt;

    @Column(name = Constants.UPDATED_AT, columnDefinition = Constants.TIMESTAMP)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updatedAt;

    @Column(name = Constants.IS_ACTIVE)
    private boolean isActive = true;

    @Column(name = Constants.IS_DELETED)
    private boolean isDeleted = false;

    @Column(name = Constants.IS_REQUEST_CLOSED)
    private boolean isRequestClosed = false;

    @Column(name = Constants.INFO)
    private String info;

    @Column(name = Constants.PHONE_NUMBER)
    private String phoneNumber;
}
