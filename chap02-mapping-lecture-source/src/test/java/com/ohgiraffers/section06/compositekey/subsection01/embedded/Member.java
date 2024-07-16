package com.ohgiraffers.section06.compositekey.subsection01.embedded;

import jakarta.persistence.*;
import lombok.*;

//@Entity(name="member_section06_sub01")
@Table(name="tbl_member_section06_sub01")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {

    @EmbeddedId
    private MemberPK memberPk;

    @Column(name="phone")
    private String phone;

    @Column(name="address")
    private String address;

}
