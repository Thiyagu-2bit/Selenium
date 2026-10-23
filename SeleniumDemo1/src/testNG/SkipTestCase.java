package testNG;

import org.testng.annotations.Test;

public class SkipTestCase {

	@Test(priority=0)
	public void FirstStartCar() {
		System.out.println("Start The Car");
	}
	@Test(priority=1,enabled = false)
	public void putFristGeer() {
		System.out.println("Frist Geer");
	}
	@Test(priority=2)
	public void putSecondGeer() {
		System.out.println("Second Geer");
	}
}
