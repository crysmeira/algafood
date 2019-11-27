package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name =  "order_table")
public class Order {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private BigDecimal subtotal;
	
	@Column(nullable = false)
	private BigDecimal deliveryFee;
	
	@Column(nullable = false)
	private BigDecimal totalValue;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime creationDate;
	
	@Column(nullable = true, columnDefinition = "datetime")
	private LocalDateTime confirmationDate;
	
	@Column(nullable = true, columnDefinition = "datetime")
	private LocalDateTime cancelationDate;
	
	@Column(nullable = true, columnDefinition = "datetime")
	private LocalDateTime deliveryDate;
	
	@JsonIgnore
	@Embedded
	private Address deliveryAddress;
	
	@Column(nullable = false)
	private OrderStatus orderStatus;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private PaymentType paymentType;
	
	@ManyToOne
	@JoinColumn(name = "user_client_id", nullable = false)
	private User client;
	
	@JsonIgnore
	@Embedded
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<>();
}
