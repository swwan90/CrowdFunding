package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.Admin;

import java.util.List;

/**
 * @author swwan
 * @create 2020-03-26 17:42
 */
public interface AdminService {

    void saveAdmin(Admin admin);

    List<Admin> getAll();
}
