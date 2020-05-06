package com.spring.boot.application.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Opinion  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer opinionId;

    @Column
    private String text;

    @Column
    private String storeName;

    @Column
    private int rating;


    public Opinion() {
    }
    public Opinion(Integer opinionId) {
        this.opinionId=opinionId;
    }
    public Opinion(String text, String storeName, Integer rating){
        this.text=text;
        this.storeName=storeName;
        this.rating=rating;
    }

    public Integer getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(Integer opinionId) {
        this.opinionId = opinionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opinion opinion = (Opinion) o;
        return rating == opinion.rating &&
                Objects.equals(opinionId, opinion.opinionId) &&
                Objects.equals(text, opinion.text) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(opinionId, text, rating);
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "opinionId=" + opinionId +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }
}