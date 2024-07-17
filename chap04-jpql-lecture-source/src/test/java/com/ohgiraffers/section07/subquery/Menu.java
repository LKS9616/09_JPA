package com.ohgiraffers.section07.subquery;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="menu_section08")
@Table(name="tbl_menu")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
// @NamedQiery 정적쿼리를 정의하는 데 사용되는 어노테이션
// JPQL을 미리 엔티티 클래스에 정의를 해둠으로써 재사용성을 높일 수 있다.
@NamedQueries({
        @NamedQuery(
                name="section08.namedquery.Menu.findAll", // 쿼리이름
                query= """
                    select m from menu_section08 m
                    """
        )
        })
public class Menu {

    @Id
    @Column(name="menu_code")
    private int menuCode;

    @Column(name="menu_name")
    private String menuName;

    @Column(name="menu_price")
    private int menuPrice;

    @JoinColumn(name="category_code")
    private int categoryCode;

    @Column(name="orderable_status")
    private String orderableStatus;

}
