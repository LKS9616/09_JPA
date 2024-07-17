package com.ohgiraffers.section08.namedquery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JpqlNamedQueryTests {
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

    @Test
    @DisplayName("@NamedQuery - findAll")
    void test1() {

        // given
        // when
        List<Menu> menuList = entityManager.createNamedQuery("section08.namedquery.Menu.findAll", Menu.class).getResultList();
        // then
        assertThat(menuList).isNotNull();
//        assertNotnull(menuList);
        menuList.forEach(System.out::println);
    }

    // findByMenuName -> 메뉴 이름으로 찾아오는 JPQL
    // 메뉴 이름을 파라미터로 받고, 일치하는 메뉴객체를 반환하는 JPQL

    @Test
    @DisplayName("@NamedQuery - findMenuByName")
    void test2() {

        // given
        String menuName = "홍어마카롱";
        // when
        List<Menu> menuList = entityManager.createNamedQuery("section08.namedquery.Menu.findByMenuName", Menu.class).setParameter("menuName", menuName).getResultList();
        // then
        assertThat(menuList).isNotNull();
//        assertNotnull(menuList);
        menuList.forEach(System.out::println);
    }


    @Test
    @DisplayName("@NamedQuery - findMenuByNameConcat")
    void test3() {

        // given
        String menuName = "밥";
        // when
        List<Menu> menuList = entityManager.createNamedQuery("section08.namedquery.Menu.findByMenuNameConcat", Menu.class).setParameter("menuName", menuName).getResultList();
        // then
        assertThat(menuList).isNotNull();
//        assertNotnull(menuList);
        menuList.forEach(System.out::println);
    }

}
