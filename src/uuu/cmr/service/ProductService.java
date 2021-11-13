package uuu.cmr.service;

import java.util.List;

import uuu.cmr.entity.Product;
import uuu.cmr.entity.VGBException;

public class ProductService {
	private ProductsDao dao = new ProductsDao();
	
	public List<Product> getAllProducts() throws VGBException{
		return dao.selectAllProducts();
	}
	
	public List<Product> getProductsByKeyword(String keyword) throws VGBException{
		return dao.selectProductsByKeyword(keyword);
	}
	
	public Product getProductById(String productId) throws VGBException{
		return dao.selectProductById(productId);
	}
}
