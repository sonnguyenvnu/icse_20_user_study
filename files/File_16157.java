package org.hswebframework.web.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;
import java.util.Optional;

/**
 * 跨域设置，支�?�?�?�的请求路径，�?置�?�?�的跨域信�?��?置
 *
 * <p>
 * Example:
 * <pre class="code">
 *   {@code
 *      hsweb:
 *        cors:
 *          enable: true
 *          configs:
 *            - /**:
 *                allowed-headers: "*"
 *                allowed-methods: ["GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"]
 *                allowed-origins: ["http://xxx.example.com"]
 *                allow-credentials: true
 *                maxAge: 1800
 *   }
 * </pre>
 *
 * enable设为true，但是configs未�?置，将使用已下的默认�?置:
 * <pre class="code">
 *   {@code
 *      hsweb:
 *        cors:
 *          enable: true
 *          configs:
 *            - /**:
 *                allowed-headers: "*"
 *                allowed-methods: ["GET", "POST", "HEAD"]
 *                allowed-origins: "*"
 *                allow-credentials: true
 *                maxAge: 1800
 *   }
 * </pre>
 *
 * <p>
 * <b>注�?:</b>
 * �?置文件中对象的属性�??在 SpringBoot 2.x 版本开始�?在支�?特殊字符，会将特殊字符过滤掉，
 * 仅支�?{@code [A-Za-z0-9\-\_]}，具体细节请查看{@code ConfigurationPropertyName}类的{@code adapt}方法
 *
 * @author zhouhao
 * @author Jia
 * @since 1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "hsweb.cors", name = "enable", havingValue = "true")
@EnableConfigurationProperties(CorsProperties.class)
public class CorsAutoConfiguration {

    /**
     * 默认匹�?全部
     */
    private static final String CORS_PATH_ALL = "/**";

    @Bean
    public CorsFilter corsFilter(CorsProperties corsProperties) {
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();

        Optional.ofNullable(corsProperties.getConfigs())
                .orElse(Collections.singletonList(Collections.singletonMap(CORS_PATH_ALL,
                        new CorsProperties.CorsConfiguration().applyPermitDefaultValues())))
                .forEach((map) ->
                        map.forEach((path, config) ->
                                corsConfigurationSource.registerCorsConfiguration(path, buildConfiguration(config))
                        )
                );

        return new CorsFilter(corsConfigurationSource);
    }

    private CorsConfiguration buildConfiguration(CorsProperties.CorsConfiguration config) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(config.getAllowedHeaders());
        corsConfiguration.setAllowedMethods(config.getAllowedMethods());
        corsConfiguration.setAllowedOrigins(config.getAllowedOrigins());
        corsConfiguration.setAllowCredentials(config.getAllowCredentials());
        corsConfiguration.setExposedHeaders(config.getExposedHeaders());
        corsConfiguration.setMaxAge(config.getMaxAge());

        return corsConfiguration;
    }
}
