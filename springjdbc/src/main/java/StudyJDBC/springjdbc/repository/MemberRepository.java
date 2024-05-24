package StudyJDBC.springjdbc.repository;

import java.util.List;
import StudyJDBC.springjdbc.domain.Member;

public interface MemberRepository {
    List<Member> findAll();
    Member findById(Long id);
    void insert(Member member);
}
