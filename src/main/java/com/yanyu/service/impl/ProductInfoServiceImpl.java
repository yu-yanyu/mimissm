package com.yanyu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanyu.mapper.ProductInfoMapper;
import com.yanyu.pojo.ProductInfo;
import com.yanyu.pojo.ProductInfoExample;
import com.yanyu.pojo.vo.ProductInfoVo;
import com.yanyu.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    //数据访问层对象
    @Autowired
    ProductInfoMapper productInfoMapper;


    @Override
    public List<ProductInfo> getAll() {
        //没有条件，查询所有
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }


    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
        //分页插件使用PageHelper工具类完成分页设置(),放在取数据集合之前
        PageHelper.startPage(pageNum,pageSize);

        //条件查询 - 主键降序 select* from product_info order by p_id desc
        ProductInfoExample example =new ProductInfoExample();
        //添加条件order by p_id desc
        example.setOrderByClause("p_id desc");
        //查询数据，分页设置PageHelper要放在取数据集合之前
        List<ProductInfo> list = productInfoMapper.selectByExample(example);

        //将数据封装进PageInfo中,PageInfo自动对list进行分页
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public int save(ProductInfo info) {

        return productInfoMapper.insert(info);
    }

    @Override
    public ProductInfo getById(int pid) {
        return productInfoMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int update(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);
    }

    @Override
    public int delete(int pid) {
        return productInfoMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return productInfoMapper.deleteBatch(ids);
    }

    @Override
    public List<ProductInfo> selectCondition(ProductInfoVo vo) {
        return productInfoMapper.selectCondition(vo);
    }

    @Override
    public PageInfo<ProductInfo> splitPageVo(ProductInfoVo vo, int pageSize) {
        //分页插件使用PageHelper工具类完成分页设置(),放在取数据集合之前
        PageHelper.startPage(vo.getPage(),pageSize);

        List<ProductInfo> list = productInfoMapper.selectCondition(vo);

        //将数据封装进PageInfo中,PageInfo自动对list进行分页
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }
}
