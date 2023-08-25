package com.speech.cardatabase;

import com.speech.cardatabase.entity.Product;
import com.speech.cardatabase.entity.ProductRepository;
import com.speech.cardatabase.entity.Provider;
import com.speech.cardatabase.entity.ProviderRespository;
import com.speech.cardatabase.web.ProjectController;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

@SpringBootTest
public class ProviderRepositoryTest {

    private final Logger LOGGER = LoggerFactory.getLogger(ProviderRepositoryTest.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRespository providerRespository;

    @Test
    void relationshipTest1(){

        LOGGER.info( "[START] relationshipTest1");

        Provider provider = new Provider();
        provider.setName("OO물산");

        providerRespository.save(provider);

        Product product = new Product();
        product.setName("가위");
        product.setPrice(5000);
        product.setStock(500);
        product.setProvider(provider);

        productRepository.save(product);


        product = new Product();
        product.setName("바위");
        product.setPrice(5);
        product.setStock(5);
        product.setProvider(provider);

        productRepository.save(product);



        //테스트
        LOGGER.info( "product: " + productRepository.findById(2L).orElseThrow(RuntimeException::new));
        LOGGER.info( "provider" + productRepository.findById(2L).orElseThrow(RuntimeException::new).getProvider());


        LOGGER.info( "[DONE] relationshipTest1 ");

    }


    @Test
    void relationshipTest2(){


        LOGGER.info( "[START] relationshipTest2");

        Provider provider = new Provider();
        provider.setName("OO물산");

        providerRespository.save(provider);



        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(2000);
        product1.setStock(100);
        product1.setProvider(provider);

        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("가방");
        product2.setPrice(20000);
        product2.setStock(200);
        product2.setProvider(provider);
        productRepository.save(product2);


        Product product3 = new Product();
        product3.setName("노트");
        product3.setPrice(3000);
        product3.setStock(3000);
        product3.setProvider(provider);
        productRepository.save(product3);

        List<Product> products = providerRespository.findById( provider.getId() ).get().getProducts();

        for( Product product : products ){
            System.out.println(product);
        }

        LOGGER.info( "[DONE] relationshipTest2");



    }
}
