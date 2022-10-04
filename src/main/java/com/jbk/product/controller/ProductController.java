package com.jbk.product.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jbk.product.Entity.Product;
import com.jbk.product.exception.ProductNotFoundException;
import com.jbk.product.service.ProductService;
import com.jbk.product.sort.ProductIdComparator;
import com.mysql.fabric.Response;

import ch.qos.logback.core.joran.conditional.ElseAction;
import ch.qos.logback.core.joran.conditional.IfAction;

@RestController
public class ProductController {
	@Autowired
	private ProductService service;
	
	@PostMapping(value="/saveProduct")
	public ResponseEntity<Boolean>saveProduct(@Valid @RequestBody Product product){
		boolean isAdded = service.saveProduct(product);
		
		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded,HttpStatus.CREATED);
			
		}else {
			return new ResponseEntity<Boolean>(isAdded,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//#############################
	                          //####################################################
	
	@GetMapping(value="/getProductById/{productId}")
	public ResponseEntity<Product>getProductById(@PathVariable String productId){
		Product product =service.getProductById(productId);
		if (product!=null) {
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		}else {
			throw new ProductNotFoundException("Product Not Found For Id : "+productId);
		}
	}
	
	//#############################
                              //####################################################
	
	@GetMapping(value="/getAllProduct")
	public ResponseEntity<List <Product>>getAllProduct(){
		List<Product>allProducts=service.getAllProduct();
		if(!allProducts.isEmpty()) {
			return new ResponseEntity<List<Product>>(allProducts,HttpStatus.OK);
			
		}else throw new ProductNotFoundException("Products Not Found  : ");
		}
	
	
	//#############################
                            //####################################################
	
	@DeleteMapping(value="/deleteProductById")
		public ResponseEntity<Boolean>deleteProductById(@RequestParam String productId){
		boolean isDeleted = service.deleteProduct(productId);
		if (isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted , HttpStatus.OK);
			
		}else {
			throw new ProductNotFoundException("Products Not Found  : ");
			
		}
			
	}
	
	//#############################
                             //####################################################
	
	@PutMapping(value ="updateProduct")
	public ResponseEntity<Boolean>updateProduct(@RequestBody Product product){
		boolean isUpdated = service.updateProduct(product);
		if (isUpdated) {
			return new ResponseEntity<Boolean>(isUpdated ,HttpStatus.OK);
		}else {
			 	throw new ProductNotFoundException("Product Not Found For update  with Id : "+product.getProductId());
		}
	}
	
	//#############################
                              //####################################################

	@GetMapping(value="/sortproducts")
			public ResponseEntity<List<Product>>sortProducts(@RequestParam String sortBy){
		List<Product>sortedProducts =service.sortProducts(sortBy);
		if (!sortedProducts.isEmpty()) {
			return new ResponseEntity<List<Product>>(sortedProducts, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
	}
	
	//#############################
                         //####################################################
	
	@GetMapping(value ="/getMaxPricrProduct")
	public ResponseEntity<Product>getMaxPriceProducts(){
	Product product = service.getMaxPriceProducts();
	if( product!=null) {
		return new ResponseEntity<Product>(product, HttpStatus.OK);
		
	}else {
		return new ResponseEntity<Product>( HttpStatus.NO_CONTENT);
	}	
	}
	
	//#############################
                         //####################################################
	
	@GetMapping(value="/sumOfProductPrice")
	public ResponseEntity<Double>sumOfProductPrice(){
		double sum = service.sumOfProductPrice();
		
		if(sum>0)
			return new ResponseEntity<Double>(sum , HttpStatus.OK);
		
	else {
		return new ResponseEntity<>( HttpStatus.NO_CONTENT);
	}
	}
	
	//#############################
                                 //####################################################
	
	@GetMapping(value ="/getTotalCountOfProducts")
	public ResponseEntity<Integer>getTotaCountOfProducts(){
		
		int count = service.getTotalCountOfProduct();
	
		if (count> 0) 
			
		
			return new ResponseEntity<Integer>(count, HttpStatus.OK);
		
		else {
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
			
			
		}		
     }
	
	@PostMapping(value = "/uploadSheet")
	public ResponseEntity<Map<String, String>> uploadSheet(@RequestParam CommonsMultipartFile file,
			HttpSession session) {
		Map<String, String> map = service.uploadSheet(file, session);
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	
      
		
	}
	
	}




