package com.yanyu.service.impl;

import com.yanyu.mapper.AdminMapper;
import com.yanyu.pojo.Admin;
import com.yanyu.pojo.AdminExample;
import com.yanyu.service.AdminService;
import com.yanyu.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    //数据访问层对象,spring自动创建注入
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(String name, String pwd) {

        //根据用户名在数据库查找用户
        //使用AdminExample对象来封装条件
        AdminExample example = new AdminExample();
        /* select * from admin where a_name = 'admin' */
        //添加用户名a_name = 'admin'条件
        example.createCriteria().andANameEqualTo(name);
        //执行查询
        List<Admin> adminList = adminMapper.selectByExample(example);
        if(adminList.size()>0){
            Admin admin = adminList.get(0);
            //进行密码对比判断 - 密码是密文
            String md5 = MD5Util.getMD5(pwd);
           /* System.out.println(admin.getaPass()+"  "+md5);*/
            if(md5.equals(admin.getaPass())){
                return admin;
            }
        }

        return null;
    }

    @Override
    public int regist(String name, String pwd) {
        //使用AdminExample对象来封装条件
        AdminExample example = new AdminExample();
        /* select * from admin where a_name = 'admin' */
        //添加用户名a_name = 'admin'条件
        example.createCriteria().andANameEqualTo(name);
        //执行查询
        List<Admin> adminList = adminMapper.selectByExample(example);
        if(adminList.size()>0){
            // 用户存在
            return 0;
        }else{
            //加密
            String md5 = MD5Util.getMD5(pwd);
            Admin admin = new Admin();
            admin.setaName(name);
            admin.setaPass(md5);
            int num = adminMapper.insert(admin);
            return num;
        }

    }

    @Override
    public Admin repeat(String name) {
        //根据用户名在数据库查找用户
        //使用AdminExample对象来封装条件
        AdminExample example = new AdminExample();
        /* select * from admin where a_name = 'admin' */
        //添加用户名a_name = 'admin'条件
        example.createCriteria().andANameEqualTo(name);
        //执行查询
        List<Admin> adminList = adminMapper.selectByExample(example);
        if(adminList.size()>0){
            Admin admin = adminList.get(0);
                return admin;
            }
        return null;
    }
}
