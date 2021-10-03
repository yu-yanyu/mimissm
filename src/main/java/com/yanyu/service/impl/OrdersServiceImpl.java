package com.yanyu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanyu.mapper.OrdersMapper;
import com.yanyu.pojo.Orders;
import com.yanyu.pojo.ProductInfo;
import com.yanyu.pojo.ProductInfoExample;
import com.yanyu.pojo.vo.Ordersvo;
import com.yanyu.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    OrdersMapper ordersMapper;

    @Override
    public List<Ordersvo> getAll() {
        return ordersMapper.selectAll();
    }

    @Override
    public List<String> getAllName() {
        List<String> allName = ordersMapper.getAllName();
        return allName;
    }

    @Override
    public PageInfo split(int pageNum, int pageSize) {
        //分页插件使用PageHelper工具类完成分页设置(),放在取数据集合之前
        PageHelper.startPage(pageNum,pageSize);


        //查询数据，分页设置PageHelper要放在取数据集合之前
        List<Ordersvo> list1 = ordersMapper.selectAll();

        //将数据封装进PageInfo中,PageInfo自动对list进行分页
        PageInfo<Ordersvo> pageInfo = new PageInfo<>(list1);

        return pageInfo;
    }

    @Override
    public PageInfo splitPage(Ordersvo ordersvo,int page, int pageSize) {
        //分页插件使用PageHelper工具类完成分页设置(),放在取数据集合之前
        PageHelper.startPage(page,pageSize);


        //查询数据，分页设置PageHelper要放在取数据集合之前
        List<Ordersvo> list1 = ordersMapper.selectCondition(ordersvo);

        //将数据封装进PageInfo中,PageInfo自动对list进行分页
        PageInfo<Ordersvo> pageInfo = new PageInfo<>(list1);

        return pageInfo;
    }

    @Override
    public int calculation(int pNum, String pName) {
        int num = ordersMapper.selectMoneyByName(pName);
        return num*pNum;
    }

    @Override
    public int selectIdByName(String pName) {
        int id = ordersMapper.selectIdByName(pName);
        return id;
    }

    @Override
    public int save(Orders orders) {
        int num = ordersMapper.insert(orders);
        return num;
    }

    @Override
    public Ordersvo getById(int oid) {
        return ordersMapper.getById(oid);
    }

    @Override
    public int update(Orders orders) {
        return ordersMapper.updateByPrimaryKey(orders);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return ordersMapper.deleteBatch(ids);
    }

}
