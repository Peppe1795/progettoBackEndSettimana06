package Giuseppe.gestione.incendi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import Giuseppe.gestione.incendi.entities.Sonda;

@SpringBootTest
public class SondaTest {
	private Sonda sonda;

	@BeforeEach
	public void setUp() {
		sonda = new Sonda("26 °C", "27289", "27283", 0);
	}

	@Test
	public void testControlloLivelloFumo_Allarme() {
		sonda.setLivelloFumo(6);
		String expectedMessage = "ALLARME!!!";
		String result = captureConsoleOutput(() -> sonda.controlloLivelloFumo());
		assertEquals(true, result.contains(expectedMessage));
	}

	@Test
	public void testControlloLivelloFumo_NeiLimiti() {
		sonda.setLivelloFumo(3);
		String expectedMessage = "nei limiti";
		String result = captureConsoleOutput(() -> sonda.controlloLivelloFumo());
		assertEquals(true, result.contains(expectedMessage));
	}

	private String captureConsoleOutput(Runnable code) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outputStream));

		code.run();

		System.setOut(originalOut);
		return outputStream.toString();
	}
}
