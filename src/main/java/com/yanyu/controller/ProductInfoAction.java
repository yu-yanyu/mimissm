package com.yanyu.controller;

import com.github.pagehelper.PageInfo;
import com.yanyu.pojo.ProductInfo;
import com.yanyu.pojo.vo.ProductInfoVo;
import com.yanyu.service.ProductInfoService;
import com.yanyu.utils.FileNameUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    //每页显示记录数
    public static final int PAGE_SIZE = 5 ;

    //图片名称
    String uuidFileName ="" ;

    //业务逻辑层对象
    @Autowired
    ProductInfoService productInfoService;

    //显示所有商品
    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request){
        List<ProductInfo> list = productInfoService.getAll();
        //传递数据
        request.setAttribute("list",list);
        //跳转页面
        return "product";
    }


    //显示第1页的5条记录
    @RequestMapping("/split")
    public String split(HttpServletRequest request, HttpSession session) {
        PageInfo info = null;
        Object vo = request.getSession().getAttribute("prodVo");
        if(vo != null ){
            info = productInfoService.splitPageVo((ProductInfoVo) vo,PAGE_SIZE);
            session.removeAttribute("prodVo");
        }else {
            //得到第1页的数据
            info = productInfoService.splitPage(1, PAGE_SIZE);
        }
        session.setAttribute("info", info);
        return "product";
    }

    //ajax分页翻页处理
    @ResponseBody
    @RequestMapping("/ajaxSplit")
    public void ajaxSplit(ProductInfoVo vo, HttpSession session) {
        //取得当前page参数的页面的数据
        PageInfo info = productInfoService.splitPageVo(vo,PAGE_SIZE);
        session.setAttribute("info", info);
    }

    //异步Ajax文件上传处理
    @ResponseBody
    @RequestMapping("/ajaxImg")
    //pimage与前台上传的name值一样
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request){
        //生成文件名和后缀，通过FileNameUtil将前台上传的文件名通过UUID重新生成，防止重复
         uuidFileName = FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
        //存取路径  -- 完整的项目本地路径
        String path = request.getServletContext().getRealPath("/image_big");
        //存储  File.separator -> \
        try {
            pimage.transferTo(new File(path+File.separator+uuidFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回josn对象，包含图片路径，
        String s = "{\"imgurl\":\""+uuidFileName+"\"}";
        return s;
    }

    @RequestMapping("/save")
    public String save(ProductInfo info,HttpServletRequest request){
        info.setpImage(uuidFileName);
        info.setpDate(new Date());

        //num受影响行数
        int num=0;
        try {
            num = productInfoService.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num>0){
            request.setAttribute("msg","增加成功！");
        }else{
            request.setAttribute("msg","增加失败！");
        }
        //转发到所有商品初始页
        return "forward:/prod/split.action";
    }

    @RequestMapping("one")
    public String one(int pid, Model model){
        ProductInfo byId = productInfoService.getById(pid);
        model.addAttribute("prod",byId);
        //更新时可以修改图片，防止数据冲突，清除uuidFileName
        uuidFileName="";
        return "update";
    }


    @RequestMapping("/update")
    public String update(ProductInfo info,HttpServletRequest request){
        //判断是否有重新上传的图片，若有则修改图片地址,没有不变
        if(!uuidFileName.equals("")) {
            info.setpImage(uuidFileName);
        }
        //num受影响行数
        int num=0;
        try {
            num = productInfoService.update(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num>0){
            request.setAttribute("msg","更新成功！");
        }else{
            request.setAttribute("msg","更新失败！");
        }
        //转发到所有商品初始页
        return "forward:/prod/split.action";
    }

    //ajax异步删除
    @ResponseBody
    @RequestMapping(value = "/delete",produces = "text/html;charset=UTF-8")
    public Object delete(int pid,HttpSession session){
        //num受影响行数
        int num=0;
        try {
            num = productInfoService.delete(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //重新分页
        PageInfo info = productInfoService.splitPage(1,PAGE_SIZE);
        session.setAttribute("info",info);

        if(num>0){
            return "删除成功！";
        }else{
            return  "删除失败！";
        }

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
            num = productInfoService.deleteBatch(pids);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //重新分页
        PageInfo info = productInfoService.splitPage(1,PAGE_SIZE);
        session.setAttribute("info",info);

        if(num>0){
            return "删除成功！";
        }else{
            return  "删除失败！";
        }

    }

    //多条件查询
    @ResponseBody
    @RequestMapping("/condition")
    public void condition(ProductInfoVo vo,HttpSession session){
        List<ProductInfo> list = productInfoService.selectCondition(vo);
        session.setAttribute("list",list);
    }
}
