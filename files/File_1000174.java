package com.lou.springboot.controller;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DataSourceController {

    //自动�?置，因此�?�以直接通过 @Autowired 注入进�?�
    @Autowired
    DataSource dataSource;

    // 查询数�?��?信�?�
    @GetMapping("/datasource")
    public Map<String, Object> datasource() throws SQLException {
        Map result = new HashMap();
        result.put("数�?��?类�??", dataSource.getClass()+"");
        // 获�?�数�?�库连接对象
        Connection connection = dataSource.getConnection();
        // 判断连接对象是�?�为空
        result.put("能�?�正确获得连接", connection != null);
        connection.close();
        return result;
    }

    // 查询数�?��?信�?�
    @GetMapping("/datasource2")
    public Map<String, Object> datasource2() throws SQLException {
        DruidDataSource druidDataSource = (DruidDataSource)dataSource;
        Map result = new HashMap();
        result.put("数�?��?类�??", druidDataSource.getClass()+"");
        // 获�?�数�?�库连接对象
        Connection connection = druidDataSource.getConnection();
        // 判断连接对象是�?�为空
        result.put("能�?�正确获得连接", connection != null);
        result.put("initialSize值为",druidDataSource.getInitialSize());
        result.put("maxActive值为",druidDataSource.getMaxActive());
        result.put("minIdle值为",druidDataSource.getMinIdle());
        result.put("validationQuery值为",druidDataSource.getValidationQuery());
        result.put("maxWait值为",druidDataSource.getMaxWait());
        connection.close();
        return result;
    }

}
