package com.spring.boot.application.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Opinion")
public class Opinion implements Serializable {

    private static final long serialVersionUID = 9137208142471420520L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long opinionId;

    @Column(name = "text")
    private String text;

    @Column
    private int rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "storeId")
    private MusicStore musicStore;

    public Opinion() {
    }

    public Opinion(Long opinionId, String text, int rating) {
        this.opinionId = opinionId;
        this.text = text;
        this.rating = rating;
    }

    public Long getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(Long opinionId) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MusicStore getMusicStore() {
        return musicStore;
    }

    public void setMusicStore(MusicStore musicStore) {
        this.musicStore = musicStore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opinion opinion = (Opinion) o;
        return rating == opinion.rating &&
                Objects.equals(opinionId, opinion.opinionId) &&
                Objects.equals(text, opinion.text) &&
                Objects.equals(user, opinion.user) &&
                Objects.equals(musicStore, opinion.musicStore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opinionId, text, rating, user, musicStore);
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "opinionId=" + opinionId +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                ", user=" + user +
                ", musicStore=" + musicStore +
                '}';
    }
}
