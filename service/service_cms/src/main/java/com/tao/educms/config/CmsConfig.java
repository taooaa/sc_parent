package com.tao.educms.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.tao.educms.mapper")
public class CmsConfig {
}
