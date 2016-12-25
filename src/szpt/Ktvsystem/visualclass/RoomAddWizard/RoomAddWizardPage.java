package szpt.Ktvsystem.visualclass.RoomAddWizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class RoomAddWizardPage extends WizardPage {
	private Shell sShell=null;
	private RoomAddComposite roomAddCp=null;
	
	public RoomAddWizardPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite arg0) {
		// TODO Auto-generated method stub
		sShell=arg0.getShell();
		this.setTitle("输入新包房信息");
		this.setDescription("请输入完整的包房信息。");
		roomAddCp=new RoomAddComposite(arg0,SWT.NONE);
		this.setControl(roomAddCp);
	}

	public RoomAddComposite getRoomAddCp() {
		return roomAddCp;
	}
}
