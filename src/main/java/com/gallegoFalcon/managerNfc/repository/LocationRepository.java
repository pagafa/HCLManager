package com.gallegoFalcon.managerNfc.repository;

import org.springframework.data.repository.CrudRepository;

import com.gallegoFalcon.managerNfc.bean.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {
	public Location findByCode(String code);
}
