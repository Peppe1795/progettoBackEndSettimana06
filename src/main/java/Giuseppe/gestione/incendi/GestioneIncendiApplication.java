package Giuseppe.gestione.incendi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Giuseppe.gestione.incendi.entities.ControlCenter;
import Giuseppe.gestione.incendi.entities.Sonda;

@SpringBootApplication
public class GestioneIncendiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestioneIncendiApplication.class, args);

		Sonda s = new Sonda("26 °C", "27289", "27283", 0);
		Sonda s1 = new Sonda("150 °C", "23445", "66290", 9);
		Sonda s2 = new Sonda("100 °C", "99989", "00789", 6);
		Sonda s3 = new Sonda("66 °C", "98743", "23458", 3);

		ControlCenter cc = new ControlCenter();
		cc.register(s);
		cc.register(s1);
		cc.register(s2);
		cc.register(s3);

		cc.notifyObservers();
	}

}
