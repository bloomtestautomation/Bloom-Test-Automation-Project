import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

waiting {
	timeout = 5
}

environments {

	chrome {
		driver = { new ChromeDriver() }
	}

	firefox {
		driver = { new FirefoxDriver() }
	}

}