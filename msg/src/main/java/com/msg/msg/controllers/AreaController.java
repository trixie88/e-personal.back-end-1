package com.msg.msg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msg.msg.entities.Area;
import com.msg.msg.repositories.AreaRepository;

@RestController
@RequestMapping("/area")
@CrossOrigin(origins = "*") // because this web service is only used locally i have crossOrigin all (*) if
							// it was to be deployed this must change
public class AreaController {

	@Autowired
	public AreaRepository areaRepository;

	@GetMapping("/all")
	public List<Area> getAllAreas() {
		return areaRepository.findAll();
	}
}
