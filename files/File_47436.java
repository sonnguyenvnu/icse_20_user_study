package cn.abel.config;

import cn.abel.enums.DatabaseTypeEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yyb
 * @time 2019/3/27
 */
@Configuration
@MapperScan(basePackages = "cn.abel.dao")
public class DynamicDataSourceConfig {
    @Value("${spring.datasource.primary.url}")
    private String primaryUrl;
    @Value("${spring.datasource.user.url}")
    private String userUrl;
    @Value("${mybatis.mapper-locations}")
    private String resources;
    //当两个数�?�库连接账�?�密�?�?一样时
//    @Value("${spring.datasource.user.username}")
//    private String userName;
//    @Value("${spring.datasource.user.password}")
//    private String password;

    @Autowired
    private HikariConfig hikariConfig;

    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource getPrimaryDataSource() {
        return hikariConfig.getHikariDataSource(primaryUrl);
    }

    @Bean(name = "userDataSource")
    public DataSource getUserDataSource() {
        return hikariConfig.getHikariDataSource(userUrl);
    }


    //当两个数�?�库连接账�?�密�?�?一样时使用
//    @Bean(name = "userDataSource")
//    public DataSource getUserDataSource() {
//        return hikariConfig.getHikariDataSource(userUrl, userName, password);
//    }


    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("primaryDataSource") DataSource primaryDataSource,
                                               @Qualifier("userDataSource") DataSource miaoMoreDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseTypeEnum.PRIMARY, primaryDataSource);
        targetDataSources.put(DatabaseTypeEnum.USER, miaoMoreDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(primaryDataSource);// 默认的datasource设置为myTestDbDataSource
        return dataSource;
    }

    /**
     * 根�?�数�?��?创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(resources));
        return bean.getObject();
    }
}
