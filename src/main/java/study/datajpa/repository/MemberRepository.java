package study.datajpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query(name = "Member.findByUsername") // << 이거 없어도 가능함. 가능한 이유는 클래스이름.메소드 이름으로 먼저 찾기때문, 만약에 그걸로 못찾음녀 쿼리메소드로 함
    Member findByUsername(@Param("username") String username);

    @Query("select m from Member m where m.username = :username AND m.age = :age")
    Member findUserByUsernameAndAge(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member as m")
    List<String> findUserName();

    @Query("select  new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();
}
