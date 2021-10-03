package com.yanyu.service.impl;

import com.yanyu.mapper.ProductTypeMapper;
import com.yanyu.pojo.ProductType;
import com.yanyu.pojo.ProductTypeExample;
import com.yanyu.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {
    //数据访问层对象
    @Autowired
    ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAll() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }

}
