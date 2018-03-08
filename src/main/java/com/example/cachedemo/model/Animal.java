package com.example.cachedemo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author claus
 */
@Data
@Entity
@Table(name = "Animal")
@NoArgsConstructor
@AllArgsConstructor
public class Animal extends BaseEntity {
    
    @Column(name = "name")
    String name;
    
    @OrderColumn(name = "order_index")
    @JoinTable(name = "Animal_KeeperList", joinColumns = {
        @JoinColumn(name = "animal_oid")}, inverseJoinColumns = {
        @JoinColumn(name = "keeperList_oid")})
    @ManyToMany
    List<Keeper> keepers = new ArrayList<>();
    
}
