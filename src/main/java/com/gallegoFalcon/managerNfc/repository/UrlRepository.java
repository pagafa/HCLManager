package com.gallegoFalcon.managerNfc.repository;

import org.springframework.data.repository.CrudRepository;

import com.gallegoFalcon.managerNfc.bean.Url;

public interface UrlRepository extends CrudRepository<Url, Long> {

	public Url findByCode(String code);

}
