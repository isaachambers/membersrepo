package com.memebershiprest.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.memebershiprest.demo.model.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

}
