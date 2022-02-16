package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.model.BinaryTreeNode;
import com.model.LinkedListNode;
import com.service.StackService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/datastructures")
@Api(value = "StackRestController", tags = "REST Apis related to Stack!!!!")
public class StackController {

	@Autowired
	private StackService StackService;

	@ApiOperation(value = "Add element to the Stack", response = LinkedListNode.class, notes = "Elements are getting added to the Stack")
	@PostMapping("/StackPush")
	public String addToStack(@RequestBody LinkedListNode listNode) {
		log.info("Inside Stack add at the end method");
		StackService.pushToStack(listNode);
		return Constants.DISPLAYING_THE_CONTENTS_OF + listNode.getData();
	}

	@ApiOperation(value = "Pop element from the Stack", response = BinaryTreeNode.class)
	@GetMapping("/popStack")
	public String popToStack() {
		log.info("Inside pop Stack method");
		StackService.popStack();
		return Constants.DISPLAYING_THE_CONTENTS_OF;
	}

	@ApiOperation(value = "Display element from the Stack", response = BinaryTreeNode.class)
	@GetMapping("/displayStack")
	public void displayStack() {
		log.info("Inside display Stack method");
		StackService.displayStack();
	}

}
