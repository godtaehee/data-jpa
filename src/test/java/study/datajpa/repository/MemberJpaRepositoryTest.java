package study.datajpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

// To inject spring bean while test
@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void testMember() {
        Member member = new Member("memberA", 10);
        System.out.println("----");
        System.out.println(member.getId());
        System.out.println("----");
        Member savedMember = memberJpaRepository.save(member);

        Member foundMember = memberJpaRepository.find(savedMember.getId());

        assertEquals(foundMember.getId(), member.getId());
        assertEquals(foundMember.getUsername(), member.getUsername());
        assertEquals(foundMember, member);
    }
}
