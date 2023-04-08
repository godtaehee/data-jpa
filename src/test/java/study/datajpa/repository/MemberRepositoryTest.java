package study.datajpa.repository;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testMember() {
        Team teamA = new Team("Team A");

        Member member = new Member("MemberA", 10, teamA);
        Member memberB = new Member("MemberB", 10, teamA);
        Member savedMember = memberRepository.save(member);

        Optional<Member> byId = memberRepository.findById(member.getId());

        Member findMember = byId.orElse(memberB);

        Assertions.assertEquals(member.getId(), findMember.getId());
        Assertions.assertEquals(member.getUsername(), findMember.getUsername());
        Assertions.assertEquals(member, findMember);
    }
}
