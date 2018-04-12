package cn.devcenter.framework.starter.swagger.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = SwaggerProperties.PREFIX)
public class SwaggerProperties {

    public static final String PREFIX = "swagger.sandbox";

    private String groupName = "devcenter";

    private String basePackage = "";

    private String author = "gaosong";

    private String homepage = "";

    private String email = "";

    private String title = "研发中心API接口";

    private String description = "研发中心API接口";

    private String version = "4.0.0";

    private String license = "研发中心";

    private String licenseUrl = "";

    private String termsOfServiceUrl = "";


}
