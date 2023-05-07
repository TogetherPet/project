package together.pet.domain.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import together.pet.domain.board.dto.Post;
import together.pet.domain.board.dto.Reply;
import together.pet.domain.facilities.dto.Review;
import together.pet.domain.member.dto.Favorite;
import together.pet.domain.member.dto.Member;
import together.pet.domain.member.dto.myReserve;
import together.pet.domain.member.mapper.MemberMapper;

@Service
public class MemberServicempl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public Member login(String id) {
		Member member = null;
		member = memberMapper.login(id);
		return member;
	}

	@Override
	public void join(Member member) {
		memberMapper.join(member);
	}

	@Override
	public boolean idcheck(String id) {
		return false;
	}

	@Override
	public void update(Member member) {
		memberMapper.update(member);
	}

	@Override
	public void delete(String id, String password) {
		memberMapper.delete(id, password);
	}

	@Override
	public List<Reply> seeComment(String id) {
		return memberMapper.seeComment(id);
	}

	@Override
	public List<Post> seePost(String id) {
		return memberMapper.seePost(id);
	}

	@Override
	public List<Favorite> bookmark(String id) {
		return memberMapper.bookmark(id);
	}

	@Override
	public List<Review> seeReview(String id) {
		return memberMapper.seeReview(id);
	}

	@Override
	public String findId(String name, String email) {
		return memberMapper.findId(name, email);
	}

	@Override
	public String findPassword(String id, String password, String email) {
		return memberMapper.findPassword(id, password, email);
	}

	@Override
	public void updatePw(String id, String password) {
		memberMapper.updatePw(id, password);

	}

	// 예약 목록 전부 (나)
	@Override
	public List<myReserve> findReserveAll(String id) {
		return memberMapper.findReserveAll(id);
	}

	// 예약 수정 (마이페이지로)
	@Override
	public void editReserve(String id, int facNum, String resDate, int resPeoples) {
		memberMapper.editReserve(id, facNum, resDate, resPeoples);
	};

	@Override
	public void deleteReserve(String id, int facNum) {
		memberMapper.deleteReserve(id, facNum);

	}

}
