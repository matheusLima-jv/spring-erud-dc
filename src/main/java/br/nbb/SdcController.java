package br.nbb;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SdcController {
	
	private static final String temp = "Ola, %s!";
	private final AtomicLong count = new AtomicLong();
	
	@RequestMapping("/sts")
	public Sdc sdc(@RequestParam (value = "name", defaultValue = "mundo")String name) {
		return new Sdc(count.incrementAndGet(), String.format(temp, name));
	}
	
	

}
