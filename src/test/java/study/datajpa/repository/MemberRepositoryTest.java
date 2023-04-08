package study.datajpa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    public void testMember() {
        Member member = new Member("MemberA");
        Member memberB = new Member("MemberB");
        Member savedMember = memberRepository.save(member);

        Optional<Member> byId = memberRepository.findById(member.getId());

        Member findMember = byId.orElse(memberB);

        Assertions.assertEquals(member.getId(), findMember.getId());
        Assertions.assertEquals(member.getUsername(), findMember.getUsername());
        Assertions.assertEquals(member, findMember);



    }





}