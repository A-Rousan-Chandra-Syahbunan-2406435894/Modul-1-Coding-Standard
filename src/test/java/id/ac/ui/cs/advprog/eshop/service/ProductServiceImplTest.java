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
}
