package com.techmango.persistence.dao.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.techmango.persistence.entity.mongo.Item;


public interface ItemRepository extends MongoRepository<Item,Long> {
    List<Item> findByCategory(String category);
    Item findByItemId(long itemId);
}
