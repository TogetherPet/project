package together.pet.domain.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import together.pet.domain.board.dto.Post;
import together.pet.domain.board.dto.Reply;
import together.pet.domain.member.dto.Favorite;
import together.pet.domain.member.dto.Member;
import together.pet.domain.member.mapper.MemberMapper;

@Service
public class MemberServicempl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void join(Member member) {
		
		memberMapper.join(member);
		
	}

	@Override
	public void login(String id, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reply> seeComment(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> seePost(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Favorite> bookmark(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
