package com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Entity(name = "DoublyNode")
public class DoublyNode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;

	@Transient
	private DoublyNode prev;

	@Transient
	private DoublyNode next;


	private int data;

	public DoublyNode(Integer id, int data, DoublyNode next, DoublyNode prev) {
		this.id = id;
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
	
	public DoublyNode() {
		this.data = 0;
		this.next = null;
		this.prev = null;
	}
	
}
