package com.tao.serviceBase.execeptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //有参数构造
@NoArgsConstructor //无参数构造
public class MyException extends RuntimeException{
    private Integer code;
    private String msg;


}
