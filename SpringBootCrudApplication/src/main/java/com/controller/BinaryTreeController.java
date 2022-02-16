package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.model.BinaryTreeNode;
import com.service.BinaryTreeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/datastructures")
@Api(value = "BinaryTreeRestController", tags = "REST Apis related to BinaryTree!!!!")
public class BinaryTreeController {

	@Autowired
	private BinaryTreeService binaryTreeService;

	@ApiOperation(value = "Add element to the BinaryTree", response = BinaryTreeNode.class)
	@PostMapping("/treeAdd")
	public String addToTree(@RequestBody BinaryTreeNode binaryTreeNode) {
		log.info("Inside BinaryTree add method : " + binaryTreeNode.getValue());
		binaryTreeService.add(binaryTreeNode.getValue());
		return Constants.DISPLAYING_THE_CONTENTS_OF + binaryTreeNode.getValue();
	}

	@ApiOperation(value = "Level Order Display element of the BinaryTree", response = BinaryTreeNode.class)
	@PostMapping("/treeLevelOrderDisplay")
	public String levelOrderTraversal() {
		log.info("Inside Level Order Display of BinaryTree method");
		binaryTreeService.levelOrder();
		return "Level Order Traversal";
	}
	
	@ApiOperation(value = "Inorder Display element of the BinaryTree", response = BinaryTreeNode.class)
	@GetMapping("/inorderDisplayTree")
	public void inorderDisplayTree() {
		log.info("Inside Inorder display of BinaryTree method");
		binaryTreeService.inorderRecursive();
	}

	@ApiOperation(value = "Level Order Leaf Node Display of the BinaryTree", response = BinaryTreeNode.class)
	@PostMapping("/treeLevelOrderLeafNodeDisplay")
	public String levelOrderleafTraversal() {
		log.info("Inside Level Order Leaf Node Display of the BinaryTree method");
		binaryTreeService.printLeafNodesByLevel();
		return "Level Order leaf Traversal";
	}
	
	@ApiOperation(value = "Display element of the BinaryTree", response = BinaryTreeNode.class)
	@PostMapping("/printLeafNodes")
	public String printLeafNodes() {
		log.info("Inside levelOrderDisplayTree method");
		binaryTreeService.printLeafNodesRecurrsive();
		return "Level Order reverse Traversal";
	}

	@ApiOperation(value = "Reverse Level Order Display element of the BinaryTree", response = BinaryTreeNode.class)
	@PostMapping("/treeLevelOrderReverseDisplay")
	public String levelOrderReverseTraversal() {
		log.info("Inside Reverse Level Order Display of BinaryTree method");
		binaryTreeService.levelOrderReverse();
		return "Level Order reverse Traversal";
	}



}
