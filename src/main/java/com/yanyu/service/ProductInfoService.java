package com.yanyu.service;

import com.github.pagehelper.PageInfo;
import com.yanyu.pojo.ProductInfo;
import com.yanyu.pojo.vo.ProductInfoVo;

import java.util.List;

public interface ProductInfoService {

    //显示所有商品、
    List<ProductInfo> getAll();

    //分页功能,PageInfo由分页插件pagehelper提供,pageNum当前页，pageSize页的大小
    PageInfo splitPage(int pageNum,int pageSize);

    //新增商品
    int save(ProductInfo info);

    //按主键id查询商品
    ProductInfo getById(int pid);

    //更新商品
    int update(ProductInfo info);

    //单个商品删除
    int delete(int pid);

    //批量商品删除
    int deleteBatch(String[] ids);

    //多条件查询
    List<ProductInfo> selectCondition(ProductInfoVo vo);

    //多条件查询分页
    PageInfo splitPageVo(ProductInfoVo vo,int pageSize);
}
