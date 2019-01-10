package com.syjun.demo.model.common;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseDto implements Serializable {

    private Date updateAt;

    private Date createdAt;

    @PreUpdate
    public void preUpdate(){
        this.updateAt = new Date();
    }

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    }

}
