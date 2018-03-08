package com.example.cachedemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author claus
 */
@Data
@Entity
@Table(name = "Keeper")
@NoArgsConstructor
@AllArgsConstructor
public class Keeper extends BaseEntity {
    
    @Column(name = "name")
    String name;

}
