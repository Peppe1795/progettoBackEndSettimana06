package Giuseppe.gestione.incendi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import Giuseppe.gestione.incendi.entities.ControlCenter;
import Giuseppe.gestione.incendi.entities.Sonda;

@SpringBootTest
public class ControlCenterTest {
	private ControlCenter controlCenter;
	private Sonda sonda1;
	private Sonda sonda2;

	@BeforeEach
	public void setUp() {
		controlCenter = new ControlCenter();
		sonda1 = new Sonda("26 °C", "27289", "27283", 0);
		sonda2 = new Sonda("150 °C", "23445", "66290", 9);
		controlCenter.register(sonda1);
		controlCenter.register(sonda2);
	}

	@Test
	public void testNotifyObservers_Allarme() {
		sonda1.setLivelloFumo(6);
		sonda2.setLivelloFumo(3);
		String expectedMessage = "ALLARME!!!";
		String result = captureConsoleOutput(() -> controlCenter.notifyObservers());
		assertEquals(true, result.contains(expectedMessage));
	}

	@Test
	public void testNotifyObservers_NeiLimiti() {
		sonda1.setLivelloFumo(3);
		sonda2.setLivelloFumo(1);
		String expectedMessage = "nei limiti";
		String result = captureConsoleOutput(() -> controlCenter.notifyObservers());
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
