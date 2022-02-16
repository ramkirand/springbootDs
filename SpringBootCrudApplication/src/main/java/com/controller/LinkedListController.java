package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.exception.LinkedListException;
import com.model.LinkedListNode;
import com.service.LinkedListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/datastructures")
@Api(value = "LinkedListRestController", tags = "REST Apis related to LinkedList!!!!")
public class LinkedListController {				
														//end points
	@Autowired
	private LinkedListService linkedListService;

	@ApiOperation(value = "Add element to the Linkedlist", response = LinkedListNode.class, notes = "Elements are getting added to the LinkedList")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully added element"),
			  				@ApiResponse(code = 401, message = "Unauthorized - element could not be added")})
	@PostMapping("/LinkedlistAddBeginning")
	public String addToList(@RequestBody LinkedListNode listNode ) throws LinkedListException {
		log.info("Inside LinkedList add at the Beginning method");
		String str = linkedListService.addAtTheBiginning(listNode);
		return Constants.DISPLAYING_THE_CONTENTS_OF + listNode.getData() + str;
	}
	
	@ApiOperation(value = "Add element to the Linkedlist at the end", response = LinkedListNode.class)
	@PostMapping("/linkedListAddEnd")
	public String addToLinkedListAtEnd(@RequestBody LinkedListNode listNode) {
		log.info("Inside LinkedList add at the end method ");
		String str = linkedListService.addAtTheEnd(listNode);
		return Constants.DISPLAYING_THE_CONTENTS_OF + listNode.getData() + str;
	}

	@ApiOperation(value = "Display element of the Linkedlist", response = LinkedListNode.class)
	@GetMapping("/displayLinkedList")
	public String displayLinkedList() {
		log.info("Inside display LinkedList method");
		return Constants.DISPLAYING_THE_CONTENTS_OF + linkedListService.display();
	}

	@ApiOperation(value = "Writing to the Linkedlist file", response = LinkedListNode.class)
	@PostMapping("/writeToLinkedListFile")
	public String writeToLinkedListFile() {
		log.info("Inside writeToFile of LinkedList method");
		linkedListService.writeToFile();
		return Constants.WRITE_TO_FILE;
	}

	@ApiOperation(value = "Reading from the Linkedlist file", response = LinkedListNode.class)
	@GetMapping("/readFromLinkedListFile") // ?
	public String readFromLinkedListFile() {
		log.info("Inside readFromFile method of LinkedList");
		linkedListService.readFromFile();
		return Constants.READ_FROM_FILE;
	}

	@ApiOperation(value = "Delete from the Linkedlist", response = LinkedListNode.class)
	@DeleteMapping("/deleteLinkedListElement")
	public String deleteLinkedListElement(@RequestBody LinkedListNode listNode) throws LinkedListException {
		log.info("Inside delete LinkedList Element method");
		linkedListService.deleteLinkedListElement(listNode.getData());
		return Constants.DISPLAYING_THE_CONTENTS_OF + listNode.getData();
	}

	@ApiOperation(value = "Search the Linkedlist", response = LinkedListNode.class)
	@PostMapping("/searchLinkedList")
	public String searchLinkedList(@RequestBody LinkedListNode listNode) {
		log.info("Inside LinkedList search method");
		linkedListService.linkedListSearch(listNode.getData());
		return Constants.DISPLAYING_THE_CONTENTS_OF + listNode.getData();
	}
	
	@ApiOperation(value = "Delete from the front of the Linkedlist", response = LinkedListNode.class)
	@PostMapping("/deleteLinkedListFromFront")
	public String deleteLinkedListElementFromFront() throws LinkedListException {
		log.info("Inside delete LinkedList Element method");
		linkedListService.delFromFront();
		return Constants.DISPLAYING_THE_CONTENTS_OF;
	}


}
