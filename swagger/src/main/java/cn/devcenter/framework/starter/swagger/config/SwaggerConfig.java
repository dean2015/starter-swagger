package cn.devcenter.framework.starter.swagger.config;

import cn.devcenter.framework.starter.swagger.config.property.SwaggerProperties;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author gaosong
 */
@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig {

    @Autowired
    private SwaggerProperties swaggerProperty;

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public Docket createRestApi() {
        log.info("---------------------Swagger2 is loading---------------------");
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName(swaggerProperty.getGroupName())
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .apiInfo(apiInfo());
        String basePackage = swaggerProperty.getBasePackage();
        ApiSelectorBuilder apiBuilder = null;
        if (StringUtils.isBlank(basePackage)) {
            apiBuilder = docket.select()
                    .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
        } else {
            apiBuilder = docket.select().apis(RequestHandlerSelectors.basePackage(basePackage));
        }
        return apiBuilder.build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(swaggerProperty.getAuthor(), swaggerProperty.getHomepage(),
                swaggerProperty.getEmail());
        return new ApiInfoBuilder().title(swaggerProperty.getTitle())
                .description("basepath: /" + appName).license(swaggerProperty.getLicense())
                .licenseUrl(swaggerProperty.getLicenseUrl()).contact(contact)
                .termsOfServiceUrl(swaggerProperty.getTermsOfServiceUrl())
                .version(swaggerProperty.getVersion()).build();
    }

}
