package com.Hos.core.common.model;

import com.Hos.core.common.util.Constants;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = Constants.REQUEST)
public class Request  implements Serializable {
    private static final long serialVersionUID = 4174505913611242103L;

    @Id
    @Column(name = Constants.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = Constants.NAME)
    private String entity;

}
