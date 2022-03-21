package com.cyl;

import com.cyl.entity.User;
import com.cyl.service.ProductService;
import com.cyl.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cyl
 * @create 2022/3/18
 */
@SpringBootTest
public class MybatisPlusServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Test
    public void testCount(){
        //  SELECT COUNT( * ) FROM user
        long count = userService.count();
        System.out.println("count="+count);
    }

    @Test
    public void testUserService(){
        //   INSERT INTO user ( id, name, age, email ) VALUES ( 1504755591750668289, 'happy0', 18, 'happy0@abc.com' ) ...
        /*
        Preparing: INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        Parameters: 1504755591750668289(Long), happy0(String), 18(Integer), happy0@abc.com(String)
        Parameters: 1504755591868108802(Long), happy1(String), 19(Integer), happy1@abc.com(String)
        Parameters: 1504755591868108803(Long), happy2(String), 20(Integer), happy2@abc.com(String)
        Parameters: 1504755591868108804(Long), happy3(String), 21(Integer), happy3@abc.com(String)
        Parameters: 1504755591868108805(Long), happy4(String), 22(Integer), happy4@abc.com(String)
        Parameters: 1504755591868108806(Long), happy5(String), 23(Integer), happy5@abc.com(String)
        Parameters: 1504755591868108807(Long), happy6(String), 24(Integer), happy6@abc.com(String)
        Parameters: 1504755591868108808(Long), happy7(String), 25(Integer), happy7@abc.com(String)
        Parameters: 1504755591868108809(Long), happy8(String), 26(Integer), happy8@abc.com(String)
         Parameters: 1504755591868108810(Long), happy9(String), 27(Integer), happy9@abc.com(String)
         */
        List<User> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("happy"+i)
                    .setAge(18+i)
                    .setEmail("happy"+i+"@abc.com");
            list.add(user);
        }
        boolean result = userService.saveBatch(list);
        System.out.println("result="+result);
    }

    @Test
    public void test(){
        // SELECT id,name,age,email,sex,is_deleted FROM t_user WHERE id=1 AND is_deleted=0
        System.out.println(userService.getById(1L));
        // SELECT id,name,price,version FROM t_product11 WHERE id=1
        System.out.println(productService.getById(1L));
    }
}
