package com.techmango.resource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techmango.resource.model.Grant;




@RestController
@RequestMapping("/grants")
public class GrantController extends GenericRestController<Grant> {

}
