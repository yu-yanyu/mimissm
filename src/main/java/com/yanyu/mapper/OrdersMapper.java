package com.yanyu.mapper;

import com.yanyu.pojo.Orders;
import com.yanyu.pojo.OrdersExample;
import com.yanyu.pojo.ProductInfo;
import com.yanyu.pojo.vo.Ordersvo;
import com.yanyu.pojo.vo.ProductInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersMapper {
    int countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Integer oId);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Integer oId);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<Ordersvo> selectAll();
    List<String> getAllName();
    //多条件查询
    List<Ordersvo> selectCondition(Ordersvo vo);

    int selectMoneyByName(String pName);

    int selectIdByName(String pName);

    Ordersvo getById(int oid);

    int deleteBatch(String[] ids);
}