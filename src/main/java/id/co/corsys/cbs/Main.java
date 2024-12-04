package id.co.corsys.cbs;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({ "classpath:application.properties", "classpath:jdbc.properties" })
public class Main extends SpringBootServletInitializer {
	@Value("${live.datasource.driverClassName}")
	private String liveDriverClassName;
	@Value("${live.datasource.username}")
	private String liveUsername;
	@Value("${live.datasource.password}")
	private String livePassword;
	@Value("${live.datasource.url}")
	private String liveUrl;

	@Value("${cetak.datasource.driverClassName}")
	private String cetakDriverClassName;
	@Value("${cetak.datasource.username}")
	private String cetakUsername;
	@Value("${cetak.datasource.password}")
	private String cetakPassword;
	@Value("${cetak.datasource.url}")
	private String cetakUrl;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean(name = "live")
	@Primary
	public BasicDataSource liveDS() throws Exception {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(liveDriverClassName);
		basicDataSource.setUrl(liveUrl);
		basicDataSource.setUsername(liveUsername);
		basicDataSource.setPassword(livePassword);
		basicDataSource.setDefaultAutoCommit(false);
		return basicDataSource;
	}

	@Bean(name = "cetak")
	public BasicDataSource cetakDS() throws Exception {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(cetakDriverClassName);
		basicDataSource.setUrl(cetakUrl);
		basicDataSource.setUsername(cetakUsername);
		basicDataSource.setPassword(cetakPassword);
		basicDataSource.setDefaultAutoCommit(false);
		return basicDataSource;
	}

}
