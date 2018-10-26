package com.techmango.persistence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techmango.persistence.entity.mongo.Item;
import com.techmango.persistence.service.ItemService;


@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getItems(){
        return itemService.getAllItems();
    }

    @RequestMapping(value = "/{category}" , method = RequestMethod.GET) 
    @ResponseBody
    public List<Item> getItem(@RequestParam("category") String category){
        return itemService.getItemByCategory(category);
    }

    @RequestMapping(value = "/{item}" , method = RequestMethod.GET) 
    @ResponseBody
    public Item getItemById(@RequestParam("item") long item){
        return itemService.getItemByItemId(item);
    }

    @RequestMapping(value = "/" , method = RequestMethod.POST) 
    @ResponseBody
    public String addItem(@RequestParam("itemId") long itemId,@RequestParam("serialNumber") String serialNumber,
                          @RequestParam("name") String name,
                          @RequestParam("category") String category){
        if(itemService.addItem(itemId,serialNumber,name,category) != null){
            return "Item Added Successfully";
        }else{
            return "Something went wrong !";
        }
    }
    @RequestMapping(value = "/{itemId}" , method = RequestMethod.DELETE) 
    @ResponseBody
    public String deteteItem(@RequestParam("itemId") int itemId){
        if(itemService.deleteItem(itemId) == 1){
            return "Item Deleted Successfully";
        }else{
            return "Something went wrong !";
        }
    }
}
