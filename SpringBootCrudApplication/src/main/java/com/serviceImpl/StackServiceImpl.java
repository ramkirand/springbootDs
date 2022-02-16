package com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.LinkedListNode;
import com.repository.StackRepository;
import com.service.StackService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StackServiceImpl implements StackService {

	private LinkedListNode head;
	private LinkedListNode tail;
	private LinkedListNode prev;

	StackServiceImpl() {
		this.tail = null;
		this.head = null;
		this.prev = null;
	}

	@Autowired
	private StackRepository stackRepository;

	@Override
	public String pushToStack(LinkedListNode x) {
		LinkedListNode temp = new LinkedListNode();
		temp.setData(x.getData());

		if (head == null) {
			head = temp;
		} else {
			tail.setNext(temp);
		}

		tail = temp;
		stackRepository.save(temp);
		return "element added";
	}

	@Override
	public void popStack() {
		log.info("start display stack >>>>>>>>>>>>>>>>>>>>>");
		if (head == null) {
			log.info("Stack is empty");
			return;
		}
		LinkedListNode ptr = head;
		while (ptr.getNext() != null) {
			prev = ptr;
			ptr = ptr.getNext();
		}
		if (prev == null) {
			prev = head;
			head = null;
			log.info(ptr.getData() + ", is poped out of stack");
			log.info("Stack is empty at the end");
			return;
		} else {
			if (prev.getNext() == null) {
				head = null;
				log.info(ptr.getData() + ", is poped out of stack");
				log.info("Stack is empty at the end");
				return;
			}
			prev.setNext(null);

			log.info(ptr.getData() + ", is poped out of stack");
			prev = null;
		}
		log.info("exit display stack >>>>>>>>>>>>>>>>>>>>>");
	}

	@Override
	public void displayStack() {
		LinkedListNode ptr = head;
		if (ptr == null) {
			log.info("Stack is empty");
			return;
		}
		while (ptr != null) {
			log.info(ptr.getData() + ",");
			ptr = ptr.getNext();
		}
	}

}
