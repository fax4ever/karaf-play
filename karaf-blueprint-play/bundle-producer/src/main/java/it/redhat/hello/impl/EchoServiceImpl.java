package it.redhat.hello.impl;

import org.osgi.service.component.annotations.Component;

import it.redhat.hello.api.EchoService;

@Component
public class EchoServiceImpl implements EchoService {

	public String mirrorMe(String original) {
		// very complex algorithm
		return original;
	}
}
