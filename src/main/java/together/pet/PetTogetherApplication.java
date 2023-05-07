package together.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class PetTogetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetTogetherApplication.class, args);
	}

	/**
	 * @return
	 * delete 매핑을 실행하기 위한 자바 객체 등록입니다.
	 * 해당 빈을 선언하면,
	 * 사용하는 곳에서 input type="hidden" 을 통해 전송방식을 결정할수있습니다
	 */
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}
	

  
}
