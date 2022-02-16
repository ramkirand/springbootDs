package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.BinaryTreeNode;

@Repository
public interface BinaryTreeRepository extends CrudRepository<BinaryTreeNode, Integer>{

}
