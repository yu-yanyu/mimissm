package com.yanyu.test;

import com.alibaba.druid.support.json.JSONUtils;
import com.yanyu.mapper.OrdersMapper;
import com.yanyu.mapper.ProductInfoMapper;
import com.yanyu.pojo.ProductInfo;
import com.yanyu.pojo.vo.Ordersvo;
import com.yanyu.pojo.vo.ProductInfoVo;
import com.yanyu.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_dao.xml","classpath:applicationContext_service.xml"})
public class MyTest {
    @Autowired
    ProductInfoMapper productInfoMapper;

    @Autowired
    OrdersMapper ordersMapper;

    @Test
    public void testSelectCondition(){
        ProductInfoVo vo = new ProductInfoVo();
        vo.setPname("4");
        vo.setTypeid(3);
        vo.setLprice(3000);
        vo.setHprice(4000);
        List<ProductInfo> list = productInfoMapper.selectCondition(vo);

        list.forEach(ProductInfo -> System.out.println(ProductInfo));
    }

    @Test
    public void test2(){
        List<Ordersvo> list = ordersMapper.selectAll();
        list.forEach(Ordersvo-> System.out.println(Ordersvo));
    }
    @Test
    public void test3(){
        Ordersvo ordersvo =new Ordersvo();
        ordersvo.setpName("红米4X");

        List<Ordersvo> list = ordersMapper.selectCondition(ordersvo);
        list.forEach(Ordersvo-> System.out.println(Ordersvo));
    }

    @Test
    public void test4(){
    }
}
