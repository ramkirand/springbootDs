package com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@Entity(name = "BinaryTreeNode")
public class BinaryTreeNode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;


	private int value;
	
	@Transient
	private BinaryTreeNode leftChild;
	
	@Transient
	private BinaryTreeNode rightChild;
	
	public BinaryTreeNode(Integer id, int value, BinaryTreeNode leftChild, BinaryTreeNode rightChild) {
		this.id = id;
		this.value = value;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	

}
