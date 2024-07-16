package com.ohgiraffers.section06.compositekey.subsection02.IdClassTests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdClassTests {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManger() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll // 테스트 클래스 전체가 진행된 후 한번만 실행되는 메소드 정의
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManger() {
        entityManager.close();
    }

    @DisplayName("아이디 클래스를 사용한 복합키 테이블 매핑 테스트")
    @Test
    public void embeddedIdTest() {

        // given
        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setPhone("010-1234-5678");
        member.setAddress("서울시 삼성동");

        // when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            entityManager.persist(member);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }

        // then
        Member foundmember = entityManager.find(Member.class, new MemberPK(1, "user01"));
        assertEquals(member, foundmember);
    }

}
