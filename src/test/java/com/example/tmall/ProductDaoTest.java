package com.example.tmall;

import com.example.tmall.dao.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

@SpringBootTest
public class ProductDaoTest {
  @Autowired
  private ProductDao productDao;

  @Test
  public void test1() {
    System.out.println(productDao.findByPno(1));
  }
}
