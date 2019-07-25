package info.xiaomo.website;

import info.xiaomo.website.view.UserView;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * æŠŠä»Šå¤©æœ€å¥½çš„è¡¨çŽ°å½“ä½œæ˜Žå¤©æœ€æ–°çš„èµ·ç‚¹ï¼Žï¼Žï½ž
 * ã?„ã?¾ æœ€é«˜ã?®è¡¨ç?¾ ã?¨ã?—ã?¦ æ˜Žæ—¥æœ€æ–°ã?®å§‹ç™ºï¼Žï¼Žï½ž
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/4/1 15:38
 * Description: å?Žå?°ç®¡ç?†å?¯åŠ¨å™¨
 * Copyright(Â©) 2015 by xiaomo.
 **/
@Configuration
@EnableAutoConfiguration
@ComponentScan("info.xiaomo")
@EntityScan("info.xiaomo.*.model")
@EnableTransactionManagement
@EnableJpaRepositories("info.xiaomo.*.dao")
@EnableCaching
@EnableSwagger2
@Controller
public class XiaomoMain implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(XiaomoMain.class, args);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return UserView.INDEX.getName();
    }

    /**
     * æŽ¥å?£
     *
     * @return æŽ¥å?£
     */
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    @ApiIgnore()
    @ApiOperation(value = "é‡?å®šå?‘åˆ°apié¦–é¡µ")
    public ModelAndView api() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("info.xiaomo.website"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Bootä¸­ä½¿ç”¨Swagger2æž„å»ºRESTful APIs")
                .description("apiæ ¹åœ°å?€ï¼šhttp://api.xiaomo.info:8080/")
                .termsOfServiceUrl("https://xiaomo.info/")
                .contact("å°?èŽ«")
                .version("1.0")
                .build();
    }

}
