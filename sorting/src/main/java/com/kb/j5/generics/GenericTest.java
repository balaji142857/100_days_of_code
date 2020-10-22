package com.kb.j5.generics;

public class GenericTest<T> {
	
	public void accept(T arg) { } 
}

class ConcreteChild extends GenericTest<String> {
	//overriding a method which takes in Object arg with another method that takes a string arg - possible because of bridge method  
	@Override
	public void accept(String s) { }
	
}

class NonGenericTest {
	void accept(Object arg) {}
}
class Test2 extends NonGenericTest {
	// adding override here would fail as we are not having a method which has no return value and takes string arg in parent
	// works in the case of generic child class because compiler adds in a bridge method with object as arg type
	//	@Override 
	void accept(String arg) { }
}