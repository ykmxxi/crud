package com.example.crud.build;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class H2DatabaseConnectionTest {

    @Autowired private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("insert into members(name, age) values (?, ?);", "kim", 20);
    }

    @Test
    void insert() {
        Member findMember = jdbcTemplate.queryForObject("select * from members;",
                (rs, rowNum) -> {
                    Member member = new Member(rs.getString("name"), rs.getInt("age"));
                    member.setId(rs.getLong("id"));
                    return member;
                });

        assertThat(findMember).isNotNull();
        assertThat(findMember.id).isEqualTo(1L);
        assertThat(findMember.name).isEqualTo("kim");
        assertThat(findMember.age).isEqualTo(20);
    }

    @Entity
    @Table(name = "members")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    static class Member {

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Setter
        private Long id;

        private String name;
        private int age;

        public Member(final String name, final int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Member{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

    }

}
