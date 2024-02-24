package com.backend.ecommerce.api;


import com.backend.ecommerce.entity.OrderMain;
import com.backend.ecommerce.entity.ProductInOrder;
import com.backend.ecommerce.service.OrderService;
import com.backend.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@CrossOrigin
public class OrderController {
}
