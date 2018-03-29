package com.example.demo.controll;

public class Value<T> {
	public static<T> T defaultValue() {
		return (T) "值为空";
	}
	
	public T getOrDefault(T value,T defaultValue) {
		return (value!="")?value:defaultValue;
	}
	
	public static void main(String[] args) {
		final Value<String> value=new Value<>();
		System.out.println(value.getOrDefault("1232", Value.defaultValue()));
	}

}
