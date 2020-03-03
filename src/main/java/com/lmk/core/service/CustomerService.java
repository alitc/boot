package com.lmk.core.service;

import com.lmk.common.utils.Page;
import com.lmk.core.po.Customer;

public interface CustomerService {
    public Page<Customer> findCustomerList(Integer page,Integer rows,String custName,String custSource,String custIndustry,String custLevel);
    public int createCustomer(Customer customer);
    public Customer getCustomerById(Integer id);
    public int updateCustomer(Customer customer);
    public int delete(Integer id);
}
