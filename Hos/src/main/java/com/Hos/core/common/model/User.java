package com.Hos.core.common.model;

import com.Hos.core.common.util.Constants;
import com.Hos.core.common.util.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = Constants.TABLE_USER)
public class User  implements Serializable  {
    @Id
    @Column(name = Constants.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Constants.FIRST_NAME)
    private String firstName;

    @Column(name = Constants.USERNAME)
    private String username;

    @Column(name = Constants.PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = Constants.OTP)
    private String otp;

//    @ColumnTransformer(forColumn = Constants.PASSWORD, read = "public.pgp_sym_decrypt(password::bytea, " + "'"
//            + Constants.HOS + "'"
//            + ")", write = "public.pgp_sym_encrypt(?, " + "'" + Constants.HOS + "'" + ")")
//    @Column(name = Constants.PASSWORD, columnDefinition = "bytea", nullable = false)
    @Column(name = Constants.PASSWORD)
    private String password;

    @Column(name = Constants.CREATED_BY, updatable = false, nullable = false)
    private Long createdBy;

    @Column(name = Constants.UPDATED_BY, nullable = true)
    private Long updatedBy ;

    @Column(name = Constants.CREATED_AT, columnDefinition = Constants.TIMESTAMP, nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createdAt;

    @Column(name = Constants.UPDATED_AT, columnDefinition = Constants.TIMESTAMP)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updatedAt;

    @Column(name = Constants.IS_ACTIVE)
    private boolean isActive = true;

    @Column(name = Constants.IS_DELETED)
    private boolean isDeleted = false;

    @Column(name = Constants.IS_COMMUNITY_USER)
    private boolean isCommunityUser = false;

    @ManyToOne
    @JoinColumn(name = Constants.CITY_ID)
    private City city;
}

