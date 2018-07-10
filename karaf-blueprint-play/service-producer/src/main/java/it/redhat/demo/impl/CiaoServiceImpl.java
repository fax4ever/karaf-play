package it.redhat.demo.impl;

import it.redhat.demo.api.CiaoService;

public class CiaoServiceImpl implements CiaoService {

	public String ciao(String name) {
		return "Ciao " + name + ". " + System.currentTimeMillis() + " millis have gone since 1970";
	}
}
