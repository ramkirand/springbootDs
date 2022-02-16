package com.serviceImpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exception.LinkedListException;
import com.model.LinkedListNode;
import com.repository.LinkedListRepository;
import com.service.LinkedListService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Component
public class LinkedListServiceImpl implements LinkedListService {
	private LinkedListNode head;
	private LinkedListNode tail;
	private FileOutputStream fileOutputStream;
	private FileInputStream fileInputStream;

	@Autowired
	private LinkedListRepository linkedListRepository;

	public LinkedListServiceImpl() {
		this.head = null;
		this.fileOutputStream = null;
		this.fileInputStream = null;
		this.tail = null;
	}

	@Override
	public String addAtTheBiginning(LinkedListNode numberToAddAtTheBegening) throws LinkedListException {
		if (numberToAddAtTheBegening == null)
			throw new LinkedListException("Adding null node");
		LinkedListNode temp = new LinkedListNode();
		temp.setData(numberToAddAtTheBegening.getData());
		temp.setNext(head);
		head = temp;

		linkedListRepository.save(temp);
		return "element added";
	}

	@Override
	public String addAtTheEnd(LinkedListNode x) {
		LinkedListNode temp = new LinkedListNode();
		temp.setData(x.getData());

		if (head == null) {
			head = temp;
		} else {
			if (tail != null)
				tail.setNext(temp);
		}

		tail = temp;
		linkedListRepository.save(temp);
		return "element added";
	}

	@Override
	public String display() {
		StringBuilder sb = new StringBuilder();
		LinkedListNode ptr = head;
		log.info("tail----> ");
		while (ptr != null) {
			log.info(ptr.getData() + ",");
			sb.append(ptr.getData()).append(" ");
			ptr = ptr.getNext();
		}
		log.info(" <--- head");
		return sb.toString();
	}

	@Override
	public void writeToFile() {
		try {
			fileOutputStream = new FileOutputStream("displayList.txt");
			LinkedListNode ptr = head;
			fileOutputStream.write(String.valueOf("tail----> ").getBytes());
			while (ptr != null) {
				fileOutputStream.write(Integer.toString(ptr.getData()).getBytes());
				fileOutputStream.write(String.valueOf(" ").getBytes());
				ptr = ptr.getNext();
			}
			fileOutputStream.write(String.valueOf(" <--- head").getBytes());
			fileOutputStream.close();
			log.info("writeToFile success...");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public void readFromFile() {
		try {
			fileInputStream = new FileInputStream("displayList.txt");
			int i = 0;
			while ((i = fileInputStream.read()) != -1) {
				System.out.print((char) i);
			}
			fileInputStream.close();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		log.info("read from file success...");
	}

	@Override
	public void deleteLinkedListElement(int numberTodelete) throws LinkedListException {
		if (!(linkedListSearch(numberTodelete) != null)) {
			throw new LinkedListException("Number is not present in the LinkedList " + numberTodelete);
		}
		if (linkedListSearch(numberTodelete) != null) {
			LinkedListNode prev = null;
			LinkedListNode ptr = head;
			while (ptr != null) {
				if (ptr.getData() == numberTodelete)
					break;
				prev = ptr;
				ptr = ptr.getNext();
			}
			if (prev == null) {
				prev = head;
				head = head.getNext();
				prev = null;
			} else {
				prev.setNext(ptr.getNext());
				ptr = null;
			}
		}
		log.info("deleteLinkedListElement success ........");
	}

	@Override
	public LinkedListNode linkedListSearch(int numberTosearch) {
		LinkedListNode ptr = head;
		while (ptr != null) {
			if (ptr.getData() == numberTosearch)
				break;
			ptr = ptr.getNext();
		}
		if (ptr == null)
			log.info("Entered number: " + numberTosearch + " not present");
		else
			log.info("Number is present : " + numberTosearch);
		return ptr;
	}

	@Override
	public void delFromFront() {
		if (head == null)
			return;
		if (head.getNext() == null) {
			head = null;
			return;
		} else {
			LinkedListNode ptr = head;
			ptr = ptr.getNext();
			ptr = null;
			return;
		}
	}

	@Override
	public void delFromLast() {
		LinkedListNode prev = null;
		LinkedListNode ptr = head;
		while (ptr.getNext() != null) {
			prev = ptr;
			ptr = ptr.getNext();
		}
		log.info("delFromLast success: " + prev.getNext().getData());
		prev.setNext(null);
		ptr = null;
	}

}
