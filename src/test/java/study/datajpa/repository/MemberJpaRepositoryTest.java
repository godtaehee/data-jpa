package study.datajpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

// To inject spring bean while test
@SpringBootTest
@Transactional
@Rollback(value = false)
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

    @Test
    public void basicCRUD() {
        // C
        Member member1 = new Member("Member 1");
        Member member2 = new Member("Member 2");

        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        Member findMember1 = memberJpaRepository.findById(member1.getId()).orElseThrow();
        Member findMember2 = memberJpaRepository.findById(member2.getId()).orElseThrow();

        assertEquals(member1, findMember1);
        assertEquals(member2, findMember2);
        // R
        List<Member> allMember = memberJpaRepository.findAll();
        long count = memberJpaRepository.count();

        assertEquals(allMember.size(), 2);
        assertEquals(count, 2);

        // U
        Member newMember1 = new Member(findMember1.getId(), "Updated Member", member1.getAge(), member1.getTeam());

        System.out.println("Before " + member1);
        memberJpaRepository.merge(newMember1);

        // Error
        // memberJpaRepository.save(newMember1);

        Member afterUpdatedMember = memberJpaRepository.find(member1.getId());
        System.out.println("After " + member1);

        assertEquals(afterUpdatedMember.getUsername(), "Updated Member");
        // D
        //        memberJpaRepository.delete(member1);
        //        memberJpaRepository.delete(member2);

        //        List<Member> allMemberAfterDeleted = memberJpaRepository.findAll();
        //        long countAfterDelete = memberJpaRepository.count();

        //        assertEquals(allMemberAfterDeleted.size(), 0);
        //        assertEquals(countAfterDelete, 0);
    }

    @Test
    public void basicCRUDMerge() {
        // Create
        Member member1 = new Member("Member 1");
        Member member2 = new Member("Member 2");

        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        Member findMember1 = memberJpaRepository.findById(member1.getId()).orElseThrow();
        Member findMember2 = memberJpaRepository.findById(member2.getId()).orElseThrow();

        assertEquals(member1, findMember1);
        assertEquals(member2, findMember2);

        // Update
        Member newMember1 = new Member(findMember1.getId(), "Updated Member", member1.getAge(), member1.getTeam());

        System.out.println("Before " + member1);
        memberJpaRepository.merge(newMember1);

        // Error
        // memberJpaRepository.save(newMember1);

        Member afterUpdatedMember = memberJpaRepository.find(member1.getId());
        System.out.println("After " + member1);

        assertEquals(afterUpdatedMember.getUsername(), "Updated Member");
    }

    @Test
    public void basicCRUDError() {
        // Create
        Member member1 = new Member("Member 1");
        Member member2 = new Member("Member 2");

        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        Member findMember1 = memberJpaRepository.findById(member1.getId()).orElseThrow();
        Member findMember2 = memberJpaRepository.findById(member2.getId()).orElseThrow();

        assertEquals(member1, findMember1);
        assertEquals(member2, findMember2);

        // Update
        Member newMember1 = new Member(findMember1.getId(), "Updated Member", member1.getAge(), member1.getTeam());

        //        System.out.println("Before " + member1);
        //        memberJpaRepository.merge(newMember1);

        //         Error
        memberJpaRepository.save(newMember1);

        Member afterUpdatedMember = memberJpaRepository.find(member1.getId());
        System.out.println("After " + member1);

        assertEquals(afterUpdatedMember.getUsername(), "Updated Member");
    }

    @Test
    public void findByUserNameAndAgeGreaterThen() {
        Member member1 = new Member("Member 1", 20);
        Member member2 = new Member("Member 2", 30);

        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        List<Member> byUserNameAndAgeGreaterThen = memberJpaRepository.findByUserNameAndAgeGreaterThan("Member 2", 25);

        assertEquals(byUserNameAndAgeGreaterThen.size(), 1);
    }
}
