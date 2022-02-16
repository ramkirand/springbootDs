package com.service;

import org.springframework.stereotype.Service;
import com.exception.LinkedListException;
import com.model.LinkedListNode;

@Service
public interface LinkedListService { // API - application programming interface

	String addAtTheBiginning(LinkedListNode linkedListNode) throws LinkedListException;

	String display();

	void writeToFile();

	void readFromFile();

	void deleteLinkedListElement(int numberToDelete) throws LinkedListException;

	LinkedListNode linkedListSearch(int numberTosearch);

	void delFromFront();

	void delFromLast();

	public String addAtTheEnd(LinkedListNode linkedListNode);

}
