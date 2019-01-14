package com.syjun.demo.model.common;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class TestModel extends BaseDto{

    @Id
    @GeneratedValue
    private long id;

    private Date checkDate;
}
