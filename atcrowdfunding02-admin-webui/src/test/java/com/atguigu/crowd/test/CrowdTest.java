package com.atguigu.crowd.test;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author swwan
 * @create 2020-03-26 18:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Test
    public void testTx(){
        Admin admin = new Admin(null, "Lily","222222","莉莉", "Lily@126.com",null);
        adminService.saveAdmin(admin);
    }

    @Test
    public void testConnection() throws SQLException{
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }

    @Test
    public void testInsertAdmin(){
        Admin admin = new Admin(null,"jack","123456","杰克","jack@126.com",null);
        int count = adminMapper.insert(admin);

        System.out.println("受影响的行数 = " + count);
    }

}
