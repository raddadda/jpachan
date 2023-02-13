package jpashop.jpachan.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpashop.jpachan.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RequiredArgsConstructor
public class MemberRepository {

   // @PersistenceContext
    //스프링부의 jpa를 이용하여 Autowired를 할 수 있다.
    @Autowired
    //고칠일이 거의 없으니 final를 해둔다.
    private final EntityManager em;

    public MemberRepository(EntityManager em){
        this.em = em;
    }
    public void save(Member member) {
        //jpa에서 영속성은 엔티티를 영구적을 저장해주는 환경을 의미한다.
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
    public List<Member> findByUserId(String userid){
        return em.createQuery("select m from Member m where m.userid = :userid", Member.class)
                .setParameter("userid",userid)
                .getResultList();
    }


}
