package com.tao;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tao"})
@MapperScan("com.tao.educenter.mapper")
public class UcenterApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
