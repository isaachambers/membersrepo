package com.memebershiprest.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.memebershiprest.demo.model.Plan;

@Repository
public interface PlanRepository extends CrudRepository<Plan, Long> {

}
