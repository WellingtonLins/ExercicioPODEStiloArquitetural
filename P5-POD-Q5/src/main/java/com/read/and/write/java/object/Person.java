package com.read.and.write.java.object;

/**
 * @Date 29/07/2017 @Time 20:30:04
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
import java.io.Serializable;


public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	private String gender;

	Person() {
	};

	Person(String name, int age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Name:" + name + "\nAge: " + age + "\nGender: " + gender;
	}
}