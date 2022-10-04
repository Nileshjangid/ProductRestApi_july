package com.jbk.product.dao;

import java.util.List;

import com.jbk.product.Entity.Product;

public interface ProductDao {

	public boolean saveProduct(Product product);
	
	public List<Product> getAllProduct();
	
 	public Product getProductById(String ProductId);
	
	public boolean deleteProduct(String productId);
	
	public boolean  updateProduct(Product product);
	
	public List<Product> sortProducts(String sortBy);
	
	public Product getMaxPriceProducts();
	
	public double countSumOfProductPrice();
	
	public int getTotalCountOfProduct();
	
	public int  uploadProductList(List<Product>list);
	
	
}


