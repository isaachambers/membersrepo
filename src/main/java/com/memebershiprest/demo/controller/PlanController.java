package com.memebershiprest.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.memebershiprest.demo.model.Plan;
import com.memebershiprest.demo.repository.PlanRepository;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/plans")
public class PlanController {

	@Value("${environment.name}")
	private String environment;

	@Autowired
	private PlanRepository planrepository;

	@PostMapping
	public ResponseEntity<Plan> savePlan(@RequestBody Plan plan) {

		try {
			if (plan.isLimited() && plan.getEndDate() == null && plan.getStartDate() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				plan = planrepository.save(plan);
				return new ResponseEntity<>(plan, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping
	public List<Plan> getAllPlans() {
		System.out.println(environment);
		List<Plan> allPlans = new ArrayList<>();
		try {
			Iterable<Plan> plans = planrepository.findAll();
			for (Plan plan : plans) {
				allPlans.add(plan);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allPlans;
	}
}
