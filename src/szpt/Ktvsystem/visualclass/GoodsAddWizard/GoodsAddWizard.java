package szpt.Ktvsystem.visualclass.GoodsAddWizard;

import org.eclipse.jface.wizard.Wizard;

import szpt.Ktvsystem.visualclass.SystemManageShell;




public class GoodsAddWizard extends Wizard{

	private GoodsAddWizardPage goodsAddWp;
	private GoodsAddComposite goodsAddCp;

	public GoodsAddWizard() {
		this.setWindowTitle("添加新商品哈哈哈");	
	}
	@Override
	public boolean performFinish() {

		goodsAddCp=goodsAddWp.getGoodsAddCp();
		String goodsNo=goodsAddCp.getTextGoodsNo().getText().trim();
		String goodsName=goodsAddCp.getTextGoodsName().getText().trim();
		float goodsPrice=Float.parseFloat(goodsAddCp.getTextGoodsPrice().getText().trim());
		
		SystemManageShell.newgoods.setGoodsName(goodsName);
		SystemManageShell.newgoods.setGoodsNo(goodsNo);
		SystemManageShell.newgoods.setGoodsPrice(goodsPrice);
		
		return true;
	}

	@Override
	public void addPages() {
		goodsAddWp=new GoodsAddWizardPage("添加新包房");
		this.addPage(goodsAddWp);
	}

	@Override
	public boolean needsPreviousAndNextButtons() {
		return false;
	}
}
