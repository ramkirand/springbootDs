package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface BinaryTreeService {

	void add(int x);
	
	List<List<Integer>> levelOrder();

	void inorderRecursive();

	void printLeafNodesRecurrsive();
	
	List<List<Integer>> levelOrderReverse();
	
	List<List<Integer>> printLeafNodesByLevel();
	
}
