package com.tao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.tao"})
public class CmsApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CmsApplication.class,args);
    }
}
