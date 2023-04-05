package together.pet;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.sql.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import lombok.extern.slf4j.Slf4j;
import together.pet.PetTogetherApplication;
import together.pet.domain.member.dto.Member;
import together.pet.domain.member.mapper.MemberMapper;

@SpringBootTest
@ContextConfiguration(classes = PetTogetherApplication.class)
@Slf4j
class PetTogetherApplicationTests {
	
	@Autowired
	MemberMapper memberMapper;
	
	@Test
	@Disabled
	void contextLoads() {
		
	}
	@Test
	void join() {
		Member member = new Member();
        member.setAdmin("관리자");
        member.setYear("1997");
        member.setMonth("09");
        member.setDate("11");
        member.setBirth("19970911");
        member.setEmail("ysy7109@naver.com");
        member.setGender("남성");
        member.setId("tss2222212229");
        member.setName("유선우");
        member.setPassword("12345");
        member.setPhonenum("010-7721-7109");
        memberMapper.join(member);
        log.info("회원 등록 완료 : {}", member);
	}
}
