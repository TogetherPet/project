package together.pet;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import lombok.extern.slf4j.Slf4j;
import together.pet.domain.facilities.dto.Facilities;
import together.pet.domain.facilities.mapper.FacilitiesMapper;

@SpringBootTest
@ContextConfiguration(classes = PetTogetherApplication.class)
@Slf4j
class FacilitiesTest {
	
	@Autowired
	FacilitiesMapper facilitiesMapper;
	
	@Test
	@Disabled
	void findAll() {
		List<Facilities> facilities = facilitiesMapper.findAll();
		for(Facilities list : facilities) {
			System.out.println(list);
		}
	}
	
}