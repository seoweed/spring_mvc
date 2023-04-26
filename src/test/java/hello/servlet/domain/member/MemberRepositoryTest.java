package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    public void save() throws Exception {
        // given
        Member member = new Member("hello", 20);

        // when
        Member saveMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(saveMember.getId());
        // then
        assertThat(findMember).isEqualTo(saveMember);
        assertThat(saveMember.getUsername()).isEqualTo("hello");
        assertThat(saveMember.getAge()).isEqualTo(20);
    }

    @Test
    public void findAll() throws Exception {
        // given
        Member member1 = new Member("hello", 20);
        Member member2 = new Member("hello1", 21);

        memberRepository.save(member1);
        memberRepository.save(member2);
        // when
        List<Member> result = memberRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }

}