/**
 * 
 */
package com.packtpub.springdata.configuration.util;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * @author herb
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.packtpub.springdata.jpa.controller", "com.packtpub.springdata.jpa.service" })
@EnableJpaRepositories("com.packtpub.springdata.jpa.repository")
@EnableWebMvc
@PropertySource("classpath:application.properties")
public class ApplicationContext extends WebMvcConfigurerAdapter {
	
	@Resource
	private Environment env;
	
	
	/**
	 * Configure the data source bean
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		BoneCPDataSource ds = new BoneCPDataSource();
		
		ds.setDriverClass(env.getRequiredProperty("db.driver"));
		ds.setJdbcUrl(env.getRequiredProperty("db.url"));
		ds.setUsername(env.getRequiredProperty("db.username"));
		ds.setPassword(env.getRequiredProperty("db.password"));
		
		return ds;
	}
		
	/**
	 * Configure the entity manager factory bean
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		
		em.setDataSource(dataSource());
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setPackagesToScan(env.getRequiredProperty("entitymanager.packages.to.scan"));
		
		Properties props = new Properties();
		props.put("hiberante.dialect", env.getRequiredProperty("hibernate.dialect"));
		props.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		props.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));
		props.put("hibernate.formate_sql", env.getRequiredProperty("hibernate.format_sql"));
		props.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		em.setJpaProperties(props);
		
		return em;
	}
	
	/**
	 * Configure the transaction manager bean
	 * @return
	 */
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
}
