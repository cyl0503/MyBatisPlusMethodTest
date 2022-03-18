package com.cyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cyl
 */
@SpringBootApplication
/** 扫描接口所在的包 */
//@MapperScan("com.cyl.myapp.mapper")
public class MyappApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyappApplication.class, args);
    }

}
