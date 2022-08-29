package me.minikuma.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {
    // TODO: Time Entity 추상 클래스 작성 필요
    @CreatedBy
    private LocalDateTime createdAt;

    @LastModifiedBy
    private LocalDateTime modifiedAt;
}