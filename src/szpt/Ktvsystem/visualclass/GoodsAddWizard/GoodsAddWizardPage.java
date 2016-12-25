package szpt.Ktvsystem.visualclass.GoodsAddWizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;



public class GoodsAddWizardPage extends WizardPage{
	private Shell sShell=null;
	private GoodsAddComposite goodsAddCp=null;
	
	protected GoodsAddWizardPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite arg0) {
		// TODO Auto-generated method stub
		sShell=arg0.getShell();
		this.setTitle("输入新包房信息");
		this.setDescription("请输入完整的包房信息。");
		goodsAddCp=new GoodsAddComposite(arg0,SWT.NONE);
		this.setControl(goodsAddCp);
	}

	public GoodsAddComposite getGoodsAddCp() {
		return goodsAddCp;
	}
}
