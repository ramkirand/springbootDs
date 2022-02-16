package com.SpringBoot;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.exception.LinkedListException;
import com.model.LinkedListNode;
import com.repository.LinkedListRepository;
import com.serviceImpl.LinkedListServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LinkedListServiceTest {

	@Autowired
	@InjectMocks
	LinkedListServiceImpl linkedListService;

	@Mock
	LinkedListRepository linkedListRepository;

	@Test
	@DisplayName("Should add a node at the begening of the linkedlist")
	public void shouldAddNodeAtTheBiginning() throws LinkedListException {

		LinkedListNode linkedListNode1 = new LinkedListNode();
		linkedListNode1.setData(12);

		when(linkedListRepository.save(any())).thenReturn(linkedListNode1);
		linkedListService.addAtTheBiginning(linkedListNode1);
		verify(linkedListRepository, times(1)).save(linkedListNode1);
	}

	@Test
	@DisplayName("Should add a node at the tail of the linkedlist")
	public void shouldAddNodeAtTheTail() throws LinkedListException {

		LinkedListNode linkedListNode1 = new LinkedListNode();
		linkedListNode1.setData(12);

		when(linkedListRepository.save(any())).thenReturn(linkedListNode1);
		linkedListService.addAtTheEnd(linkedListNode1);
		verify(linkedListRepository, times(1)).save(linkedListNode1);
	}
}
