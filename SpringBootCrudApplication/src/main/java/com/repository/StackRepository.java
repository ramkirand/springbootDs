package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.model.LinkedListNode;

@Repository
public interface StackRepository extends CrudRepository<LinkedListNode, Integer> {

}
