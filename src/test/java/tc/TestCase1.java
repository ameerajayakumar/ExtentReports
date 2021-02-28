package tc;

import java.io.IOException;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestCase1 {

	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeTest
	public void setReport() {
		htmlReporter = new ExtentSparkReporter("./reports/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Automation tester", "Ameera");
		extent.setSystemInfo("Org", "Work from Home");
		extent.setSystemInfo("Build", "WFH1.1");

	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}

	@Test
	public void doLogintest() {
		test = extent.createTest("Login Test");
		System.out.println("executing login test");

	}

	@Test
	public void doreg() {
		test = extent.createTest("Register Test");
		Assert.fail("Registration failed!!");

	}

	@Test
	public void doCount() {
		test = extent.createTest("Count Test");
		System.out.println("Skipping this count test");
		throw new SkipException("Skipping this test case!!!");

	}

	@AfterMethod
	public void checkStatus(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			
			String exception = Arrays.deepToString(result.getThrowable().getStackTrace());
			test.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
					+ "</font>" + "</b >" + "</summary>" + exception.replaceAll(",", "<br>") + "</details>" + " \n");
				
			/*
			 * try {
			 * 
			 * ExtentManager.captureScreenshot(); test.fail("<b>" + "<font color=" + "red>"
			 * + "Screenshot of failure" + "</font>" + "</b>",
			 * MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
			 * .build()); } catch (IOException e) {
			 * 
			 * }
			 */
			
			String failureLogg = "TEST CASE FAILED";
			Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
			test.log(Status.FAIL, m);

		} else if (result.getStatus() == ITestResult.SKIP) {
			
			String name = result.getMethod().getMethodName();
			String logText = "<b>" + "TEST CASE: " + name.toUpperCase() + " SKIPPED" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
			test.skip(m);
		
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			
			String name = result.getMethod().getMethodName();
			String logText = "<b>" + "TEST CASE: " + name.toUpperCase() + " PASSED" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			test.pass(m);
		}

	}
}
