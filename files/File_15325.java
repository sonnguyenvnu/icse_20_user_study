/*Copyright ©2016 TommyLemon(https://github.com/TommyLemon/APIJSON)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package apijson.demo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import zuo.biao.apijson.Log;


/**SpringBootApplication
 * �?�键这个类 > Run As > Java Application
 * @author Lemon
 */
@Configuration
@SpringBootApplication
public class APIJSONApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(APIJSONApplication.class, args);

		Log.DEBUG = true; //上线生产环境�?改为 false，�?��?输出 APIJSONORM 的日志 以�?� SQLException 的原始(�?感)信�?�
		
		System.out.println("\n\n\n\n\n<<<<<<<<<<<<<<<<<<<<<<<<< APIJSON >>>>>>>>>>>>>>>>>>>>>>>>\n");
		System.out.println("开始测试:远程函数 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
		try {
			DemoFunction.test();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n完�?测试:远程函数 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");


		System.out.println("\n\n\n开始测试:请求校验 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
		try {
			StructureUtil.test();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n完�?测试:请求校验 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		System.out.println("\n\n<<<<<<<<<<<<<<<<<<<<<<<<< APIJSON已�?�动 >>>>>>>>>>>>>>>>>>>>>>>>\n");
	}


	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {

			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.setPort(8080); //自定义端�?��?�，如果和 TiDB 等其它程�?端�?�有冲�?，�?�改为 8081, 9090, 9091 等未被�?�用的端�?� 	
			}
		};
	}

	//支�?JavaScript跨域请求<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/** 
	 * 跨域过滤器 
	 * @return 
	 */  
	@Bean  
	public CorsFilter corsFilter() {  
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);  
	}  
	/**CORS跨域�?置
	 * @return
	 */
	private CorsConfiguration buildConfig() {  
		CorsConfiguration corsConfiguration = new CorsConfiguration();  
		corsConfiguration.addAllowedOrigin("*"); //�?许的域�??或IP地�?�
		corsConfiguration.addAllowedHeader("*"); //�?许的请求头
		corsConfiguration.addAllowedMethod("*"); //�?许的HTTP请求方法
		corsConfiguration.setAllowCredentials(true); //�?许�?��?跨域凭�?�，�?端Axios存�?�JSESSIONID必须�?
		return corsConfiguration;  
	}  
	//支�?JavaScript跨域请求 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
