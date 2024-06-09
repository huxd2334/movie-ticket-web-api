package com.binhlc.movieticketadmin;

import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.models.info.Info;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableJpaRepositories(basePackages = MovieticketadminApplication.REPO_PACKAGE)
@EntityScan(basePackages = MovieticketadminApplication.ENTITY_PACKAGE)
public class MovieticketadminApplication {

	public static final String REPO_PACKAGE = "com.binhlc.movieticketadmin.domain.repo";
	public static final String ENTITY_PACKAGE = "com.binhlc.movieticketadmin.domain.entity";
	public static final String CONTROLLER_PACKAGE = "com.binhlc.movieticketadmin.controller";

	public static void main(String[] args) {
		SpringApplication.run(MovieticketadminApplication.class, args);
	}

//	@Bean
//	public Docket docket() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage(CONTROLLER_PACKAGE))
//				.paths(PathSelectors.any())
//				.build()
//				.apiInfo(apiInfo());
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder()
//				.version("1.0")
//				.title("Movie Ticket Admin Api")
//				.build();
//	}
@Bean
public OpenAPI customOpenAPI() {
	return new OpenAPI()
			.info(new Info()
					.title("Movie Ticket Admin API")
					.version("1.0"));
}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("public")
				.packagesToScan(CONTROLLER_PACKAGE)
				.pathsToMatch("/**")
				.build();
	}
}
