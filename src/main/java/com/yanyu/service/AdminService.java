package com.yanyu.service;

import com.yanyu.pojo.Admin;

public interface AdminService {
    Admin login(String name,String pwd);
    int regist(String name,String pwd);
    Admin repeat(String name);
}
