package study.datajpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

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

    @Test
    public void findByUsernameAndAgeGreaterThan() {
        Member memberA = new Member("MemberA", 20);
        Member memberB = new Member("MemberB", 30);

        Member savedMemberA = memberRepository.save(memberA);
        Member savedMemberB = memberRepository.save(memberB);

        List<Member> members = memberRepository.findByUsernameAndAgeGreaterThan("MemberB", 27);

        Assertions.assertEquals(members.size(), 1);
    }

    @Test
    public void namedQuery() {
        Member member1 = new Member("Member 1", 20);

        memberRepository.save(member1);

        Member foundMember = this.memberRepository.findByUsername("Member 1");

        assertEquals(foundMember.getUsername(), "Member 1");
    }

    @Test
    public void testFindUserByUsernameAndAge() {
        Member member1 = new Member("Member 1", 20);

        memberRepository.save(member1);

        Member userByUsernameAndAge = memberRepository.findUserByUsernameAndAge("Member 1", 20);

        assertEquals(userByUsernameAndAge.getUsername(), "Member 1");
        assertEquals(userByUsernameAndAge.getAge(), 20);
    }

    @Test
    public void testFindUserName() {
        Member member1 = new Member("Member 1", 20);
        Member member2 = new Member("Member 2", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<String> userName = memberRepository.findUserName();
        System.out.println("userName");
        System.out.println(userName);

        for (String username : userName) {
            System.out.println(username);
        }
    }

    @Test
    public void testFindMemberDto() {
        Team team1 = new Team("Team 1");
        Team team2 = new Team("Team 2");

        teamRepository.save(team1);
        teamRepository.save(team2);

        Member member1 = new Member("Member 1", 20, team1);
        Member member2 = new Member("Member 2", 20, team2);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<MemberDto> memberDtos = memberRepository.findMemberDto();

        for (MemberDto memberDto : memberDtos) {
            System.out.println(memberDto);
        }
    }
}
