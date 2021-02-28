package tc;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestCase2 {
	@Test
	public void doLogintest() {
		System.out.println("executing login test");

	}

	@Test
	public void doreg() {
		Assert.fail("Registration failed!!");

	}

	@Test
	public void doCount() {
		System.out.println("Skipping this count test");
		throw new SkipException("Skipping this test case!!!");

	}

}
