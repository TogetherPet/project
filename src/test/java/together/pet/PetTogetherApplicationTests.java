package together.pet;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import oracle.net.aso.b;
import oracle.net.aso.p;
import together.pet.PetTogetherApplication;
import together.pet.domain.board.dto.Post;
import together.pet.domain.board.dto.PostSearch;
import together.pet.domain.board.dto.Reply;
import together.pet.domain.board.dto.Report;
import together.pet.domain.board.mapper.BoardMapper;
import together.pet.domain.facilities.dto.Facilities;
import together.pet.domain.facilities.mapper.FacilitiesMapper;
import together.pet.domain.member.dto.Member;
import together.pet.domain.member.mapper.MemberMapper;
import together.pet.web.common.Pagination;
import together.pet.web.common.RequestParams;

@SpringBootTest
@ContextConfiguration(classes = PetTogetherApplication.class)
@Slf4j
class PetTogetherApplicationTests {
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	BoardMapper boardMapper;
	
	@Autowired
	FacilitiesMapper facilitiesMapper;
	
	@Test
	@Disabled
	void contextLoads() {
		
	}
	@Test
	@Disabled
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
	
	//작동 확인
	@Test
	@Disabled
	void writeReply() {
		Reply reply = new Reply();
		reply.setCommentContent("추가");
		reply.setCommentDate("2023/04/05");
		reply.setId("tjsdn7109");
		reply.setPostNum(2);
		boardMapper.writeReply(reply);
	}
	
	@Test
	@Disabled
	void getReplyAll() {
		List<Reply> list = boardMapper.getReplyAll(4);
		System.out.println(list.size());
		for (Reply reply : list) {
			System.out.println(reply);
		}
	}
	
//	@Test
//	void getPost() {
//		List<Post> list = boardMapper.getPostAll();
//		for (Post post : list) {
//			System.out.println(post);
//		}
//	}
	
	@Test
	@Disabled
	void deletePost() {
//		boardMapper.deletePost(2);
		boardMapper.deleteReply(23);
	}
	
	@Test
	@Disabled
//	@Transactional
	void addViews() {
		boardMapper.addViews(25);
	}
	@Test
	@Disabled
//	@Transactional
	void updatePost() {
		Post post = new Post();
		post.setPostNum(25);
		post.setId("ysw7109");
		post.setPostContent("ㅎㅇㅎㅇ");
		post.setPostTitle("수정함");
		post.setPostType("자유게시판");
		boardMapper.updatePost(post);
	}
	
	@Test
	@Disabled
	void Search() {
		PostSearch postSearch = new PostSearch();
		postSearch.setDay("kakao");
		postSearch.setMonth("12");
		postSearch.setType("id");
		postSearch.setSearch("ysw7109");
		
//		List<Post> list = boardMapper.searchPost(postSearch);
		
//		for (Post post : list) {
//			log.info("기존 검색 결과 : {}", post);
//		}
	}
	
	@Test
	@Disabled
	@Transactional
	void findPostAll2() {
		RequestParams params = new RequestParams(3, 5,3,null);
		log.info("입력되는 데이터 params : {}", params);
		List<Post> list = boardMapper.getPostAll(params);
		for (Post post : list) {
			log.info("결과 : {}", post);
		}
	}
	
	@Test
	@Disabled
	void searchPost2() {
		RequestParams params = new RequestParams(1, 16, 5,null);
		params.setDay("alllaaaal");
		params.setType("postType");
		params.setSearch("자유게시판");
		List<Post> list = boardMapper.searchPost(params);
			log.info("일반 검색 결과 사이즈 : {}", list.size());
		for (Post post : list) {
				log.info(" 결과 : {}", post);
		}
	}
	
	@Test
	@Disabled
	void searchCount() {
		RequestParams params = new RequestParams(1, 16, 5,null);
		params.setDay("alllaaaal");
		params.setType("postType");
		params.setSearch("자유게시판");
		int selectNum = boardMapper.getPostCount(params);
		log.info("받은 열 개수 : {}", selectNum);
		Pagination pagination = new Pagination(params, selectNum);
		log.info("페이지 개수 : {}", pagination);
		List<Post> list = boardMapper.searchPost(params);
		
	}
	
	@Test
	@Disabled
	void updateReply() {
		Reply reply = new Reply();
		reply.setCommentContent("수정할게요");
		reply.setCommentNum(86);
		
		boardMapper.updateReply(reply);
	}
	
	@Test
	@Disabled
	void report() {
		Report report = new Report();
//		report.setPostNum(50);
		report.setCommentNum(86);
		report.setReportId("ysw7109");
		report.setReportReason("두번째 신고");
		
		boardMapper.report(report);
	}	
	
	@Test
	@Disabled
	void getreport() {
		List<Report> reply =  boardMapper.getReplyReport();
		for (Report report : reply) {
			log.info("댓글신고 검색 결과 : {}", report);
		}
		
		List<Report> post = boardMapper.getPostReport();
		for (Report report : post) {
			log.info("게시글신고 검색 결과 : {}", report);
		}
		
//		boardMapper.deleteReport(1);
		
	}
	
	@Test
	@Disabled
	void getPost() {
		List<Post> post = boardMapper.recentPost();
		for (Post post2 : post) {
			log.info("최신글 5개 : {}",post2);
		}
		
		post = boardMapper.popularityPost();
		for (Post post2 : post) {
			log.info("인기글 5개 : {}",post2);
		}
	}
	
	@Test
	@Disabled
	void fac() {
		List<Facilities> list = facilitiesMapper.hospitalSlide();
		for (Facilities facilities : list) {
			log.info("인기글 4개 : {}",facilities);
		}
	}
	
}
