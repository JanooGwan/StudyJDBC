package StudyJDBC.springjdbc.controller.dto;

import StudyJDBC.springjdbc.domain.Member;

public record MemberResponse(Long id, String name, String email, String password) {
    public static MemberResponse of(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPassword()
        );
    }
}
