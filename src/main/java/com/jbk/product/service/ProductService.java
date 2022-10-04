package com.jbk.product.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jbk.product.Entity.Product;

public interface ProductService {

	public boolean saveProduct(Product product);
	
	public List<Product> getAllProduct();
	
 	public Product getProductById(String ProductId);
	
	public boolean deleteProduct(String productId);
	
	public boolean  updateProduct(Product product);
	
	public List<Product> sortProducts(String sortBy);
	
	public Product getMaxPriceProducts();
	
	public double sumOfProductPrice();
	
	public int getTotalCountOfProduct();
	
	public Map<String, String> uploadSheet(CommonsMultipartFile file,HttpSession httpSession); // read data from excel and write in to db
	
	public String exportToExcel();
	
}


