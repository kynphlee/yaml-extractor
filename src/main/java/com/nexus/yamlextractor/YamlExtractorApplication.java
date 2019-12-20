package com.nexus.yamlextractor;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sun.tools.jar.CommandLine;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@SpringBootApplication
public class YamlExtractorApplication implements CommandLineRunner {

	@Autowired
	private WebDriver driver;

	public static void main(String[] args) {
		SpringApplication.run(YamlExtractorApplication.class, args);
	}

	@Bean
	public WebDriver webdriver() {
		return new FirefoxDriver();
	}

	@Override
	public void run(String... args) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			driver.get("https://google.com/ncr");
			driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
			WebElement firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h3>div")));
			System.out.println(firstResult.getAttribute("textContent"));
		} finally {
			driver.quit();
		}
	}
}
