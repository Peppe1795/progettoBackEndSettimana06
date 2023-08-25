package Giuseppe.gestione.incendi.entities;

import java.util.UUID;

import Giuseppe.gestione.incendi.interfaces.Observer;
import lombok.Data;

@Data
public class Sonda implements Observer {
	private UUID id;
	private String latitudine;
	private String longitudine;
	private Integer livelloFumo = 0;

	private Sonda(String latitudine, String longitudine, Integer livelloFumo) {
		this.id = UUID.randomUUID();
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.livelloFumo = livelloFumo;
	}

	@Override
	public void controlloLivelloFumo() {
		if (this.livelloFumo >= 5) {
			String notificationUrl = generateNotificationUrl();
			System.out.println("ALLARME!!! La sonda n. " + id + " ha registrato un livello di fumo di " + livelloFumo
					+ ", latitudine: " + latitudine + " longitudine: " + longitudine);
			System.out.println("\"Il centro di controllo ha mandato L'URL di notifica: " + notificationUrl);
		} else {
			System.out.println("Livello di fumo nei limiti!!");
		}

	}

	public String generateNotificationUrl() {
		return "http://host/alarm?idsonda=" + id + "&lat=" + latitudine + "&lon=" + longitudine + "&smokelevel="
				+ livelloFumo;
	}

}
