package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateReturnProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        Mockito.when(productRepository.create(product)).thenReturn(product);

        Product savedProduct = productService.create(product);
        assertEquals(product.getProductId(), savedProduct.getProductId());
        Mockito.verify(productRepository, Mockito.times(1)).create(product);
    }

    @Test
    void testFindAllReturnList() {
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        productList.add(product);

        Iterator<Product> iterator = productList.iterator();
        Mockito.when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();
        assertFalse(result.isEmpty());
        assertEquals(product.getProductName(), result.get(0).getProductName());
    }
    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        Mockito.when(productRepository.edit(product)).thenReturn(product);

        Product result = productService.edit(product);
        assertEquals(product.getProductId(), result.getProductId());
        assertEquals(product.getProductName(), result.getProductName());
        Mockito.verify(productRepository, Mockito.times(1)).edit(product);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Mockito.when(productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);

        Product result = productService.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(product.getProductId(), result.getProductId());
        Mockito.verify(productRepository, Mockito.times(1)).delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }
    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");

        // Kita simulasiin kalau repository nemu barangnya
        Mockito.when(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);

        Product result = productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertNotNull(result);
        assertEquals(product.getProductId(), result.getProductId());
        assertEquals(product.getProductName(), result.getProductName());
        Mockito.verify(productRepository, Mockito.times(1)).findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }
}
