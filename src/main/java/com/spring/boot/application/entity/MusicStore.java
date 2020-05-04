package com.spring.boot.application.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "musicStore")
public class MusicStore {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Integer storeId;
    @Column
    private String storeName;
    @Column
    private String street;
    @Column
    private Integer streetNumber;
    @Column
    private String equipmentType;
    @Column
    private String linkGoogle;

    public MusicStore() {
    }
    public MusicStore(Integer storeId) {
        this.storeId=storeId;
    }
    public MusicStore(Integer storeId,String street,Integer streetNumber,String equipmentType,String linkGoogle){
        this.storeId=storeId;
        this.street=street;
        this.streetNumber=streetNumber;
        this.equipmentType=equipmentType;
        this.linkGoogle=linkGoogle;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
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

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicStore that = (MusicStore) o;
        return Objects.equals(storeId, that.storeId) &&
                Objects.equals(storeName, that.storeName) &&
                Objects.equals(street, that.street) &&
                Objects.equals(streetNumber, that.streetNumber) &&
                Objects.equals(equipmentType, that.equipmentType) &&
                Objects.equals(linkGoogle, that.linkGoogle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, storeName, street, streetNumber, equipmentType, linkGoogle);
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
                '}';
    }
}