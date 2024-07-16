package com.ohgiraffers.section05.access.subsection02.property;

import jakarta.persistence.*;

// @Entity(name="member_section05_sub02")
@Access(AccessType.PROPERTY)
/*
* 클래스 레벨에 @Acces(AccessType.PROPERTY)를 선언할 때 @Id 어노테이션이 필드에 있다면 엔티티를 생성하지 못한다.
* */
public class Member {

    @Id
    @Column(name="member_no")
    private int memberNo;

    @Column(name="member_id")
    private String memberId;

    @Column(name="member_pwd")
    private String memberPwd;

    @Column(name="nickname")
    private String nickname;


    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getNickname() {
        System.out.println("🌟🌟🌟🌟 getNickname() 메소드 확인");
        return nickname + "님";
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
