package com.yeoljeong.autovote.database

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@MappedSuperclass
class BaseEntity {

    @CreationTimestamp
    @Column(updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.MIN

    @UpdateTimestamp
    @Column(insertable = false)
    val updatedAt: LocalDateTime = LocalDateTime.MIN

}