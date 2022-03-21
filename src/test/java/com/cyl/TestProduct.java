package com.cyl;

import com.cyl.entity.Product;
import com.cyl.mapper.ProductMapper;
import com.cyl.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author cyl
 * @create 2022/3/21
 */
@SpringBootTest
public class TestProduct {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void test01(){
        //小李查询商品价格
        Product product1 = productMapper.selectById(1L);
        // SELECT id,name,price,version FROM t_product WHERE id=1
        System.out.println("小李查询的商品价格:"+product1.getPrice());

        //小王查询商品价格
        Product product2 = productMapper.selectById(1L);
        // SELECT id,name,price,version FROM t_product WHERE id=1
        System.out.println("小王查询的商品价格:"+product1.getPrice());

        //小李将商品价格+50
        product1.setPrice(product1.getPrice()+50);
        // 在原来获取的基础上版本号+1
        //加乐观锁：UPDATE t_product SET name='外星人笔记本', price=150, version=1 WHERE id=1 AND version=0
        productMapper.updateById(product1);

        //小王将商品价格-30
        product2.setPrice(product2.getPrice()-30);
        //加乐观锁： UPDATE t_product SET name='外星人笔记本', price=70, version=1 WHERE id=1 AND version=0
        productMapper.updateById(product2);

        //老板查询商品价格 不加锁：小王覆盖小李操作
        Product product3 = productMapper.selectById(1L);
        // SELECT id,name,price,version FROM t_product WHERE id=1
        System.out.println("老板查询的商品价格:"+product3.getPrice());
    }

    @Test
    public void test02(){
        //小李查询商品价格
        Product product1 = productMapper.selectById(1L);
        // SELECT id,name,price,version FROM t_product WHERE id=1
        System.out.println("小李查询的商品价格:"+product1.getPrice());

        //小王查询商品价格
        Product product2 = productMapper.selectById(1L);
        // SELECT id,name,price,version FROM t_product WHERE id=1
        System.out.println("小王查询的商品价格:"+product1.getPrice());

        //小李将商品价格+50
        product1.setPrice(product1.getPrice()+50);
        // 在原来获取的基础上版本号+1
        //加乐观锁：UPDATE t_product SET name='外星人笔记本', price=150, version=1 WHERE id=1 AND version=0
        productMapper.updateById(product1);

        //小王将商品价格-30
        product2.setPrice(product2.getPrice()-30);
        //加乐观锁： UPDATE t_product SET name='外星人笔记本', price=70, version=1 WHERE id=1 AND version=0
        int result = productMapper.updateById(product2);
        if (result == 0){
            //操作失败，重试
            Product productNew = productMapper.selectById(1L);
            productNew.setPrice(productNew.getPrice()-30);
            productMapper.updateById(productNew);
        }
        //老板查询商品价格 不加锁：小王覆盖小李操作
        Product product3 = productMapper.selectById(1L);
        // SELECT id,name,price,version FROM t_product WHERE id=1
        System.out.println("老板查询的商品价格:"+product3.getPrice());
    }
}
