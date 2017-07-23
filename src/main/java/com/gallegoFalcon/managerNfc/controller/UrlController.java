package com.gallegoFalcon.managerNfc.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gallegoFalcon.managerNfc.bean.Url;
import com.gallegoFalcon.managerNfc.repository.UrlRepository;

@RestController
@RequestMapping(path = "/url")
public class UrlController {
	@Autowired
	private UrlRepository urlRepository;

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void get(@RequestBody @NotNull final Url url) {
		final Url old = urlRepository.findOne(url.getId());
		if (old != null) {
			url.setCode(old.getCode());
		} else {
			url.generateCode();
		}
		urlRepository.save(url);
	}

	@RequestMapping("/remove")
	public void list(@RequestParam final long id) {
		urlRepository.delete(id);
	}

	@RequestMapping("/list")
	public Iterable<Url> list() {
		return urlRepository.findAll();
	}

}
