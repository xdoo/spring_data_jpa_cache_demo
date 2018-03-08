package com.example.cachedemo.model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author claus
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements Cloneable, Serializable {
    
    @Column(name = "OID")
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type="uuid-char")
    UUID oid;
    
}
