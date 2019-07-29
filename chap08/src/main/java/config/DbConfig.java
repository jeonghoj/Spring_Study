package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {
    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        DataSource ds = new DataSource();
        ds.setUrl("jdbc:mysql://192.168.27.129/spring5fs?characterEncoding=utf8");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("spring5");
        ds.setPassword("spring5");
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setTestWhileIdle(true);
        ds.setMinEvictableIdleTimeMillis(1000*60*3);
        ds.setTimeBetweenEvictionRunsMillis(1000*10);
        return ds;
    }
}
