package go.go.info.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("application.properties")
public class DBConnectionConfig {
	
	@Value("${spring.datasource.url}")
	private String dbUrl;
	@Value("${spring.datasource.username}")
	private String dbUserName;
	@Value("${spring.datasource.password}")
	private String dbPassword;
	@Value("${spring.datasource.driver-class-name}")
	private String dbClassName;
	
	@Lazy
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		final HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setUsername(dbUserName);
		hikariConfig.setPassword(dbPassword);
		hikariConfig.addDataSourceProperty("url", dbUrl);
		hikariConfig.setDataSourceClassName(dbClassName);
		hikariConfig.setLeakDetectionThreshold(2000);
		hikariConfig.setPoolName("keysystemDBpool");
		
		final HikariDataSource dataSource = new HikariDataSource(hikariConfig);	
		return dataSource;
	}
}
