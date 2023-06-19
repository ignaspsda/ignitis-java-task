package com.task.java.ignitis.repository;

import com.task.java.ignitis.entity.Message;
import com.task.java.ignitis.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findBySender(User sender);
}
