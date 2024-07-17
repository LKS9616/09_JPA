package com.ohgiraffers.section09.nativequery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JpqlNativeQueryTests {
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

    /*
    * Native Query
    * - SQL 쿼리를 그대로 사용하는 것을 의미한다.
    * - ORM의 기능을 이용하면서 SQL 쿼리도 활용할 수 있어서 더욱 강력한 데이터베이스 접근 가능
    * - 복잡한 쿼리, 특정 데이터베이스에서만 사용가능한 기능을 사용할 때 사용한다.
    *
    * 네이티브 쿼리 API 종류 3가지
    * 1. 결과 타입 정의 (엔티티클래스만 지정 가능)
    *    public Query createNativeQuery(String sqlString, Class resultClass)
    * 2. 결과 타입을 정의할 수 없을 때
    *    public Query createNativeQuery(String sqlString, String resultSetMapping)
    * */

    @Test
    @DisplayName("결과타입을 지정해서 Native Query 사용하기")
    void test1() {

        // given
        Long menuCode = 15L;

        // when
        String jpql = """
                select
                *
                from
                tbl_menu
                where
                menu_name = ?
                """;
        Query query = entityManager.createNativeQuery(jpql, Menu.class).setParameter(1, menuCode);

        Menu menu = (Menu) query.getSingleResult();
        System.out.println("menu = " + menu);
        
        Menu menu2 = entityManager.find(Menu.class, menuCode);
        System.out.println("menu2 = " + menu2);

        // then

        assertNotNull(menu);
    }

    @Test
    @DisplayName("결과 타입을 지정하지 않고 Native Query 사용하기")
    void test2() {

        // given
        Long menuCode = 15L;

        // when
        String sql = """
                     select
                     menu_code,
                     menu_name,
                     (select category_name from tbl_category where category_code = m.category_code)
                     from tbl_menu m
                     where menu_code = ?
                     """;
        Query query = entityManager.createNativeQuery(sql).setParameter(1, menuCode);
        Object[] row = (Object[]) query.getSingleResult();
        for(Object o : row) {
            System.out.println(o);
        }

        // then
        // row의 크기가 3이면 pass
        assertThat(row).hasSize(3);
    }

    @Test
    @DisplayName("@NamedNativeQuery 사용하기 - 엔티티")
    void test3() {
        // given
        Long menuCode = 15L;
        // when
        Query query = entityManager.createNamedQuery("section09.Menu.findByMenuCode", Menu.class).setParameter(1, menuCode);
        Menu menu = (Menu) query.getSingleResult();

        System.out.println("menu = " + menu);
        // then
        assertNotNull(menu);
    }
}
