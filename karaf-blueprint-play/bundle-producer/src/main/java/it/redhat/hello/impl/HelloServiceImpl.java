package it.redhat.hello.impl;

import org.osgi.service.component.annotations.Component;

import it.redhat.hello.api.HelloService;

@Component
public class HelloServiceImpl implements HelloService {

	public String ciao(String name) {
		return "Hello " + name + ". " + System.currentTimeMillis() + " millis have gone since 1970";
	}
}
