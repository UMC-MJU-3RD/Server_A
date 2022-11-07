package com.example.umc_tommy.model.entity;

import lombok.Getter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


/**
 * BaseEntity는 모든 Entity의 상위 클래스가 되어 Entity들의 생성일시, 수정일시를 자동으로 관리하는 역할
 * JPA Entity들이 @MappedSupperclass가 선언된 클래스를 상속할 경우 클래스의 필드들도 칼럼으로 인식하도록 한다.
 */

@EntityListeners(AuditingEntityListener.class) // Entity CRUD 작업 전 혹은 작업 후에 특정한 작업을 하기 위한 이벤트 처리를 위한 어노테이션
@MappedSuperclass // 엔티티의 공통 매핑 정보가 필요할 때 주로 사용한다.
@Getter
@Where(clause = "deleted = false") // soft delete 처리를 위한 어노테이션으로, 해당 어노테이션을 사용하면 entity를 조회하는 모든 요청에 default 옵션으로 적용된다.
public class BaseEntity {

    // Entity가 생성되어 저장될 때의 시간이 자동 저장된다.
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // Entity 값을 변경할 떄의 시간이 자동 저장된다.
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private boolean isDeleted = false;

    public void delete(){
        this.isDeleted = true;
    }

    public void restore(){
        this.isDeleted = false;
    }


}
