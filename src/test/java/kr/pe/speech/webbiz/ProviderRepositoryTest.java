package kr.pe.speech.webbiz;

import kr.pe.speech.webbiz.entity.*;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class ProviderRepositoryTest {

    private final Logger LOGGER = LoggerFactory.getLogger(ProviderRepositoryTest.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRespository providerRespository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProducerRepository producerRepository;

    @Test
    void relationshipTest1() {

        LOGGER.info("[START] relationshipTest1");

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
        LOGGER.info("product: " + productRepository.findById(2L).orElseThrow(RuntimeException::new));
        LOGGER.info("provider" + productRepository.findById(2L).orElseThrow(RuntimeException::new).getProvider());


        LOGGER.info("[DONE] relationshipTest1 ");

    }


    @Test
    void relationshipTest2() {


        LOGGER.info("[START] relationshipTest2");

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

        List<Product> products = providerRespository.findById(provider.getId()).get().getProducts();

        for (Product product : products) {
            System.out.println(product);
        }

        LOGGER.info("[DONE] relationshipTest2");
    }

    @Test
        // 일대일 단뱡향
    void relationshipTest3() {
        Product product = new Product();
        product.setName("스프링부트 JPA");
        product.setPrice(5000);
        product.setStock(500);

        productRepository.save(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setDescription("스프링 부트와 JPA를 함께 볼 수 있는 책");

        productDetailRepository.save(productDetail);

        //
        System.out.println("Product: " + productDetailRepository.findById(productDetail.getId()).get().getProduct());
        System.out.println("ProductDetail: " + productDetailRepository.findById(productDetail.getId()).get());
    }

    @Test
    @Transactional
    void relationshipTest4(){
        Product product1 = saveProduct("동글펜", 500, 1000);
        Product product2 = saveProduct("네모 공책", 100, 2000);
        Product product3 = saveProduct("지우개", 152, 1234);

        Producer producer1 = saveProducer("flature");
        Producer producer2 = saveProducer("wikibooks");

        producer1.addProduct(product1);
        producer1.addProduct(product2);

        producer2.addProduct(product1);
        producer2.addProduct(product2);

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));

        System.out.println( producerRepository.findById(1L).get().getProducts());

    }

    private Product saveProduct(String name, Integer price, Integer stock){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return productRepository.save(product);
    }

    private Producer saveProducer(String name){
        Producer producer = new Producer();
        producer.setName(name);

        return producerRepository.save(producer);

    }

    @Test
    @Transactional
    void relationshipTest5(){
        Product product1 = saveProduct("동글펜", 500, 1000);
        Product product2 = saveProduct("네모 공책", 100, 2000);
        Product product3 = saveProduct("지우개", 152, 1234);

        Producer producer1 = saveProducer("flature");
        Producer producer2 = saveProducer("wikibooks");

        producer1.addProduct(product1);
        producer1.addProduct(product2);

        producer2.addProduct(product1);
        producer2.addProduct(product2);


        product1.addProducer(producer1);
        product1.addProducer(producer2);

        product2.addProducer(producer1);
        product3.addProducer(producer2);

        producerRepository.saveAll( Lists.newArrayList(producer1, producer2));
        productRepository.saveAll(Lists.newArrayList(product1, product2, product3));

        //System.out.println("products : " + producerRepository.findById(1L).get().getProducts());
        System.out.println("producers: " + productRepository.findById(2L).get().getProducers());

    }

}

