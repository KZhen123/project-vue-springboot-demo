package com.project.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserVo {

    private int id;
    private String username;
    private String password;
    private String mobilephone;
    private String email;
    private String uimage;
    private String sex;
    private String school;
    private String faculty;
    private String startime;
    private int userstatus;
    private Date createtime;
    private String sign;
    private int admin;

}
