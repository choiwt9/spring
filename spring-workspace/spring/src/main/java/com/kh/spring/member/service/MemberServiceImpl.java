package com.kh.spring.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor //필드로 선언된 객체(빈)를 생성자 방식으로 주입
@Service    //Component 보다 좀더 구체적인 객체 => Service Bean으로 등록
public class MemberServiceImpl implements MemberService{
   

	/*
     * 필드 주입방식 : 스프링 컨테이너에서 객체를 생성한 후 
     *              @Autowired 사용한 필드에 주입
	@Autowired
	private SqlSessionTemplate sqlSession;
	//SqlSessionTemplate sqlSession = new SqlSessionTemplate();
	*/
	
	 private final SqlSessionTemplate sqlSession;
	 private final MemberDao mDao;
	 	 	 
	 /*@Autowired   //롬복 안쓸 때 
	public MemberServiceImpl() {
		this.sqlSession = sqlSession;
		this.mDao = mDao;
	}
	//=>생성자 주입 방식
	*/
	@Override
	public Member loginMember(Member m) {
		//sqlSession 객체 생성 --> 스프링 컨테이너 생성
		//Dao객체에 sqlSession, 전달된 데이터 전달하면 요청
		return mDao.loginMember(sqlSession, m);
		
		//트랜잭션 처리(필요시)
		//sqlSession 객체 반납 --> 스프링 컨테이너에서 처리
		//결과 반환(return)
		
	}

	@Override
	public int insertMember(Member m) {
		// 기존에 SqlSession 객체 생성
		
		//DAO 객체한테 sqlSession , 데이터 전달 => DB 작업 요청
		int result = mDao.insertMember(sqlSession, m);
		
		//DML(insert) -> 트랜잭션 처리
		//SqlSession 객체 반납
		return result;
	}

	@Override
	public int idCheck(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Member updateMember(Member m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMember(String id, String pwd) {
		// TODO Auto-generated method stub
		return 0;
	}

}
