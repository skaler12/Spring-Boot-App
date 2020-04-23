package com.spring.boot.application.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "musicStore")
public class MusicStore implements Serializable {


    private static final long serialVersionUID = 4541387723446944108L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long storeId;
    @Column
    private String storeName;
    @Column
    private String street;
    @Column
    private int streetNumber;
    @Column
    private String equipmentType;
    @Column
    private String linkGoogle;
    @OneToMany(mappedBy = "musicStore", cascade = CascadeType.ALL)
    private List<Opinion> opinionList;

    public MusicStore() {
    }
    public MusicStore(Long storeId,String street,int streetNumber,String equipmentType,String linkGoogle){
        this.storeId=storeId;
        this.street=street;
        this.streetNumber=streetNumber;
        this.equipmentType=equipmentType;
        this.linkGoogle=linkGoogle;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getLinkGoogle() {
        return linkGoogle;
    }

    public void setLinkGoogle(String linkGoogle) {
        this.linkGoogle = linkGoogle;
    }

    public List<Opinion> getOpinionList() {
        return opinionList;
    }

    public void setOpinionList(List<Opinion> opinionList) {
        this.opinionList = opinionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicStore that = (MusicStore) o;
        return streetNumber == that.streetNumber &&
                Objects.equals(storeId, that.storeId) &&
                Objects.equals(storeName, that.storeName) &&
                Objects.equals(street, that.street) &&
                Objects.equals(equipmentType, that.equipmentType) &&
                Objects.equals(linkGoogle, that.linkGoogle) &&
                Objects.equals(opinionList, that.opinionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, storeName, street, streetNumber, equipmentType, linkGoogle, opinionList);
    }

    @Override
    public String toString() {
        return "MusicStore{" +
                "storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber=" + streetNumber +
                ", equipmentType='" + equipmentType + '\'' +
                ", linkGoogle='" + linkGoogle + '\'' +
                ", opinionList=" + opinionList +
                '}';
    }
}
