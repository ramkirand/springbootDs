package com.service;

import org.springframework.stereotype.Service;
import com.model.DoublyNode;

@Service
public interface DoublyListService {

	String add(int x);

	String display();

	void writeToFile();

	void readFromFile();

	void deleteElementFromDoublyList(int x);

	DoublyNode doublyListSearch(int x);

}
