package com.lmk.core.web.controller;

import com.lmk.common.utils.Page;
import com.lmk.core.po.BaseDict;
import com.lmk.core.po.Customer;
import com.lmk.core.po.User;
import com.lmk.core.service.BaseDictService;
import com.lmk.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BaseDictService baseDictService;
    //客户来源
    @Value("${customer.from.type}")
    private String FROM_TYPE;
    //客户所属行业
    @Value("${customer.industry.type}")
    private String INDUSTRY_TYPE;
    //客户级别
    @Value("${customer.level.type}")
    private String LEVEL_TYPE;
    //客户列表
    @RequestMapping("/customer/list.action")
    public String list(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10")Integer rows,
                       String custName, String custSource, String custIndustry, String custLevel, Model model){
        Page<Customer> customers=customerService.findCustomerList(page, rows, custName, custSource, custIndustry, custLevel);
        model.addAttribute("page",customers);
        //客户来源
        List<BaseDict> fromType=baseDictService.findBaseDictByTypeCode(FROM_TYPE);
        //客户所属行业
        List<BaseDict> industryType=baseDictService.findBaseDictByTypeCode(INDUSTRY_TYPE);
        //客户级别
        List<BaseDict> levelType=baseDictService.findBaseDictByTypeCode(LEVEL_TYPE);
        //添加参数
        model.addAttribute("fromType",fromType);
        model.addAttribute("industryType",industryType);
        model.addAttribute("levelType",levelType);
        model.addAttribute("custName",custName);
        model.addAttribute("custSource",custSource);
        model.addAttribute("custIndustry",custIndustry);
        model.addAttribute("custLevel",custLevel);
        return "customer";
    }
    @RequestMapping("/customer/create.action")
    @ResponseBody
    public String customerCreate(Customer customer, HttpSession session){
        //获取Session中的当前用户
        User user=(User)session.getAttribute("USER_SESSION");
        //将当前用户id存储在客户对象中
        customer.setCust_create_id(user.getUser_id());
        Date date=new Date();
        Timestamp timestamp=new Timestamp(date.getTime());
        customer.setCust_createtime(timestamp);
        int rows=customerService.createCustomer(customer);
        if(rows>0){
            return "OK";
        }else {
            return "FAIL";
        }
    }
    @RequestMapping("/customer/getCustomerById.action")
    @ResponseBody
    public Customer getCustomerById(Integer id){
        Customer customer=customerService.getCustomerById(id);
        return customer;
    }
    @RequestMapping("/customer/update.action")
    @ResponseBody
    public String customerUpdate(Customer customer){
        int rows=customerService.updateCustomer(customer);
        if(rows>0){
            return "OK";
        }else{
            return "FAIl";
        }
    }
    @RequestMapping("/customer/delete.action")
    @ResponseBody
    public String customerDelete(Integer id){
        int rows=customerService.delete(id);
        if(rows>0){
            return "OK";
        }else {
            return "FAIL";
        }
    }
}
