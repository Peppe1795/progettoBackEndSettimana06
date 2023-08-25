package Giuseppe.gestione.incendi.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Giuseppe.gestione.incendi.interfaces.Observer;
import Giuseppe.gestione.incendi.interfaces.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ControlCenter implements Subject {

	private UUID id;

	private List<Sonda> sondeList = new ArrayList<>();

	public ControlCenter(List<Sonda> sondeList) {
		this.id = UUID.randomUUID();
		this.sondeList = sondeList;
	}

	@Override
	public void register(Observer o) {
		sondeList.add((Sonda) o);

	}

	@Override
	public void unregister(Observer o) {
		sondeList.remove(o);

	}

	@Override
	public void notifyObservers() {
		for (Sonda s : sondeList) {
			s.controlloLivelloFumo();
		}

	}

}
