package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import static org.junit.jupiter.api.Assertions.*;

// To inject spring bean while test
@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired MemberJpaRepository memberJpaRepository;

    @Test
    public void testMember() {
        Member member = new Member("memberA");
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