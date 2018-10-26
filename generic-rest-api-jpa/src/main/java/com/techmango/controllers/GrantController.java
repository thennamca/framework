package com.techmango.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techmango.models.Grant;

@RestController
@RequestMapping("/grants")
public class GrantController extends GenericRestController<Grant> {

}
