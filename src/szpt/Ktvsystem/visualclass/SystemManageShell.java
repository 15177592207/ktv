package szpt.Ktvsystem.visualclass;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import java.lang.Object;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ITableLabelProvider;
import java.lang.String;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.wizard.WizardDialog;

import szpt.Ktvsystem.dateclass.CommonADO;
import szpt.Ktvsystem.dateclass.GoodsEntity;
import szpt.Ktvsystem.dateclass.Room;
import szpt.Ktvsystem.dateclass.RoomFactory;
import szpt.Ktvsystem.visualclass.RoomAddWizard.RoomAddWizard;
import szpt.Ktvsystem.dateclass.GoodsFactory;


public class SystemManageShell {
	public static GoodsEntity newgoods = new GoodsEntity();
	public static Room newRoom = new Room();
	private Shell sShell = null;
	private Composite composite1 = null;
	private Composite composite2 = null;
	private Label label = null;
	private Label label1 = null;
	private TabFolder tabFolder = null;
	private Composite compositeRoomManage = null;
	private Composite compositeGoodsManage = null;
	private Composite compositeStaffManage = null;
	private Composite compositeUserManage = null;
	private Table tableRoom = null;
	private Table tableGoods = null;
	private Button buttonAddGoods = null;
	private Button buttonDeleteGoods = null;
	private Table tableStaff = null;
	private Button buttonAddStaff = null;
	private Button buttonDeleteSatff = null;
	private Table tableUser = null;
	private Button buttonAddUser = null;
	private Button buttonDeleteUser = null;
	private TableViewer tableViewer1 = null;
	private TableViewer tableViewer2 = null;
	private Button buttonAddRoom = null;
	private Button buttonDeleteRoom = null;

	/**
	 * This method initializes sShell
	 */
	public SystemManageShell() {
		createSShell();
	}

	private void createSShell() {
		sShell = new Shell();
		sShell.setText("系统管理");
		sShell.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_BLACK));
		createComposite1();
		createComposite2();
		sShell.setLayout(new GridLayout());
		sShell.setSize(new Point(500, 400));

		if (SystemMainShell.userType.equals("管理员")) {
			buttonAddRoom.setEnabled(true);
			buttonDeleteRoom.setEnabled(true);
			buttonAddGoods.setEnabled(true);
			buttonDeleteGoods.setEnabled(true);
			buttonAddStaff.setEnabled(true);
			buttonAddUser.setEnabled(true);
			buttonDeleteUser.setEnabled(true);
		} else {
			buttonAddRoom.setEnabled(false);
			buttonDeleteRoom.setEnabled(false);
			buttonAddGoods.setEnabled(false);
			buttonDeleteGoods.setEnabled(false);
			buttonAddStaff.setEnabled(false);
			buttonAddUser.setEnabled(false);
			buttonDeleteUser.setEnabled(false);
		}
	}

	/**
	 * This method initializes composite1
	 * 
	 */
	private void createComposite1() {
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.heightHint = 100;
		gridData.verticalAlignment = GridData.CENTER;
		composite1 = new Composite(sShell, SWT.NONE);
		composite1.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite1.setLayoutData(gridData);
		composite1.setLayout(null);
		label = new Label(composite1, SWT.NONE);
		label.setText("Label");
		label.setBounds(new Rectangle(159, 10, 60, 60));
		label.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("/images/systemManage.png")));
		label1 = new Label(composite1, SWT.NONE);
		label1.setText("系统管理");
		label1.setFont(new Font(Display.getDefault(), "隶书", 18, SWT.NORMAL));
		label1.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label1.setBounds(new Rectangle(231, 29, 111, 37));
	}

	/**
	 * This method initializes composite2
	 * 
	 */
	private void createComposite2() {
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.heightHint = 250;
		gridData1.verticalAlignment = GridData.CENTER;
		composite2 = new Composite(sShell, SWT.NONE);
		composite2.setLayout(new GridLayout());
		createTabFolder();
		composite2.setLayoutData(gridData1);
	}

	/**
	 * This method initializes tabFolder
	 * 
	 */
	private void createTabFolder() {
		GridData gridData4 = new GridData();
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.verticalAlignment = GridData.FILL;
		gridData4.grabExcessVerticalSpace = true;
		gridData4.horizontalAlignment = GridData.FILL;
		tabFolder = new TabFolder(composite2, SWT.NONE);
		createCompositeRoomManage();
		createCompositeGoodsManage();
		createCompositeStaffManage();
		tabFolder.setLayoutData(gridData4);
		createCompositeUserManage();
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("房间管理");
		tabItem.setControl(compositeRoomManage);
		TabItem tabItem1 = new TabItem(tabFolder, SWT.NONE);
		tabItem1.setText("商品管理");
		tabItem1.setControl(compositeGoodsManage);
		TabItem tabItem2 = new TabItem(tabFolder, SWT.NONE);
		tabItem2.setText("员工管理");
		tabItem2.setControl(compositeStaffManage);
		TabItem tabItem3 = new TabItem(tabFolder, SWT.NONE);
		tabItem3.setText("系统用户管理");
		tabItem3.setControl(compositeUserManage);
	}

	/**
	 * This method initializes compositeRoomManage
	 * 
	 */
	private void createCompositeRoomManage() {
		compositeRoomManage = new Composite(tabFolder, SWT.NONE);
		compositeRoomManage.setLayout(null);
		tableRoom = new Table(compositeRoomManage, SWT.NONE);
		tableRoom.setHeaderVisible(true);
		tableRoom.setLinesVisible(true);
		tableRoom.setBounds(new Rectangle(5, 5, 454, 178));
		buttonAddRoom = new Button(compositeRoomManage, SWT.NONE);
		buttonAddRoom.setBounds(new Rectangle(90, 187, 60, 22));
		buttonAddRoom.setText("增加房间");
		buttonAddRoom.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				
				RoomAddWizard roomAddWizard = new RoomAddWizard();
				WizardDialog wDialog = new WizardDialog(sShell,roomAddWizard);	
				if(wDialog.open()==0){
					    
						if(RoomFactory.InsertDB(SystemManageShell.newRoom)){
							tableViewer1.add(SystemManageShell.newRoom);
						}
						else
							MessageDialog.openInformation(sShell, "信息提示"	, "该包房经存在，请重新添加新包房信息");
					}
			}
		});
		
		buttonDeleteRoom = new Button(compositeRoomManage, SWT.NONE);
		buttonDeleteRoom.setBounds(new Rectangle(247, 189, 88, 22));
		buttonDeleteRoom.setText("删除房间");
		buttonDeleteRoom.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				
				int delIndex=tableRoom.getSelectionIndex();
				Room roomToDel=(Room)tableRoom.getItem(delIndex).getData();
				tableViewer1.remove(roomToDel); 
				RoomFactory.deleteDB(roomToDel); 

			}
		});


		TableColumn tableColumn = new TableColumn(tableRoom, SWT.NONE);
		tableColumn.setWidth(80);
		tableColumn.setText("房间编号");
		TableColumn tableColumn1 = new TableColumn(tableRoom, SWT.NONE);
		tableColumn1.setWidth(80);
		tableColumn1.setText("房间类型");
		TableColumn tableColumn2 = new TableColumn(tableRoom, SWT.NONE);
		tableColumn2.setWidth(100);
		tableColumn2.setText("容纳人数");
		TableColumn tableColumn3 = new TableColumn(tableRoom, SWT.NONE);
		tableColumn3.setWidth(100);
		tableColumn3.setText("包房单价");
		TableColumn tableColumn4 = new TableColumn(tableRoom, SWT.NONE);
		tableColumn4.setWidth(100);
		tableColumn4.setText("房间评价");
tableViewer1 = new TableViewer(tableRoom);
		
		tableViewer1.setContentProvider(new IStructuredContentProvider() {

			public Object[] getElements(Object arg0) {
				RoomFactory roomFactory=(RoomFactory)arg0;
				
				return roomFactory.getRoomsList().toArray();

			}

			public void dispose() {
			}

			public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
			}
		});

		tableViewer1.setLabelProvider(new ITableLabelProvider() {

			public Image getColumnImage(Object arg0, int arg1) {
				// TODO Auto-generated method stub
				return null;

			}

			public String getColumnText(Object arg0, int arg1) {
				Room room = (Room) arg0;
				CommonADO ado = CommonADO.getCommonADO();
				String roomInfoQueryStr = "select * from RoomType where Type='"
						+ room.getType() + "'";
				ResultSet rs = null;
				rs = ado.executeSelect(roomInfoQueryStr);
				int peopleNum = 0;
				float price = 0;
				try {
					if (rs.next()) {
						peopleNum = rs.getInt("PeopleNums");
						price = rs.getFloat("Price");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (arg1 == 0)
					return room.getRoomNo();
				if (arg1 == 1)
					return room.getType();
				if (arg1 == 2) {
					return peopleNum + "";
				}
				if (arg1 == 3)
					return price + "";
				if (arg1 == 4)
					return room.getRemarks();

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
			}
		});
		tableViewer1.setInput(new RoomFactory());

	}

	/**
	 * This method initializes compositeGoodsManage
	 * 
	 */
	private void createCompositeGoodsManage() {
		compositeGoodsManage = new Composite(tabFolder, SWT.NONE);
		compositeGoodsManage.setLayout(null);
		tableGoods = new Table(compositeGoodsManage, SWT.NONE);
		tableGoods.setHeaderVisible(true);
		tableGoods.setLinesVisible(true);
		tableGoods.setBounds(new Rectangle(5, 5, 454, 178));
		tableViewer2 = new TableViewer(tableGoods);
		tableViewer2.setContentProvider(new IStructuredContentProvider(){

	public Object[] getElements(Object arg0) {
		GoodsFactory goodsFactory=(GoodsFactory)arg0;
		
		return goodsFactory.getGoodsList().toArray();
		
	}

	public void dispose() {
	}

	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
	}});
		tableViewer2.setInput(new GoodsFactory());
		tableViewer2.setLabelProvider(new ITableLabelProvider(){

	public Image getColumnImage(Object arg0, int arg1) {
		GoodsEntity goods = (GoodsEntity) arg0;
		   
		/*if (arg1 == 0)
			return goods.getGoodsNo();
		if (arg1 == 1)
			return goods.getGoodsName();
		if (arg1 == 2) 
			return goods.getGoodsPrice();
		
*/
		return null;

		
	}

	public String getColumnText(Object arg0, int arg1) {
		// TODO Auto-generated method stub
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
		TableColumn tableColumn5 = new TableColumn(tableGoods, SWT.NONE);
		tableColumn5.setWidth(150);
		tableColumn5.setText("商品编号");
		TableColumn tableColumn6 = new TableColumn(tableGoods, SWT.NONE);
		tableColumn6.setWidth(150);
		tableColumn6.setText("商品名称");
		TableColumn tableColumn7 = new TableColumn(tableGoods, SWT.NONE);
		tableColumn7.setWidth(150);
		tableColumn7.setText("商品单价");
		buttonDeleteGoods = new Button(compositeGoodsManage, SWT.NONE);
		buttonDeleteGoods.setBounds(new Rectangle(100, 190, 58, 22));
		buttonDeleteGoods.setText("添加商品");
		buttonAddGoods = new Button(compositeGoodsManage, SWT.NONE);
		buttonAddGoods.setBounds(new Rectangle(285, 190, 67, 22));
		buttonAddGoods.setText("删除商品");
	}

	/**
	 * This method initializes compositeStaffManage
	 * 
	 */
	private void createCompositeStaffManage() {
		compositeStaffManage = new Composite(tabFolder, SWT.NONE);
		compositeStaffManage.setLayout(null);
		tableStaff = new Table(compositeStaffManage, SWT.NONE);
		tableStaff.setHeaderVisible(true);
		tableStaff.setLinesVisible(true);
		tableStaff.setBounds(new Rectangle(5, 5, 454, 178));
		TableColumn tableColumn8 = new TableColumn(tableStaff, SWT.NONE);
		tableColumn8.setWidth(80);
		tableColumn8.setText("编号");
		TableColumn tableColumn9 = new TableColumn(tableStaff, SWT.NONE);
		tableColumn9.setWidth(80);
		tableColumn9.setText("岗位名称");
		TableColumn tableColumn10 = new TableColumn(tableStaff, SWT.NONE);
		tableColumn10.setWidth(80);
		tableColumn10.setText("姓名");
		TableColumn tableColumn11 = new TableColumn(tableStaff, SWT.NONE);
		tableColumn11.setWidth(80);
		tableColumn11.setText("性别");
		TableColumn tableColumn12 = new TableColumn(tableStaff, SWT.NONE);
		tableColumn12.setWidth(140);
		tableColumn12.setText("电话号码");
		buttonAddStaff = new Button(compositeStaffManage, SWT.NONE);
		buttonAddStaff.setText("新增员工");
		buttonAddStaff.setBounds(new Rectangle(130, 191, 60, 22));
		buttonDeleteSatff = new Button(compositeStaffManage, SWT.NONE);
		buttonDeleteSatff.setText("删除员工");
		buttonDeleteSatff.setBounds(new Rectangle(312, 192, 60, 22));
	}

	/**
	 * This method initializes compositeUserManage
	 * 
	 */
	private void createCompositeUserManage() {
		compositeUserManage = new Composite(tabFolder, SWT.NONE);
		compositeUserManage.setLayout(null);
		tableUser = new Table(compositeUserManage, SWT.NONE);
		tableUser.setHeaderVisible(true);
		tableUser.setLinesVisible(true);
		tableUser.setBounds(new Rectangle(5, 5, 454, 178));
		TableColumn tableColumn13 = new TableColumn(tableUser, SWT.NONE);
		tableColumn13.setWidth(140);
		tableColumn13.setText("帐号");
		TableColumn tableColumn14 = new TableColumn(tableUser, SWT.NONE);
		tableColumn14.setWidth(140);
		tableColumn14.setText("密码");
		TableColumn tableColumn15 = new TableColumn(tableUser, SWT.NONE);
		tableColumn15.setWidth(160);
		tableColumn15.setText("用户类型");
		buttonDeleteUser = new Button(compositeUserManage, SWT.NONE);
		buttonDeleteUser.setBounds(new Rectangle(55, 189, 79, 22));
		buttonDeleteUser.setText("新增用户");
		buttonAddUser = new Button(compositeUserManage, SWT.NONE);
		buttonAddUser.setBounds(new Rectangle(267, 188, 62, 22));
		buttonAddUser.setText("删除用户");
	}

	public Shell getsShell() {
		// TODO Auto-generated method stub
		return sShell;
	}

}
