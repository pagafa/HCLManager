package com.gallegoFalcon.managerNfc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.gallegoFalcon.managerNfc.bean.Location;
import com.gallegoFalcon.managerNfc.bean.Url;
import com.gallegoFalcon.managerNfc.repository.LocationRepository;
import com.gallegoFalcon.managerNfc.repository.UrlRepository;

@RestController
public class OthersController {

	@Autowired
	private UrlRepository urlRepository;

	@Autowired
	private LocationRepository locationRepository;

	@RequestMapping("/u/{code}")
	public RedirectView accessUrl(@PathVariable final String code) {
		final Url url = urlRepository.findByCode(code);
		if (url != null) {
			return new RedirectView(url.getUrl());
		}
		return null;
	}

	@RequestMapping("/l/{code}")
	public RedirectView accessLocation(@PathVariable final String code) {
		final Location location = locationRepository.findByCode(code);
		if (location != null) {
			return new RedirectView(
					"http://maps.google.com/maps?q=" + location.getLatitude() + "," + location.getLongitude());
		}
		return null;
	}

}
