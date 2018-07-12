package com.example.multidbjpa.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "multidbjpa")
@Getter
@Setter
public class ApplicationConfig {
    private DataSourceProperties db1DataSource;
    private DataSourceProperties db2DataSource;
}
