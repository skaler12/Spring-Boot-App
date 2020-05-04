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
    private int rating;

    // @ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "id")
    //private User user;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "storeId")
    //private MusicStore musicStore;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "opinion_store",
            joinColumns = @JoinColumn(name = "opinion_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id"))
    private Set<MusicStore> musicStores;


    public Opinion() {
    }
    public Opinion(Integer opinionId) {
        this.opinionId=opinionId;
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

  /*  public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
*/
   /* public MusicStore getMusicStore() {
        return musicStore;
    }
    public void setMusicStore(MusicStore musicStore) {
        this.musicStore = musicStore;
    }
*/

    public Set<MusicStore> getMusicStores() {
        return musicStores;
    }

    public void setMusicStores(Set<MusicStore> musicStores) {
        this.musicStores = musicStores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opinion opinion = (Opinion) o;
        return rating == opinion.rating &&
                Objects.equals(opinionId, opinion.opinionId) &&
                Objects.equals(text, opinion.text) &&
                Objects.equals(musicStores, opinion.musicStores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opinionId, text, rating, musicStores);
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "opinionId=" + opinionId +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                ", musicStores=" + musicStores +
                '}';
    }
}