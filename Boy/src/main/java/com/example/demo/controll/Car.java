package com.example.demo.controll;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 学习java8方法的引用
 * @author Administrator
 *
 */
public class Car {
	public static Car create(final Supplier<Car> supperlier) {
		return supperlier.get();
	}
	
	public static void collide(final Car car) {
		System.out.println("Collided:"+car.toString());
	}
	
	public void follow(final Car another) {
		System.out.println("Following the:"+another.toString());
	}
	
	public void repair() {
		System.out.println("Repaired:"+this.toString());
	}
	
	public static void main(String[] args) {
		final Car car=Car.create(Car::new);  //生成一个对象
		final List<Car> cars=Arrays.asList(car);
		cars.forEach(Car::collide);
		cars.forEach(Car::repair);
		final Car police=Car.create(Car::new);
		cars.forEach(police::follow);
	}
}
