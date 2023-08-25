package Giuseppe.gestione.incendi.entities;

import java.util.UUID;

import Giuseppe.gestione.incendi.interfaces.Observer;
import lombok.Data;

@Data
public class Sonda implements Observer {
	private UUID id;
	private String temperatura;
	private String latitudine;
	private String longitudine;
	private Integer livelloFumo = 0;

	public Sonda(String temperatura, String latitudine, String longitudine, Integer livelloFumo) {
		this.id = UUID.randomUUID();
		this.temperatura = temperatura;
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.livelloFumo = livelloFumo;
	}

	@Override
	public void controlloLivelloFumo() {
		if (this.livelloFumo >= 5) {
			String notificationUrl = generateNotificationUrl();
			System.out.println("ALLARME!!! La sonda n: " + id + " le cui coordinate geografiche sono: latitudine: "
					+ latitudine + ", longitudine: " + longitudine + ", ha rilevato un livello di fumo= " + livelloFumo
					+ " e una temperatura di: " + temperatura);
			System.out.println("\"Il centro di controllo ha mandato L'URL di notifica: " + notificationUrl);
		} else {
			System.out.println("La sonda n: " + id + " le cui coordinate geografiche sono: latitudine: " + latitudine
					+ ", longitudine: " + longitudine + " ha registrato un livello di fumo di: " + livelloFumo
					+ " e una temperatura di: " + temperatura + " il livello e la temperatura sono nei limiti!!");
		}

	}

	public String generateNotificationUrl() {
		return "http://host/alarm?idsonda=" + id + "&lat=" + latitudine + "&lon=" + longitudine + "&smokelevel="
				+ livelloFumo;
	}

}
