package com.spring.boot.application.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Integer ideaId;
    @Column
    private String nickname;
    @Column
    private String ideaText;

    public Idea() {
    }
    public Idea(Integer ideaId){
        this.ideaId=ideaId;
    }

    public Idea(String nickname,String ideaText){
        this.nickname=nickname;
        this.ideaText=ideaText;
    }
    public Integer getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Integer ideaId) {
        this.ideaId = ideaId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdeaText() {
        return ideaText;
    }

    public void setIdeaText(String ideaText) {
        this.ideaText = ideaText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Idea idea = (Idea) o;
        return Objects.equals(ideaId, idea.ideaId) &&
                Objects.equals(nickname, idea.nickname) &&
                Objects.equals(ideaText, idea.ideaText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ideaId, nickname, ideaText);
    }

    @Override
    public String toString() {
        return "Idea{" +
                "ideaId=" + ideaId +
                ", nickname='" + nickname + '\'' +
                ", ideaText='" + ideaText + '\'' +
                '}';
    }
}
