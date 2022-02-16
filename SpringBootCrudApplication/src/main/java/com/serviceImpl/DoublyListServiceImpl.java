package com.serviceImpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exception.DoublyListException;
import com.model.DoublyNode;
import com.repository.DoublyListRepository;
import com.repository.LinkedListRepository;
import com.service.DoublyListService;
import com.service.LinkedListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@SuppressWarnings("unused")
public class DoublyListServiceImpl implements DoublyListService {

	private DoublyNode head;
	private FileOutputStream fout;
	private FileInputStream fin;

	@Autowired
	private DoublyListRepository doublyListRepository;

	DoublyListServiceImpl() {
		head = new DoublyNode();
		head.setPrev(null);
		head.setNext(null);
		head.setData(0);
		this.fout = null;
		this.fin = null;
	}

	@Override
	public String add(int x) {
		DoublyNode temp = new DoublyNode();
		temp.setData(x);
		temp.setNext(head.getNext());
		if (head.getNext() != null)
			head.getNext().setPrev(temp);
		head.setNext(temp);
		temp.setPrev(head);
		DoublyNode ptr = head.getNext();
		log.info("x : " + x);
		doublyListRepository.save(temp);
		return "element added";
	}

	@Override
	public String display() {
		StringBuilder sb = new StringBuilder();
		DoublyNode ptr = head.getNext();
		log.info("tail----> ");
		while (ptr != null) {
			log.info(ptr.getData() + ",");
			sb.append(ptr.getData() + ",");
			ptr = ptr.getNext();
		}
		log.info(" <--- head");
		return sb.toString();
	}

	@Override
	public void writeToFile() {
		try {
			fout = new FileOutputStream("displayDoubly.txt");
			DoublyNode ptr = head.getNext();
			fout.write(String.valueOf("tail----> ").getBytes());
			while (ptr != null) {
				fout.write(Integer.toString(ptr.getData()).getBytes());
				fout.write(String.valueOf(" ").getBytes());
				ptr = ptr.getNext();
			}
			fout.write(String.valueOf(" <--- head").getBytes());
			fout.close();
			log.info("success...");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public void readFromFile() {
		try {
			fin = new FileInputStream("displayDoubly.txt");
			int i = 0;
			while ((i = fin.read()) != -1) {
				log.info("" + (char) i);
			}
			fin.close();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public DoublyNode doublyListSearch(int x) {
		DoublyNode ptr = head.getNext();
		while (ptr != null) {
			if (ptr.getData() == x)
				break;
			ptr = ptr.getNext();
		}
		if (ptr == null)
			log.info("Entered number: " + x + " not present to delete.");
		else
			log.info("Number is present : " + x);
		return ptr;
	}

	@Override
	public void deleteElementFromDoublyList(int x) {
		try {
			if (doublyListSearch(x) == null) {
				throw new DoublyListException("Number is not present in the DoublyLinkedList " + x);
			} else {
				DoublyNode ptr = doublyListSearch(x);
				if (ptr.getPrev() == null) {
					ptr = head;
					head = ptr.getNext();
					ptr = null;
				} else {
					ptr.getPrev().setNext(ptr.getNext());
					ptr = null;
				}
			}
		} catch (DoublyListException ne) {
			ne.printStackTrace();
		}
	}

}
