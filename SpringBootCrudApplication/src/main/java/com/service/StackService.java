package com.service;

import org.springframework.stereotype.Service;

import com.model.LinkedListNode;

@Service
public interface StackService {

	String pushToStack(LinkedListNode x);

//	void pushStack(int x);

	void popStack();

	void displayStack();
}
