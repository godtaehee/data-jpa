package study.datajpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void evanTestEntity() {
        Team team = new Team("Team A");

        Member member = new Member("Member A", 10, team);

        member.changeTeam(team);

        em.persist(member);

        Member findMember = em.find(Member.class, member.getId());

        Assertions.assertEquals(findMember.getTeam(), team);
    }

    @Test
    public void testEntity() {
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");

        em.persist(teamA);
        em.persist(teamB);

        Member memberA = new Member("member A", 10, teamA);
        Member memberB = new Member("member B", 20, teamA);
        Member memberC = new Member("member C", 30, teamB);
        Member memberD = new Member("member D", 40, teamB);

        em.persist(memberA);
        em.persist(memberB);

        em.flush();
        // Cache 해놓은 Query가지고 그냥 DB에 이제 쏴버림
        em.clear();
        // Clear Persistence Context

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        for (Member member : members) {
            System.out.println("member = " + member);
            System.out.println("member.team = " + member.getTeam());
        }
    }
}
