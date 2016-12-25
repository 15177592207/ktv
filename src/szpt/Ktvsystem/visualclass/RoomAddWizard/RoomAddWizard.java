package szpt.Ktvsystem.visualclass.RoomAddWizard;

import org.eclipse.jface.wizard.Wizard;

import szpt.Ktvsystem.visualclass.SystemManageShell;

public class RoomAddWizard extends Wizard {
	private RoomAddWizardPage roomAddWp;
	private RoomAddComposite roomAddCp;

	public RoomAddWizard() {
		this.setWindowTitle("添加新包房");	
	}

	@Override
	public boolean performFinish() {
		
		roomAddCp=roomAddWp.getRoomAddCp();
		String roomNo=roomAddCp.getTextRoomNo().getText().trim();
		String roomType=roomAddCp.getComboRoomType().getText().trim();
		String roomRemarks=roomAddCp.getTextAreaRemarks().getText().trim();
		
		SystemManageShell.newRoom.setRoomNo(roomNo);
		SystemManageShell.newRoom.setType(roomType);
		SystemManageShell.newRoom.setState("空闲");
		SystemManageShell.newRoom.setRemarks(roomRemarks);
		
		return true;
	}

	@Override
	public void addPages() {
		roomAddWp=new RoomAddWizardPage("添加新包房");
		this.addPage(roomAddWp);
	}

	@Override
	public boolean needsPreviousAndNextButtons() {
		return false;
	}
}
