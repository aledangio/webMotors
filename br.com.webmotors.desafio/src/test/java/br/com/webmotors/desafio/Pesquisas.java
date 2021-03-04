//Para realizar esse teste utilizei o Katalon para fazer o roteiro do Teste e após isso expostei o codigo em  Java. Fiz alguns ajustes, 
// buscando ajuda no Google. Não foi facil conseguir isso, principalmente conseguir fazer a automação clicar para ver todos os carros da loja
//Não consegui realizar os testes automatizados com API
package br.com.webmotors.desafio;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pesquisas {
	private static final String PATH_PARA_DRIVER_CHROME = System.getProperty("user.dir")
			+ "\\drivers\\chromedriver.exe";
	private final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	private WebDriver driver;
	private final String BASE_URL = "https://www.webmotors.com.br";

	private WebDriverWait wait;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty(WEBDRIVER_CHROME_DRIVER, PATH_PARA_DRIVER_CHROME);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);

		// Maximimar pagina
		driver.manage().window().maximize();
	}

	@Test
	public void testE2() throws Exception {
		// Abrir a pagina do sistema
		driver.get(BASE_URL);

		// Verificar titulo da pagina
		String tituloDaPagina = "Webmotors | Compre, venda e financie carros usados, novos e motos";
		assertTrue("Titulo da Pagina", driver.getTitle().contentEquals(tituloDaPagina));

		metodoQueClica(By.xpath("//a[text()='Ver Ofertas']"));
		// localizar objeto
		// clicar em concessionaria

		String strChkConcessionaria = "(//input[@id='Concessionária']/../../child::label[@class='Checkbox'])[1]";
		By chkConcessionaria = By.xpath(strChkConcessionaria);
		encontrarObjeto(chkConcessionaria);
		metodoQueClica(chkConcessionaria);

		// clicar em honda(logo)
//		metodoQueClica(By.xpath("//div[@id='root']/main/div/div[2]/div/div[5]/div[3]/label"));
		By imgHonda = By.xpath("//small[text()='honda']/..");
		encontrarObjeto(imgHonda);
		metodoQueClica(imgHonda);

		// clicar em listar o modelo
		By lblTodosOsModelos = By.xpath("//div[text()='Todos os modelos']");
		encontrarObjeto(lblTodosOsModelos);
		metodoQueClica(lblTodosOsModelos);

		// clicar em MODELO
		By lblModeloCity = By.linkText("CITY");
		metodoQueClica(lblModeloCity);

//		// clicar para listar todas as versoes
		By lblTodasAsVersoes = By.xpath("//div[text()='Todas as versões']");
		encontrarObjeto(lblTodasAsVersoes);
		metodoQueClica(lblTodasAsVersoes);

		// clicar na versao do carro.
		By lblVersaoCarro = By.linkText("1.5 DX 16V FLEX 4P AUTOMÁTICO");
		metodoQueClica(lblVersaoCarro);

		// seleciona o carro, dentre todos os carros honda city vai escolher o
		// primeiro[1]
		metodoQueClica(By.xpath("(//h2[text()='HONDA CITY'])[1]"));

		// Utilizar outra Aba
		List<String> abas = new ArrayList<String>();
		abas.addAll(driver.getWindowHandles());
		driver.switchTo().window(abas.get(1));

		By btnVerTodosOsCarrosDesteVendedor = By.xpath("//a[text()='Ver todos os carros deste vendedor']/i");
		metodoQueClica(btnVerTodosOsCarrosDesteVendedor);

		// Esperar ou colocar alguma validação ou elemento que garanta que esta na nova
		// tela
		Thread.sleep(5000);
	}

	private void metodoQueClica(By element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
			} catch (Exception e2) {
			}
		}
	}

	public void encontrarObjeto(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public void encontrarObjeto(By element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
	}

}