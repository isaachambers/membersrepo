package com.memebershiprest.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.memebershiprest.demo.model.Member;
import com.memebershiprest.demo.model.Plan;
import com.memebershiprest.demo.repository.MemberRepository;
import com.memebershiprest.demo.repository.PlanRepository;

@RestController
@RequestMapping("/members")
public class MembersController {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PlanRepository planrepository;

	@PostMapping
	public ResponseEntity<Member> saveMember(@RequestBody Member member) {

		try {
			member = memberRepository.save(member);
			return new ResponseEntity<>(member, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping
	public ResponseEntity<List<Member>> getAllPlans() {
		List<Member> allMembers = new ArrayList<>();
		try {
			Iterable<Member> members = memberRepository.findAll();
			for (Member m : members) {
				allMembers.add(m);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(allMembers, HttpStatus.OK);
	}

	@PostMapping("/attach/{memberId}/{planId}")
	public ResponseEntity<Object> addMemberToPlan(@PathVariable long memberId, @PathVariable long planId) {
		Member m = memberRepository.findById(memberId).orElse(null);
		if (m == null) {
			return new ResponseEntity<>("Member Not Found", HttpStatus.NOT_FOUND);
		} else {
			Plan p = planrepository.findById(planId).orElse(null);
			if (p == null) {
				return new ResponseEntity<>("Plan Not Found", HttpStatus.NOT_FOUND);
			} else {
				m.setPlanId(p.getId());
				m = memberRepository.save(m);
				return new ResponseEntity<>(m, HttpStatus.OK);

			}
		}
	}

}
