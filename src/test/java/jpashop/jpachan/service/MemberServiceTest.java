package jpashop.jpachan.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import jakarta.persistence.EntityManager;
import jpashop.jpachan.domain.Member;
import jpashop.jpachan.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;


    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        Member member = new Member();
        //회원 이름
        member.setName("kim");
        //회원 닉네임
        member.setNickname("jim");
        //회원 패스워드
        member.setPassword("asdasd486");
        Long savedId = memberService.join(member);

        //em.flush();
        //영속성 컨텍스트의 변경내용을 DB에 반영하는 것이다.
        assertEquals(member, memberRepository.findOne(savedId));
    }
    @Test
    public void 중복_회원_예외() throws Exception{
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        try{
            memberService.join(member2);
        } catch (IllegalStateException e){
            return;
        }



        fail("예외가 발생해야 한다.");
    }
}
