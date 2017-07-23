package com.gallegoFalcon.managerNfc.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gallegoFalcon.managerNfc.bean.Location;
import com.gallegoFalcon.managerNfc.repository.LocationRepository;

@RestController
@RequestMapping(path = "/location")
public class LocationController {
	@Autowired
	private LocationRepository locationRepository;

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void get(@RequestBody @NotNull final Location location) {
		final Location old = locationRepository.findOne(location.getId());
		if (old != null) {
			location.setCode(old.getCode());
		} else {
			location.generateCode();
		}
		locationRepository.save(location);
	}

	@RequestMapping("/remove")
	public void list(@RequestParam final long id) {
		locationRepository.delete(id);
	}

	@RequestMapping("/list")
	public Iterable<Location> list() {
		return locationRepository.findAll();
	}

}
