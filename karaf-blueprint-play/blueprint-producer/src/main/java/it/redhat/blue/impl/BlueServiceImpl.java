package it.redhat.blue.impl;

import it.redhat.blue.api.BlueService;

public class BlueServiceImpl implements BlueService {

	public Integer addOneTo(Integer base) {
		return ++base;
	}
}
