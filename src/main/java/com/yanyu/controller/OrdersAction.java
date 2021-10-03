package com.yanyu.controller;

import com.github.pagehelper.PageInfo;
import com.yanyu.pojo.Orders;
import com.yanyu.pojo.ProductInfo;
import com.yanyu.pojo.vo.Ordersvo;
import com.yanyu.pojo.vo.ProductInfoVo;
import com.yanyu.service.OrdersService;
import com.yanyu.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersAction {
    //每页显示记录数
    public static final int PAGE_SIZE = 5 ;

    @Autowired
    OrdersService ordersService;

    @RequestMapping("split")
    public String getAll(HttpServletRequest request){
        String page = request.getParameter("page");
            Ordersvo ordersVo = (Ordersvo) request.getSession().getAttribute("ordersVo");
            if(ordersVo!=null&&page!=null){
                    PageInfo o_info = ordersService.splitPage(ordersVo,Integer.parseInt(page),PAGE_SIZE);
                    request.setAttribute("o_info", o_info);
                }else {
                    request.getSession().removeAttribute("ordersVo");
                    PageInfo o_info = ordersService.split(1,PAGE_SIZE);
                    request.setAttribute("o_info",o_info);
                }


        List<String> plist = ordersService.getAllName();
        request.getSession().setAttribute("plist",plist);


        return "orders";
    }

    //ajax分页翻页处理
    @ResponseBody
    @RequestMapping("/ajaxSplit")
    public void ajaxSplit(String oname,String pname,String address,int paystate,int page, HttpSession session) {
        Ordersvo ordersvo = new Ordersvo();
        ordersvo.setoName(oname);
        ordersvo.setpName(pname);
        ordersvo.setoAddress(address);
        ordersvo.setPaystate(paystate);
        session.setAttribute("ordersVo",ordersvo);
        //取得当前page参数的页面的数据
        PageInfo o_info = ordersService.splitPage(ordersvo,page,PAGE_SIZE);
        session.setAttribute("o_info", o_info);
    }

    @ResponseBody
    @RequestMapping("/calculation")
    public Object calculation(int pNum,String pName){

        int calculation = ordersService.calculation(pNum, pName);
        return calculation;
    }

    @RequestMapping("/save")
    public String save(Orders orders,String pName,HttpServletRequest request){
        orders.setOrdertime(new Date());
        orders.setpId(ordersService.selectIdByName(pName));
        int num = ordersService.save(orders);
        if(num>0){
            request.setAttribute("msg","增加成功！");
        }else{
            request.setAttribute("msg","增加失败！");
        }
        //转发到所有商品初始页
        return "forward:/orders/split.action";
    }

    @RequestMapping("one")
    public String one(int oid,int page, Model model){
        Ordersvo byId = ordersService.getById(oid);
        model.addAttribute("orders",byId);
        model.addAttribute("page",page);
        return "updateorders";
    }


    @RequestMapping("/update")
    public String update(Orders orders,String pName,int page,HttpServletRequest request){

        orders.setpId(ordersService.selectIdByName(pName));
        int num = 0;
        num = ordersService.update(orders);
        if(num>0){
            request.setAttribute("msg","更新成功！");
        }else{
            request.setAttribute("msg","更新失败！");
        }
        //转发到所有商品初始页
        return "forward:/orders/split.action?page="+page;
    }

    //ajax异步批量删除
    @ResponseBody
    @RequestMapping(value = "/deleteBatch",produces = "text/html;charset=UTF-8")
    public Object deleteBatch(String ids,HttpSession session){
        //将上传的id拼接字符串拆分为字符数组
        String[] pids = ids.split(",");
        //num受影响行数
        int num=0;
        try {
            num = ordersService.deleteBatch(pids);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //重新分页
        PageInfo o_info = ordersService.split(1,PAGE_SIZE);
        session.setAttribute("o_info",o_info);

        if(num>0){
            return "删除成功！";
        }else{
            return  "删除失败！";
        }

    }
}
