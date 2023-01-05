
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FacebookSignup {
	
	WebDriver driver;
	
	String fbUrl = "https://www.fb.com";
	String facebookUrl = "https://www.facebook.com";
	
	public void invokeBrowser(){
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/Saurabh Dhingra/workspace/libs/chromedriver_76/Chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();
		
		driver.get(fbUrl);
		
		String urlFromBrowser = driver.getCurrentUrl();
		
		if(urlFromBrowser.equalsIgnoreCase(facebookUrl)) {
			System.out.println("fb has redirected to facebook");
		} else {
			System.out.println("No redirection happened");
		}
	}
	
	public void facebookSignUp() {
		
		driver.findElement(By.name("firstname")).sendKeys("Tester");
		driver.findElement(By.name("lastname")).sendKeys("One");
		driver.findElement(By.name("reg_email__")).sendKeys("testeruser@test.com");
		driver.findElement(By.name("reg_passwd__")).sendKeys("testpw");
		
		Select selDate = new Select(driver.findElement(By.id("day")));
		Select selMonth = new Select(driver.findElement(By.id("month")));
		Select selYear = new Select(driver.findElement(By.id("year")));
		
		selDate.selectByVisibleText("01");
		selMonth.selectByVisibleText("Jul");
		selYear.selectByVisibleText("2003");
		
		driver.findElement(By.xpath("//input[@type='radio' and @value='2']")).click();
		
		driver.findElement(By.xpath("//button[text()='Sign Up']")).click();
	}
	
	public void closeBrowser(){
		
		driver.quit();