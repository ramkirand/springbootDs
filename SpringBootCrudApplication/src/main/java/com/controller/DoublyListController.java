package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.model.DoublyNode;
import com.service.DoublyListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/datastructures")
@Api(value = "DoublyListRestController", tags = "REST Apis related to DoublyList!!!!")
public class DoublyListController {

	@Autowired
	private DoublyListService doublyListService;

	@ApiOperation(value = "Add element to the Doublylist", response = DoublyNode.class, notes = "Elements are getting added to the DoublyList")
	@PostMapping("/doublyListAdd")
	public String addToDoublyList(@RequestBody DoublyNode doublyNode) {
		log.info("Inside DoublyList add method");
		doublyListService.add(doublyNode.getData());
		return Constants.DISPLAYING_THE_CONTENTS_OF + doublyNode.getData();
	}

	@ApiOperation(value = "Display element of the Doublylist", response = DoublyNode.class)
	@GetMapping("/displayDoublyList")
	public String displayDoublyList() {
		log.info("Inside DoublyList display method");
		return Constants.DISPLAYING_THE_CONTENTS_OF + doublyListService.display();
	}

	@ApiOperation(value = "Writing elements to the DoublyList file", response = DoublyNode.class)
	@PostMapping("/writeToFileDoublyList")
	public String writeToDoublyFile() {
		log.info("Inside writeToFile of DoublyList method");
		doublyListService.writeToFile();
		return Constants.WRITE_TO_FILE;
	}

	@ApiOperation(value = "Read elements from the Doublylist file", response = DoublyNode.class)
	@GetMapping("/readFromFileDoublyList") 
	public String readFromDoulbyFile() {
		log.info("Inside readFromFile method of DoublyList");
		doublyListService.readFromFile();
		return Constants.READ_FROM_FILE;
	}

	@ApiOperation(value = "Delete element from the Doublylist", response = DoublyNode.class)
	@DeleteMapping("/deleteElementDoublyList")
	public String deleteElementInDoublyList(@RequestBody DoublyNode doublyNode) {
		log.info("Inside deleteElement from DoublyList method");
		doublyListService.deleteElementFromDoublyList(doublyNode.getData());
		return Constants.DISPLAYING_THE_CONTENTS_OF + doublyNode.getData();
	}

	@ApiOperation(value = "Search element from the Doublylist", response = DoublyNode.class)
	@PostMapping("/searchDoublyList")
	public String searchDoublyList(@RequestBody DoublyNode doublyNode) {
		log.info("Inside doublyList Search method");
		doublyListService.doublyListSearch(doublyNode.getData());
		return Constants.DISPLAYING_THE_CONTENTS_OF + doublyNode.getData();
	}

}
