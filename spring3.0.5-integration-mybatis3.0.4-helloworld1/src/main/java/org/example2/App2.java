package org.example2;

import org.example2.mapper.UserMapper2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App2 {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
    	ac.register(AppConfig2.class);
    	ac.refresh();
    	UserMapper2 userMapper = (UserMapper2)ac.getBean(UserMapper2.class);
    	System.out.println(userMapper);
	}

}
