package jpashop.jpachan.service;

import jpashop.jpachan.domain.Member;
import jpashop.jpachan.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
  /*  public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }*/
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    // 회원 가입
    @Transactional
    //기본적으로 readOnly = false이 기본이다.
    public Long join(Member member){
        validateDuplicateMember(member); //멤버 중복 검사
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByUserId(member.getUserid());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    //회원 전체 조회

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
