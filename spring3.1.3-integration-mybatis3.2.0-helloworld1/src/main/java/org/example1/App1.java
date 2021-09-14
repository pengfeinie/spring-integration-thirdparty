package org.example1;

import org.example1.mapper.UserMapper1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App1 {
	
	public static void main(String[] args) {
		//System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
    	ac.register(AppConfig1.class);
    	ac.refresh();
    	UserMapper1 userMapper = (UserMapper1)ac.getBean(UserMapper1.class);
    	System.out.println(userMapper);
	}

}
