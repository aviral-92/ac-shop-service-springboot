package com.ac;

import java.util.Locale;

import javafx.stage.Stage;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.ac.main.ui.LoginPage;
import com.ac.pojo.LoginPagePojo;

@SpringBootApplication
public class AcShopServiceApplication extends AbstractJavaFxApplicationSupport {

	@Value("${className}")
	private String className;
	@Value("${url}")
	private String url;
	@Value("${usrname}")
	private String username;
	@Value("${pswd}")
	private String pswd;
	
	@Autowired
	LoginPage loginPage;

	public static void main(String[] args) {
		launchApp(AcShopServiceApplication.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		loginPage.start(stage);
	}

	public static LoginPagePojo test() {

		String confFile = "ReadPropertiesFile.xml";
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				confFile);
		LoginPagePojo readEnglishProperties = (LoginPagePojo) context
				.getBean("EnglishProperties");
		context.close();
		return readEnglishProperties;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:locale/messages");
		messageSource.setCacheSeconds(3600);
		return messageSource;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}
	
	@Bean
	public DataSource getDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(className);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(pswd);
		return dataSource;
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}

}
