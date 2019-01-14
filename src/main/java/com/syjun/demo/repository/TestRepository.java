package com.syjun.demo.repository;

import com.syjun.demo.model.common.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestModel, Long> {

}
