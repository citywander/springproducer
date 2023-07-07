package com.sfsf.spring.cdc.producer;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class JpaConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
	  String appVersion = "1";
	   return new OpenAPI()
	    .components(new Components().addSecuritySchemes("basicScheme",
	            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
	    .info(new Info().title("SpringShop API").version(appVersion)
	            .license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
	
	@Bean
	public OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {
	   return openApi -> openApi.path("/foo",
	   new PathItem().get(new Operation().operationId("foo").responses(new ApiResponses()
	   .addApiResponse("default",new ApiResponse().description("")
	   .content(new Content().addMediaType("fatz", new MediaType()))))));
	}
//  
//  @Autowired
//  private Environment env;
//  
//  @Bean
//  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//
//      HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//      vendorAdapter.setDatabase(Database.POSTGRESQL);
//      vendorAdapter.setGenerateDdl(true);
//
//      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//      em.setDataSource(dataSource());
//      em.setPackagesToScan("com.thomasvitale.jpa.demo.model");
//      em.setJpaVendorAdapter(vendorAdapter);
//      em.setJpaProperties(additionalProperties());
//
//      return em;
//  }
//
//  @Bean
//  public DataSource dataSource() {
//      DriverManagerDataSource dataSource = new DriverManagerDataSource();
//      dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
//      dataSource.setUrl(env.getProperty("spring.datasource.url"));
//      dataSource.setUsername(env.getProperty("spring.datasource.username"));
//      dataSource.setPassword(env.getProperty("spring.datasource.password"));
//      dataSource.setSchema(env.getProperty("spring.datasource.schema"));
//      return dataSource;
//  }
//
//  @Bean
//  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//      JpaTransactionManager transactionManager = new JpaTransactionManager();
//      transactionManager.setEntityManagerFactory(emf);
//
//      return transactionManager;
//  }
//
//  @Bean
//  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//      return new PersistenceExceptionTranslationPostProcessor();
//  }
//
//  private Properties additionalProperties() {
//      Properties properties = new Properties();
//      properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
//      //properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
//      //properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
//      //properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
//      return properties;
//  }

}
