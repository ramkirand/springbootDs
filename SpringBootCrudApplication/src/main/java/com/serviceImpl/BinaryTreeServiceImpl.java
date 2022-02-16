package com.serviceImpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.BinaryTreeNode;
import com.repository.BinaryTreeRepository;
import com.service.BinaryTreeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BinaryTreeServiceImpl implements BinaryTreeService {
	private BinaryTreeNode root;

	@Autowired
	private BinaryTreeRepository binaryTreeRepository;

	BinaryTreeServiceImpl() {
		this.root = null;
	}

	@Override
	public void add(int x) {
		BinaryTreeNode tempRoot = root;
		BinaryTreeNode temp = new BinaryTreeNode();
		temp.setValue(x);

		if (root != null)
			while (true) {
				if (tempRoot.getValue() > x) {
					if (tempRoot.getLeftChild() == null) {
						tempRoot.setLeftChild(temp);
						break;
					} else {
						tempRoot = tempRoot.getLeftChild();
					}
				} else if (tempRoot.getValue() < x) {
					if (tempRoot.getRightChild() == null) {
						tempRoot.setRightChild(temp);
						break;
					} else {
						tempRoot = tempRoot.getRightChild();
					}
				} else {
					log.info("Duplicate");
					break;
				}
			}
		else
			root = temp;
	}
	
	
	@Override
	public List<List<Integer>> levelOrder() {
		List<List<Integer>> ans = new ArrayList<>();

		if (root == null)
			return ans;

		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {
			int size = q.size();
			List<BinaryTreeNode> temp = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				BinaryTreeNode current = q.remove();
				temp.add(current);
				if (current.getLeftChild() != null) {
					q.add(current.getLeftChild());
				}
				if (current.getRightChild() != null) {
					q.add(current.getRightChild());
				}
			}
			log.info("Node : " + temp);
			binaryTreeRepository.saveAll(temp);
		}
		return ans;
	}
	
	
	@Override
	public void inorderRecursive() {
		List<BinaryTreeNode> list = new ArrayList<>();
		inorder(root, list);
		binaryTreeRepository.saveAll(list);
	}

	private List<BinaryTreeNode> inorder(BinaryTreeNode p, List<BinaryTreeNode> list) {
		if (p != null) {
			inorder(p.getLeftChild(), list);
			list.add(p);
			inorder(p.getRightChild(), list);
		}
		return list;
	}
	
	
	@Override
	public void printLeafNodesRecurrsive() {
		printLeafNodesRecurrsive(root);
	}

	private void printLeafNodesRecurrsive(BinaryTreeNode root) {
		if (root == null) {
			return;
		}
		if (root.getLeftChild() == null && root.getRightChild() == null) {
			binaryTreeRepository.save(root);
		}
		if (root.getLeftChild() != null) {
			printLeafNodesRecurrsive(root.getLeftChild());
		}
		if (root.getRightChild() != null) {
			printLeafNodesRecurrsive(root.getRightChild());
		}
	}
	
	
	@Override
	public List<List<Integer>> printLeafNodesByLevel() {
		List<List<Integer>> ans = new ArrayList<>();

		if (root == null)
			return ans;

		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {
			int size = q.size();
			List<BinaryTreeNode> temp = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				BinaryTreeNode current = q.remove();
				temp.add(current);

				if (current.getLeftChild() == null && current.getRightChild() == null) {
					binaryTreeRepository.save(current);
				}
				if (current.getLeftChild() != null) {
					q.add(current.getLeftChild());
				}
				if (current.getRightChild() != null) {
					q.add(current.getRightChild());
				}
			}
			log.info("Node : " + temp);			
		}
		return ans;
	}

	
	@Override
	public List<List<Integer>> levelOrderReverse() {
		List<List<Integer>> ans = new ArrayList<>();

		if (root == null)
			return ans;

		Queue<BinaryTreeNode> q = new LinkedList<>();
		Stack<List<BinaryTreeNode>> stack = new Stack<>();
		q.add(root);

		while (!q.isEmpty()) {
			int size = q.size();
			List<BinaryTreeNode> temp = new ArrayList<>();

			for (int i = 0; i < size; i++) {
				BinaryTreeNode current = q.remove();
				temp.add(current);
				if (current.getLeftChild() != null) {
					q.add(current.getLeftChild());
				}
				if (current.getRightChild() != null) {
					q.add(current.getRightChild());
				}
			}
			log.info("Node : " + temp);
			stack.push(temp);
		}
		log.info("printing stack >>>>>>>>>>>>> : " + stack);
		while (!stack.isEmpty()) {
			binaryTreeRepository.saveAll(stack.pop());
		}
		return ans;
	}

}
