package com.tao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.tao.aclservice.mapper")
@ComponentScan(basePackages = {"com.tao"})
public class AclApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(AclApplication.class,args);
    }
}
