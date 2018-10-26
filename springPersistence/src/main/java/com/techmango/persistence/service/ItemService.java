package com.techmango.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmango.persistence.dao.mongo.ItemRepository;
import com.techmango.persistence.entity.mongo.Item;


@Service
public class ItemService {
	
    @Autowired
    ItemRepository itemRepo;
    
    
    public List<Item> getAllItems(){
        return itemRepo.findAll();
    }

    /*Getting a specific item by category from collection*/
    public List<Item> getItemByCategory(String category){
        List<Item> item = itemRepo.findByCategory(category);
        return item;
    }

    /*Getting a specific item by item id from collection*/
    public Item getItemByItemId(long itemId){
        Item item = itemRepo.findByItemId(itemId);
        return item;
    }
    
    
    /*Adding/inserting an item into collection*/
    public Item addItem(long id,String serialNumber, String name,String category) {
        Item item = new Item();
        item.setCategory(category);
        item.setItemId(id);
        item.setName(name);
        item.setSerialNumber(serialNumber);
        return itemRepo.save(item);
    }
    
    
    /*delete an item from collection*/
    public int deleteItem(long itemId){
        Item item = itemRepo.findByItemId(itemId);
        if(item != null){
            itemRepo.delete(item);
            return 1;
        }
        return -1;
    }
}
