package com.marcosbarbero.so.multiple.ds.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "multi-datasource")
public class MultipleDataSourceProperties {

    private UserDataSourceProperties user = new UserDataSourceProperties();

    private ItemDataSourceProperties item = new ItemDataSourceProperties();

    @Data
    public static class UserDataSourceProperties {

        private HibernateProperties hibernate = new HibernateProperties();

    }

    @Data
    public static class ItemDataSourceProperties {

        private HibernateProperties hibernate = new HibernateProperties();

    }

    @Data
    public static class HibernateProperties {
        private Map<String, String> properties = new HashMap<>();
    }

}
