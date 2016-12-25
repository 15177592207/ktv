
package szpt.Ktvsystem.dateclass;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;

public class KTVRoom extends CLabel{
    public static final KTVRoom[] ktvRoom = null;
	private Room room = null;

	public KTVRoom(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * @param args
	 */
}











/*package szpt.Ktvsystem.dateclass;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;

public class KTVRoom extends CLabel {
	private Room room=null;
	
	public KTVRoom(Composite parent, int style) {
		super(parent, style);
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
*/