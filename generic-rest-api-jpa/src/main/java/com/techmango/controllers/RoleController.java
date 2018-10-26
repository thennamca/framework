package com.techmango.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techmango.models.Role;

@RestController
@RequestMapping("/roles")
public class RoleController extends GenericRestController<Role> {

}
