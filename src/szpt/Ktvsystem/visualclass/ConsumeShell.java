package szpt.Ktvsystem.visualclass;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import java.lang.Object;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ITableLabelProvider;
import java.lang.String;
import org.eclipse.jface.viewers.ILabelProviderListener;

import szpt.Ktvsystem.dateclass.ConsumeEntity;
import szpt.Ktvsystem.dateclass.ConsumeFactory;
import szpt.Ktvsystem.dateclass.GoodsEntity;
import szpt.Ktvsystem.dateclass.GoodsFactory;


import org.eclipse.swt.custom.ScrolledComposite;




public class ConsumeShell {

	private Shell sShell = null;
	private Composite composite = null;
	private Composite composite1 = null;
	private Composite composite2 = null;
	private Label label = null;
	private Label label1 = null;
	private Label label2 = null;
	private Table goodsTable = null;
	private Label label3 = null;
	private Label labelRoom = null;
	private Table consumeTable = null;
	private Button buttonAdd = null;
	private Button buttonDelete = null;
	private TableViewer tableViewerGoods = null;
	private TableViewer tableViewerConsume = null;
	private ScrolledComposite scrolledComposite1 = null;
	/**
	 * This method initializes sShell
	 */
	
	public ConsumeShell() {
		super();
		createSShell();
	}
	
	
	
	private void createSShell() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		sShell = new Shell();
		sShell.setText("顾客消费");
		sShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
		createComposite();
		createComposite1();
		sShell.setLayout(gridLayout);
		createComposite2();
		sShell.setSize(new Point(500, 400));
	}

	/**
	 * This method initializes composite	
	 *
	 */
	private void createComposite() {
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.CENTER;
		gridData.heightHint = 100;
		gridData.grabExcessHorizontalSpace = true;
		composite = new Composite(sShell, SWT.NONE);
		composite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite.setLayoutData(gridData);
		composite.setLayout(null);
		label = new Label(composite, SWT.NONE);
		label.setText("Label");
		label.setBounds(new Rectangle(162, 14, 60, 60));
		label.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/addCost.png")));
		label1 = new Label(composite, SWT.NONE);
		label1.setText("增加消费");
		label1.setFont(new Font(Display.getDefault(), "隶书", 24, SWT.NORMAL));
		label1.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label1.setBounds(new Rectangle(237, 37, 167, 32));
		createScrolledComposite1();
	}

	/**
	 * This method initializes composite1	
	 *
	 */
	private void createComposite1() {
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 1;
		GridData gridData3 = new GridData();
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.verticalAlignment = GridData.FILL;
		gridData3.grabExcessVerticalSpace = true;
		gridData3.horizontalAlignment = GridData.FILL;
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.BEGINNING;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.widthHint = 250;
		gridData1.verticalAlignment = GridData.FILL;
		composite1 = new Composite(sShell, SWT.NONE);
		composite1.setLayoutData(gridData1);
		composite1.setLayout(gridLayout1);
		label2 = new Label(composite1, SWT.NONE);
		label2.setText("商品清单");
		goodsTable = new Table(composite1, SWT.NONE);
		goodsTable.setHeaderVisible(true);
		goodsTable.setLayoutData(gridData3);
		goodsTable.setLinesVisible(true);
	
		TableColumn tableColumn = new TableColumn(goodsTable, SWT.NONE);
		tableColumn.setWidth(85);
		tableColumn.setText("商品编号");
		TableColumn tableColumn1 = new TableColumn(goodsTable, SWT.NONE);
		tableColumn1.setWidth(80);
		tableColumn1.setText("商品名");
		TableColumn tableColumn2 = new TableColumn(goodsTable, SWT.NONE);
		tableColumn2.setWidth(80);
		tableColumn2.setText("商品单价");
	
		tableViewerGoods = new TableViewer(goodsTable);
		tableViewerGoods.setContentProvider(new IStructuredContentProvider(){
		
			public Object[] getElements(Object arg0) {
				GoodsFactory goodsFactory=(GoodsFactory)arg0;
				return goodsFactory.getGoodsList().toArray();
			}
		
			public void dispose() {
			}
		
			public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
			}});
	
		tableViewerGoods.setLabelProvider(new ITableLabelProvider(){
		
			public Image getColumnImage(Object arg0, int arg1) {
				// TODO Auto-generated method stub
				return null;
				
			}
		
			public String getColumnText(Object arg0, int arg1) {
				GoodsEntity goods=(GoodsEntity)arg0;
				if(arg1==0)
					return goods.getGoodsNo();
				if(arg1==1)
					return goods.getGoodsName();
				if(arg1==2)
					return goods.getGoodsPrice()+"";
				return null;
				
			}
		
			public void addListener(ILabelProviderListener arg0) {
			}
		
			public void dispose() {
			}
		
			public boolean isLabelProperty(Object arg0, String arg1) {
				// TODO Auto-generated method stub
				return false;
				
			}
		
			public void removeListener(ILabelProviderListener arg0) {
			}});
		
		tableViewerGoods.setInput(new GoodsFactory());
      
	}

	/**
	 * This method initializes composite2	
	 *
	 */
	private void createComposite2() {
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.BEGINNING;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.widthHint = 230;
		gridData2.verticalAlignment = GridData.FILL;
		composite2 = new Composite(sShell, SWT.NONE);
		composite2.setLayoutData(gridData2);
		composite2.setLayout(null);
		label3 = new Label(composite2, SWT.NONE);
		label3.setText("消费清单");
		label3.setBounds(new Rectangle(5, 5, 48, 12));
		labelRoom = new Label(composite2, SWT.NONE);
		labelRoom.setText(SystemMainShell.lastSelectedRoom.getRoom().getRoomNo()+"包房消费清单");
		labelRoom.setBounds(new Rectangle(80, 7, 110, 22));
		consumeTable = new Table(composite2, SWT.NONE);
		consumeTable.setHeaderVisible(true);
		consumeTable.setLinesVisible(true);
		consumeTable.setBounds(new Rectangle(5, 39, 220, 180));
		tableViewerConsume = new TableViewer(consumeTable);
tableViewerConsume.setContentProvider(new IStructuredContentProvider(){
			public Object[] getElements(Object arg0) {
				ConsumeFactory consumeFactory=(ConsumeFactory)arg0;
				return consumeFactory.getConsumeList().toArray();
			}
		
			public void dispose() {
			}
		
			public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
			}});
		
		tableViewerConsume.setLabelProvider(new ITableLabelProvider(){
			public Image getColumnImage(Object arg0, int arg1) {
				return null;
			}
		
			public String getColumnText(Object arg0, int arg1) {
				ConsumeEntity consumeEntity=(ConsumeEntity)arg0;
				if(arg1==0)
					return consumeEntity.getGoodsName();
				if(arg1==1)
					return consumeEntity.getConsumeNum()+"";
				if(arg1==2)
					return consumeEntity.getGoodsTotalPrice()+"";
				
				return null;
				
			}
		
			public void addListener(ILabelProviderListener arg0) {
			}
		
			public void dispose() {
			}
		
			public boolean isLabelProperty(Object arg0, String arg1) {
				return false;
			}
		
			public void removeListener(ILabelProviderListener arg0) {
			}});
		TableColumn tableColumn3 = new TableColumn(consumeTable, SWT.NONE);
		tableColumn3.setWidth(80);
		tableColumn3.setText("商品名");
		TableColumn tableColumn4 = new TableColumn(consumeTable, SWT.NONE);
		tableColumn4.setWidth(80);
		tableColumn4.setText("数量");
		TableColumn tableColumn5 = new TableColumn(consumeTable, SWT.NONE);
		tableColumn5.setWidth(60);
		tableColumn5.setText("小计");
 tableViewerConsume.setInput(new ConsumeFactory(SystemMainShell.lastSelectedRoom.getRoom().getRoomNo()));
				
		buttonAdd = new Button(composite2, SWT.NONE);
		buttonAdd.setToolTipText("");
		buttonAdd.setBounds(new Rectangle(20, 222, 60, 22));
		buttonAdd.setText("增加消费");
		buttonAdd.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				
				int selectedIndex=goodsTable.getSelectionIndex();
				if(selectedIndex<0){
					MessageDialog.openInformation(sShell, "信息提示", "请选择要消费的商品");
					return ;
				}
					
				GoodsEntity goods=(GoodsEntity)goodsTable.getItem(selectedIndex).getData();
				ConsumeEntity consumeEntity=new ConsumeEntity();
				consumeEntity.setRoomNo(SystemMainShell.lastSelectedRoom.getRoom().getRoomNo());
				consumeEntity.setGoodsName(goods.getGoodsName());
				InputDialog inputDialog=new InputDialog(sShell,"商品数量输入窗口","请输入商品消费数量","",null);
				int consumeNum=0;
				if(inputDialog.open()==0)
					consumeNum=Integer.parseInt(inputDialog.getValue());
				consumeEntity.setConsumeNum(consumeNum);
				consumeEntity.setGoodsTotalPrice(goods.getGoodsPrice()*consumeNum);
				consumeEntity.setPayState("未结账");
				if(ConsumeFactory.InsertDB(consumeEntity))
					tableViewerConsume.add(consumeEntity);
				else 
					MessageDialog.openInformation(sShell, "信息提示", "未实现商品消费");
			
			}
		});
		
		buttonDelete = new Button(composite2, SWT.NONE);
		buttonDelete.setText("删除消费");
		buttonDelete.setBounds(new Rectangle(158, 223, 60, 22));
		buttonDelete
		.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			
				int deleteIndex=consumeTable.getSelectionIndex();
				ConsumeEntity deleteConsume=(ConsumeEntity)consumeTable.getItem(deleteIndex).getData();
				if(ConsumeFactory.deleteDB(deleteConsume))
					tableViewerConsume.remove(deleteConsume);
				else
					MessageDialog.openInformation(sShell, "信息提示", "删除消费商品未成功");
			}
		});
	}

	public  Shell getsShell() {
		// TODO Auto-generated method stub
		return sShell;
	}

	/**
	 * This method initializes scrolledComposite1	
	 *
	 */
	private void createScrolledComposite1() {
		scrolledComposite1 = new ScrolledComposite(composite, SWT.NONE);
		scrolledComposite1.setBounds(new Rectangle(5, 5, 64, 64));
	}

}
