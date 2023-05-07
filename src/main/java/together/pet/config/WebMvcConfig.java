package together.pet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @author 유선우
 * 파일 경로를 패스로 지정합니다.
 * localhost/summernoteImage/** : 경로를 요청하면,
 * file:///C:/summernote_image/ : 폴더의 이미지를 출력합니다
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	     registry.addResourceHandler("/summernoteImage/**") 
         		 .addResourceLocations("file:///C:/summernote_image/");
	}
	
}
