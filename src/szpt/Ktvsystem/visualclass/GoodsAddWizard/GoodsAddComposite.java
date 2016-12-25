package szpt.Ktvsystem.visualclass.GoodsAddWizard;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

public class GoodsAddComposite extends Composite {

	private Label label = null;
	private Label label1 = null;
	private Label label2 = null;
	private Label label3 = null;
	private Text textGoodsNo = null;
	private Text textGoodsName = null;
	private Text textGoodsPrice = null;
	private Label label4 = null;
	private Label label5 = null;
	private Label label6 = null;

	public GoodsAddComposite(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {
		GridData gridData3 = new GridData();
		gridData3.heightHint = 20;
		gridData3.widthHint = 40;
		gridData3.heightHint = 20;
		gridData3.widthHint = 40;
		GridData gridData2 = new GridData();
		gridData2.widthHint = 40;
		gridData2.heightHint = 20;
		GridData gridData1 = new GridData();
		gridData1.heightHint = 20;
		gridData1.widthHint = 40;
		gridData1.heightHint = 20;
		gridData1.widthHint = 41;
		GridData gridData = new GridData();
		gridData.heightHint = 20;
		gridData.widthHint = 40;
		gridData.heightHint = 20;
		gridData.widthHint = 40;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		label = new Label(this, SWT.NONE);
		label.setText("");
		label.setLayoutData(gridData);
		Label filler = new Label(this, SWT.NONE);
		Label filler5 = new Label(this, SWT.NONE);
		label4 = new Label(this, SWT.NONE);
		label4.setText("");
		label4.setLayoutData(gridData1);
		Label filler1 = new Label(this, SWT.NONE);
		label1 = new Label(this, SWT.NONE);
		label1.setText("商品编号");
		textGoodsNo = new Text(this, SWT.BORDER);
		Label filler6 = new Label(this, SWT.NONE);
		Label filler2 = new Label(this, SWT.NONE);
		label2 = new Label(this, SWT.NONE);
		label2.setText("商品名称");
		textGoodsName = new Text(this, SWT.BORDER);
		Label filler4 = new Label(this, SWT.NONE);
		Label filler3 = new Label(this, SWT.NONE);
		label3 = new Label(this, SWT.NONE);
		label3.setText("商品单价");
		textGoodsPrice = new Text(this, SWT.BORDER);
		Label filler7 = new Label(this, SWT.NONE);
		label5 = new Label(this, SWT.NONE);
		label5.setText("");
		label5.setLayoutData(gridData2);
		Label filler8 = new Label(this, SWT.NONE);
		Label filler9 = new Label(this, SWT.NONE);
		label6 = new Label(this, SWT.NONE);
		label6.setText("");
		label6.setLayoutData(gridData3);
		this.setLayout(gridLayout);
		setSize(new Point(300, 200));
	}

	public Text getTextGoodsNo() {
		return textGoodsNo;
	}

	public Text getTextGoodsName() {
		return textGoodsName;
	}

	public Text getTextGoodsPrice() {
		return textGoodsPrice;
	}

}  //  @jve:decl-index=0:visual-constraint="58,10"
