package DataDriven_Framework.Datadriven;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class dataProvider {
	@Test(dataProvider = "getData")
	public void run(String link, String userName, String pwd) throws Throwable {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get(link);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//a[text()=' Continue with SAML SSO ']")).click();

		String parent = driver.getWindowHandle();
		Set<String> childs = driver.getWindowHandles();
		for (String child : childs) {
			if (!parent.equals(child)) {
				driver.switchTo().window(child);
			}
		}
		driver.findElement(By.id("i0116")).sendKeys(userName);
		driver.findElement(By.id("idSIButton9")).click();

		driver.findElement(By.id("i0118")).sendKeys(pwd);

		Thread.sleep(2000);
		driver.findElement(By.id("idSIButton9")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("idSIButton9")).click();

		driver.switchTo().window(parent);

		Thread.sleep(5000);

		List<WebElement> projectNames = driver
				.findElements(By.xpath("//tbody[@class='p-datatable-tbody']/tr/td[@class=\"project-name-column\"]"));
		projectNames.stream().forEach(s -> System.out.println(s.getText()));
		driver.close();

	}
	@DataProvider
	public Object[][] getData()
	{
	 return	new Object[][] {{"https://qa-web-arete-one.azurewebsites.net/login","eng-test-serviceaccount@areteir.com","QATestService9"}};
	}

}
