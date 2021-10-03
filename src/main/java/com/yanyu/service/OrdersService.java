package com.yanyu.service;

import com.github.pagehelper.PageInfo;
import com.yanyu.pojo.Orders;
import com.yanyu.pojo.vo.Ordersvo;

import java.util.List;

public interface OrdersService {
    List<Ordersvo> getAll();
    List<String> getAllName();

    PageInfo split(int pageNum, int pageSize);

    PageInfo splitPage(Ordersvo ordersvo,int page, int pageSize);
    int calculation(int pNum,String pName);
    int selectIdByName(String pName);
    int save(Orders orders);

    Ordersvo getById(int oid);

    int update(Orders orders);

    int deleteBatch(String[] ids);
}
