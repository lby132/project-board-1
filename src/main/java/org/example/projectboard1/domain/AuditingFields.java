package org.example.projectboard1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditingFields {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @CreatedBy
    @Column(nullable = false, updatable = false, length = 100)
    protected String createdBy;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    protected LocalDateTime modifiedAt;

    @LastModifiedDate
    @Column(nullable = false, length = 100)
    protected String modifiedBy;
}
