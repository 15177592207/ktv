package szpt.Ktvsystem.visualclass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

import szpt.Ktvsystem.dateclass.CommonADO;
import szpt.Ktvsystem.dateclass.KTVRoom;
import szpt.Ktvsystem.dateclass.Room;



public class SystemMainShell {

	
	private Shell sShell = null;  
	private ToolBar toolBar = null;
	private Composite compositeRoomInfo = null;
	private Composite compositeRoomDisplay = null;
	private Composite compositeInfo = null;
	private Label labelxx = null;
	private Label label1 = null;
	private Label label2 = null;
	private Label label3 = null;
	private Label label4 = null;
	private Label label5 = null;
	private Label label6 = null;
	private Label label7 = null;
	private Label label11 = null;
	private Label label12 = null;
	private Label label13 = null;
	private Label label14 = null;
	private Label label15 = null;
	private Label label16 = null;
	private Label label17 = null;
	private Composite compositeL = null;
	private TabFolder tabFolder = null;
	private Composite compositeLittle = null;
	private ScrolledComposite scrolledComposite1 = null;
	private Composite compositeLittleRooms = null;
	private Composite compositeMiddle = null;
	private ScrolledComposite scrolledComposite2 = null;
	private Composite compositeMiddleRooms = null;  //  @jve:decl-index=0:visual-constraint="95,623"
	private Composite compositeLarge = null;
	private ScrolledComposite scrolledComposite3 = null;
	private Composite compositeLargeRooms = null;
	private Label labelRoomNo = null;
	private Label labelRoomType = null;
	private Label labelRoomState = null;
	private Label labelPeopleNum = null;
	private Label labelStartTime = null;
	private Label labelExpectTime = null;
	private Label labelPrePayMoney = null;
	private Label labelLittleTotal = null;
	public static Label labelLittleLeft = null;
	private Label labelMidTotal = null;
	public static Label labelMidLeft = null;
	private Label labelLargeTotal = null;
	public static Label labelLargeLeft = null;
	public static int littleTotal= 0 ;
	public static int littleLeft= 0 ;
	public static int midTotal= 0 ;
	public static int midLeft= 0 ;
	public static int largeTotal= 0 ;
	public static int largeLeft= 0 ;
	
	public static KTVRoom lastSelectedRoom=null;
	private Group group = null;
	private Label labelSystemTime = null;
	public static String userType="";
	private Tray tray=null;
	private TrayItem trayItem=null;
	private ToolTip tip=null;

	
	public SystemMainShell() {
		super();
		createSShell();		// TODO Auto-generated constructor stub
	}

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.horizontalSpacing = 3;
		gridLayout.verticalSpacing = 3;
		gridLayout.numColumns = 2;
		sShell = new Shell(SWT.NO_TRIM);
		sShell.setText("Shell");
		sShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
		sShell.setLayout(gridLayout);
		createToolBar();
		createCompositeRoomInfo();
		createCompositeRoomDisplay();
		createCompositeInfo();
		sShell.setSize(new Point(800, 600));
		createTray();
	}

	/**
	 * This method initializes toolBar	
	 *
	 */
	private void createToolBar() {
		GridData gridData = new GridData();
		gridData.heightHint = 80;
		gridData.horizontalSpan = 2;
		gridData.horizontalIndent = 1;
		gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		
		toolBar = new ToolBar(sShell, SWT.NONE);
		toolBar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		toolBar.setLayoutData(gridData);
		
		ToolItem toolItemCheckIn = new ToolItem(toolBar, SWT.PUSH);
		toolItemCheckIn.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/checkin.png")));
		toolItemCheckIn.setText("顾客开房");
		toolItemCheckIn
		.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(
					org.eclipse.swt.events.SelectionEvent e) {
				if (lastSelectedRoom != null) {
					if (lastSelectedRoom.getRoom().getState().trim()
							.equals("使用")) {
						MessageDialog.openInformation(sShell, "信息提示",
								"该包房正在使用，请重新选择");
						return;
					}
					CheckIn checkIn = new CheckIn();
					checkIn.getsShell().open();
				} else
					MessageDialog.openInformation(sShell, "信息提示",
							"请选中一个包房");
			}

			public void widgetDefaultSelected(
					org.eclipse.swt.events.SelectionEvent e) {
			}
		});


		
		ToolItem toolItemComsume = new ToolItem(toolBar, SWT.PUSH);
		toolItemComsume.setText("顾客消费");
		toolItemComsume.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/addCost.png")));
		toolItemComsume
		.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				if (SystemMainShell.lastSelectedRoom == null
					|| SystemMainShell.lastSelectedRoom.getRoom().getState().trim().equals("空闲"))
					MessageDialog.openInformation(sShell, "信息提示","未选中包房或者包房不在使用,请选择正在使用的包房");
				else {
					new ConsumeShell().getsShell().open();
				}
			}
			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		ToolItem toolItemPay = new ToolItem(toolBar, SWT.PUSH);
		toolItemPay.setText("顾客结账");
		toolItemPay.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/checkout.png")));
		toolItemPay
		.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				
				if(SystemMainShell.lastSelectedRoom==null
						||SystemMainShell.lastSelectedRoom.getRoom().getState().equals("空闲")){
					MessageDialog.openInformation(sShell, "信息提示", "请选择正在使用的包房进行结账");
				}
				else{
					CheckOutShell checkOut=new CheckOutShell();
					checkOut.getsShell().open();
				}
			}
			public void widgetDefaultSelected(
					org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		ToolItem toolItemSystem = new ToolItem(toolBar, SWT.PUSH);
		toolItemSystem.setText("系统管理");
		toolItemSystem.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/systemManage.png")));
		toolItemSystem
		.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				sShell=new SystemManageShell().getsShell();
				sShell.open();
			}
			public void widgetDefaultSelected(
					org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		ToolItem toolItemSperator = new ToolItem(toolBar, SWT.SEPARATOR);
		toolItemSperator.setWidth(300);
		ToolItem toolItemLoginAgain = new ToolItem(toolBar, SWT.PUSH);
		
		toolItemLoginAgain.setText("重新登录");
		toolItemLoginAgain.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/Home.png")));
	    toolItemLoginAgain.
	      addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				KTVLogin login=new KTVLogin();
				Shell againLoginShell=login.getsShell();
				sShell.setVisible(false);
				againLoginShell.open();
			}
			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
			}
		});  
		ToolItem toolItemMinilize = new ToolItem(toolBar, SWT.PUSH);
		toolItemMinilize.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/min.png")));
		toolItemMinilize.setText("最小化");
		toolItemMinilize
		.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				sShell.setVisible(!sShell.isVisible());
				trayItem.setVisible(!sShell.isVisible());
				tip.setText("KTV管理系统");
				tip.setMessage("右键单击图标，可以选择菜单");
				tip.setVisible(true);
			}
			public void widgetDefaultSelected(
					org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		ToolItem toolItemExit = new ToolItem(toolBar, SWT.PUSH);
		toolItemExit.setText("关闭");
		toolItemExit.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/SystemExit.png")));
		toolItemExit
		.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			
				System.exit(0);
			}
			public void widgetDefaultSelected(
					org.eclipse.swt.events.SelectionEvent e) {
			}
		});


	}

	/**
	 * This method initializes compositeRoomInfo	
	 *
	 */
	private void createCompositeRoomInfo() {
		GridData gridData1 = new GridData();
		gridData1.grabExcessVerticalSpace = true;
		gridData1.verticalAlignment = GridData.FILL;
		gridData1.widthHint = 260;
		gridData1.horizontalAlignment = GridData.BEGINNING;
		gridData1.grabExcessHorizontalSpace = false;
		gridData1.heightHint = 250;
		
		compositeRoomInfo = new Composite(sShell, SWT.NONE);
		compositeRoomInfo.setLayoutData(gridData1);
		compositeRoomInfo.setLayout(null);
		
		labelxx = new Label(compositeRoomInfo, SWT.NONE);
		labelxx.setText("包房信息");
		labelxx.setBounds(new Rectangle(71, 24, 96, 24));
		labelxx.setFont(new Font(Display.getDefault(), "幼圆", 18, SWT.NORMAL));
		
		label1 = new Label(compositeRoomInfo, SWT.NONE);
		label1.setText("包房编号：");
		label1.setBounds(new Rectangle(58, 56, 60, 12));
		
		labelRoomNo = new Label(compositeRoomInfo, SWT.NONE);
		labelRoomNo.setText("      ");
		labelRoomNo.setBounds(new Rectangle(123, 56, 36, 12));
		
		label2 = new Label(compositeRoomInfo, SWT.NONE);
		label2.setText("包房类型：");
		label2.setBounds(new Rectangle(58, 73, 60, 12));
		
		labelRoomType = new Label(compositeRoomInfo, SWT.NONE);
		labelRoomType.setText("      ");
		labelRoomType.setBounds(new Rectangle(123, 73, 36, 12));
		
		label3 = new Label(compositeRoomInfo, SWT.NONE);
		label3.setText("包房状态：");
		label3.setBounds(new Rectangle(58, 90, 60, 12));
		
		labelRoomState = new Label(compositeRoomInfo, SWT.NONE);
		labelRoomState.setText("      ");
		labelRoomState.setBounds(new Rectangle(123, 90, 36, 12));
		
		label4 = new Label(compositeRoomInfo, SWT.NONE);
		label4.setText("容纳人数：");
		label4.setBounds(new Rectangle(58, 107, 60, 12));
		
		labelPeopleNum = new Label(compositeRoomInfo, SWT.NONE);
		labelPeopleNum.setText("      ");
		labelPeopleNum.setBounds(new Rectangle(123, 107, 36, 12));
		
		label5 = new Label(compositeRoomInfo, SWT.NONE);
		label5.setText("开始时间：");
		label5.setBounds(new Rectangle(58, 124, 60, 12));
		
		labelStartTime = new Label(compositeRoomInfo, SWT.NONE);
		labelStartTime.setText("      ");
		labelStartTime.setBounds(new Rectangle(123, 124, 36, 12));
		
		label6 = new Label(compositeRoomInfo, SWT.NONE);
		label6.setText("预定时间：");
		label6.setBounds(new Rectangle(58, 141, 60, 12));
		
		labelExpectTime = new Label(compositeRoomInfo, SWT.NONE);
		labelExpectTime.setText("      ");
		labelExpectTime.setBounds(new Rectangle(123, 141, 36, 12));
		
		label7 = new Label(compositeRoomInfo, SWT.NONE);
		label7.setText("已交押金：");
		label7.setBounds(new Rectangle(58, 158, 60, 12));
		
		labelPrePayMoney = new Label(compositeRoomInfo, SWT.NONE);
		labelPrePayMoney.setText("      ");
		labelPrePayMoney.setBounds(new Rectangle(123, 158, 36, 12));
		createGroup();
		
		
		
	}

	/**
	 * This method initializes compositeRoomDisplay	
	 *
	 */
	private void createCompositeRoomDisplay() {
		GridData gridData2 = new GridData();
		gridData2.grabExcessVerticalSpace = true;
		gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.widthHint = -1;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.verticalSpan = 2;
		gridData2.heightHint = 150;
		compositeRoomDisplay = new Composite(sShell, SWT.NONE);
		compositeRoomDisplay.setLayout(new FillLayout());
		createTabFolder();
		compositeRoomDisplay.setLayoutData(gridData2);
	}

	/**
	 * This method initializes compositeInfo	
	 *
	 */
	private void createCompositeInfo() {
		GridData gridData3 = new GridData();
		gridData3.grabExcessHorizontalSpace = false;
		gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData3.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData3.heightHint = 150;
		gridData3.widthHint = 260;
		gridData3.grabExcessVerticalSpace = true;
		compositeInfo = new Composite(sShell, SWT.NONE);
		compositeInfo.setLayoutData(gridData3);
		compositeInfo.setLayout(null);
		label11 = new Label(compositeInfo, SWT.NONE);
		label11.setText("包房总信息");
		label11.setBounds(new Rectangle(61, 24, 120, 24));
		label11.setFont(new Font(Display.getDefault(), "幼圆", 18, SWT.NORMAL));
		label12 = new Label(compositeInfo, SWT.NONE);
		label12.setText("小包房总数：");
		label12.setBounds(new Rectangle(10, 56, 72, 12));
		labelLittleTotal = new Label(compositeInfo, SWT.NONE);
		labelLittleTotal.setText("      ");
		labelLittleTotal.setBounds(new Rectangle(87, 56, 36, 12));
		label15 = new Label(compositeInfo, SWT.NONE);
		label15.setText("剩余：");
		label15.setBounds(new Rectangle(128, 56, 36, 12));
		labelLittleLeft = new Label(compositeInfo, SWT.NONE);
		labelLittleLeft.setText("      ");
		labelLittleLeft.setBounds(new Rectangle(169, 56, 36, 12));
		label13 = new Label(compositeInfo, SWT.NONE);
		label13.setText("中包房总数：");
		label13.setBounds(new Rectangle(10, 73, 72, 12));
		labelMidTotal = new Label(compositeInfo, SWT.NONE);
		labelMidTotal.setText("      ");
		labelMidTotal.setBounds(new Rectangle(87, 73, 36, 12));
		label16 = new Label(compositeInfo, SWT.NONE);
		label16.setText("剩余：");
		label16.setBounds(new Rectangle(128, 73, 36, 12));
		labelMidLeft = new Label(compositeInfo, SWT.NONE);
		labelMidLeft.setText("      ");
		labelMidLeft.setBounds(new Rectangle(169, 73, 36, 12));
		label14 = new Label(compositeInfo, SWT.NONE);
		label14.setText("大包房总数：");
		label14.setBounds(new Rectangle(10, 90, 72, 12));
		labelLargeTotal = new Label(compositeInfo, SWT.NONE);
		labelLargeTotal.setText("      ");
		labelLargeTotal.setBounds(new Rectangle(87, 90, 36, 12));
		label17 = new Label(compositeInfo, SWT.NONE);
		label17.setText("剩余：");
		label17.setBounds(new Rectangle(128, 90, 36, 12));
		labelLargeLeft = new Label(compositeInfo, SWT.NONE);
		labelLargeLeft.setText("      ");
		labelLargeLeft.setBounds(new Rectangle(169, 90, 36, 12));
		
		String littleTotalQueryStr="select count(*) as littleTotal from room where Type='小包房'";
		String littleLeftQueryStr
		    ="select count(*) as littleLeft from room where Type='小包房' and State='空闲'";
		
		String midTotalQueryStr="select count(*) as midTotal from room where Type='中包房'";
		String midLeftQueryStr
		    ="select count(*) as midLeft from room where Type='中包房' and State='空闲'";
		
		String largeTotalQueryStr="select count(*) as largeTotal from room where Type='大包房'";
		String largeLeftQueryStr
		    ="select count(*) as largeLeft from room where Type='大包房' and State='空闲'";
		
		CommonADO ado=CommonADO.getCommonADO();
		ResultSet rs= null;
		try{
			
			rs=ado.executeSelect(littleTotalQueryStr);
			rs.next();
			littleTotal = rs.getInt("littleTotal");
			//labelLittleTotal.setText(littleTotal+"");
			labelLittleTotal.setText(littleTotal+"");
			
			rs=ado.executeSelect(littleLeftQueryStr);
			rs.next();
			littleLeft = rs.getInt("littleLeft");
			labelLittleLeft.setText(littleLeft+"");
			
			
			rs=ado.executeSelect(midTotalQueryStr);
			rs.next();
			midTotal = rs.getInt("midTotal");
			labelMidTotal.setText(midTotal+"");
			
			rs=ado.executeSelect(midLeftQueryStr);
			rs.next();
			midLeft = rs.getInt("midLeft");
			labelMidLeft.setText(midLeft+"");
			
			rs=ado.executeSelect(largeTotalQueryStr);
			rs.next();
			largeTotal = rs.getInt("largeTotal");
			labelLargeTotal.setText(largeTotal+"");
			
			rs=ado.executeSelect(largeLeftQueryStr);
			rs.next();
			largeLeft = rs.getInt("largeLeft");
			labelLargeLeft.setText(largeLeft+"");
			
		}catch (SQLException e){
			e.printStackTrace();
		}
	
	}

	private Object executeSelect(String littleTotalQueryStr) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method initializes tabFolder	
	 *
	 */
	private void createTabFolder() {
		tabFolder = new TabFolder(compositeRoomDisplay, SWT.NONE);
		createCompositeLittle();
		createCompositeMiddle();
		createCompositeLarge();
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("小包房");
		tabItem.setControl(compositeLittle);
		TabItem tabItem50 = new TabItem(tabFolder, SWT.NONE);
		tabItem50.setText("中包房");
		tabItem50.setControl(compositeMiddle);
		TabItem tabItem51 = new TabItem(tabFolder, SWT.NONE);
		tabItem51.setText("大包房");
		tabItem51.setControl(compositeLarge);
	}

	/**
	 * This method initializes compositeLittle	
	 *
	 */
	private void createCompositeLittle() {
		compositeLittle = new Composite(tabFolder, SWT.NONE);
		compositeLittle.setLayout(new GridLayout());
		createScrolledComposite1();
	}

	/**
	 * This method initializes scrolledComposite1	
	 *
	 */
	private void createScrolledComposite1() {
		scrolledComposite1 = new ScrolledComposite(compositeLittle, SWT.V_SCROLL);
		scrolledComposite1.setExpandHorizontal(true);
		scrolledComposite1.setExpandVertical(true);
		createCompositeLittleRooms();
		scrolledComposite1.setContent(compositeLittleRooms);
		scrolledComposite1.setMinSize(compositeLittleRooms.computeSize(SWT.DEFAULT,SWT.DEFAULT));
	}

	/**
	 * This method initializes compositeLittleRooms	
	 *
	 */
	private void createCompositeLittleRooms() {
		GridLayout gridLayout3 = new GridLayout();
		gridLayout3.horizontalSpacing = 8;
		gridLayout3.verticalSpacing = 8;
		gridLayout3.numColumns = 5;
		compositeLittleRooms = new Composite(scrolledComposite1, SWT.NONE);
		compositeLittleRooms.setLayout(gridLayout3);
		displayRoom("小包房",compositeLittleRooms);
	}

	/**
	 * This method initializes compositeMiddle	
	 *
	 */
	private void createCompositeMiddle() {
		compositeMiddle = new Composite(tabFolder, SWT.NONE);
		compositeMiddle.setLayout(new FillLayout());
		createScrolledComposite2();
	}

	/**
	 * This method initializes scrolledComposite2	
	 *
	 */
	private void createScrolledComposite2() {
		scrolledComposite2 = new ScrolledComposite(compositeMiddle, SWT.V_SCROLL);
		scrolledComposite2.setExpandHorizontal(true);
		scrolledComposite2.setExpandVertical(true);
		createCompositeMiddleRooms();
		scrolledComposite2.setContent(compositeMiddleRooms);
		scrolledComposite2.setMinSize(compositeMiddleRooms.computeSize(SWT.DEFAULT,SWT.DEFAULT));
	}

	/**
	 * This method initializes compositeMiddleRooms	
	 *
	 */
	private void createCompositeMiddleRooms() {
		compositeMiddleRooms = new Composite(scrolledComposite2, SWT.NONE);
		compositeMiddleRooms.setLayout(new GridLayout());
		displayRoom("中包房",compositeMiddleRooms);
	}

	/**
	 * This method initializes compositeLarge	
	 *
	 */
	private void createCompositeLarge() {
		compositeLarge = new Composite(tabFolder, SWT.NONE);
		compositeLarge.setLayout(new GridLayout());
		createScrolledComposite3();
	}

	/**
	 * This method initializes scrolledComposite3	
	 *
	 */
	private void createScrolledComposite3() {
		scrolledComposite3 = new ScrolledComposite(compositeLarge, SWT.V_SCROLL);
		scrolledComposite3.setExpandHorizontal(true);
		scrolledComposite3.setExpandVertical(true);
		createCompositeLargeRooms();
		scrolledComposite3.setContent(compositeLargeRooms);
		scrolledComposite3.setMinSize(compositeLargeRooms.computeSize(SWT.DEFAULT,SWT.DEFAULT));
	}

	/**
	 * This method initializes compositeLargeRooms	
	 *
	 */
	private void createCompositeLargeRooms() {
		compositeLargeRooms = new Composite(scrolledComposite3, SWT.NONE);
		compositeLargeRooms.setLayout(new FillLayout());
		displayRoom("大包房",compositeLargeRooms);
	}

	/**
	 * This method initializes compositeL	
	 *
	 */
	

	public void displayRoom(String roomType,Composite compositeRoomDisplay){
		String sqlStr="select * from room where Type='"+roomType+"'";
		CommonADO ado=CommonADO.getCommonADO();
		ResultSet rs=ado.executeSelect(sqlStr);
		
		try {
			rs.last();
			int littleNum=rs.getRow();
			rs.beforeFirst();
			rs.next();
		KTVRoom[] ktvRooms=new KTVRoom[littleNum];
			for(int i=0;i<littleNum;i++){
				Room room=new Room();
				room.setRoomNo(rs.getString("RoomNo"));
				room.setType(rs.getString("Type"));
				room.setState(rs.getString("State"));
				
				ktvRooms[i]=new KTVRoom(compositeRoomDisplay,SWT.NONE);
				ktvRooms[i].setRoom(room);
				
				ktvRooms[i].setText(room.getRoomNo());
				if(ktvRooms[i].getRoom().getState().trim().equals("空闲"))
					ktvRooms[i].setImage(new Image(Display.getCurrent(),getClass().getResourceAsStream("/images/greenPhone.png")));
				else
					ktvRooms[i].setImage(new Image(Display.getCurrent(),getClass().getResourceAsStream("/images/redPhone.png")));
				
				rs.next();
				ktvRooms[i].setCursor(new org.eclipse.swt.graphics.Cursor(null,
					SWT.CURSOR_HAND));
				ktvRooms[i].addMouseListener(new MyMouseListener());
				
				
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class TimeThread extends Thread{


		public void run(){
			 Display disp = sShell.getDisplay();
			 while(true){
				 
				 disp.syncExec(new Runnable() {
					 @Override
					
					public void run() {
						// TODO Auto-generated method stub
						 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
							Date dateTime = new Date();
						String time = sdf.format(dateTime);
					  labelSystemTime.setText(time);
					}
				});
				 try{
					 Thread.sleep(1000);
				 }catch(InterruptedException e){}
			 }
			 
		 }
		
		
	}
	/**
	 * This method initializes group	
	 *
	 */
	class MyMouseListener implements MouseListener{
		public MyMouseListener() {
			super();
		}
		@Override
		public void mouseDoubleClick(MouseEvent arg0) {	
		}
		@Override
		public void mouseUp(MouseEvent arg0) {
		}
		@Override
		public void mouseDown(MouseEvent arg0) {
			
			KTVRoom  selectedktvRoom=(KTVRoom)arg0.getSource();
			if(lastSelectedRoom!=null){
				if(lastSelectedRoom.getRoom().getState().trim().equals("空闲"))
					lastSelectedRoom.setImage(new Image(Display.getCurrent(),getClass().getResourceAsStream("/images/greenPhone.png")));
				else 
					lastSelectedRoom.setImage(new Image(Display.getCurrent(),getClass().getResourceAsStream("/images/RedPhone.png")));
			}
			selectedktvRoom.setImage(new Image(Display.getCurrent(),getClass().getResourceAsStream("/images/bluePhone.png")));
			lastSelectedRoom=selectedktvRoom;
			
		
			labelRoomNo.setText(selectedktvRoom.getRoom().getRoomNo());
			labelRoomType.setText(selectedktvRoom.getRoom().getType());
			labelRoomState.setText(selectedktvRoom.getRoom().getState());

			CommonADO ado=CommonADO.getCommonADO();
			String peopleNumQuery="select * from RoomType where Type='"+selectedktvRoom.getRoom().getType()+"'";
			ResultSet rs=ado.executeSelect(peopleNumQuery);
			try {
				rs.next();
				labelPeopleNum.setText(rs.getInt("PeopleNums")+"");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(selectedktvRoom.getRoom().getState().trim().equals("空闲")){
				labelStartTime.setText("");
				labelExpectTime.setText("");
				labelPrePayMoney.setText("");
			}
			else{
				String roomUseQuery="select * from RoomOrder where RoomNo='"+selectedktvRoom.getRoom().getRoomNo()+"'";
				rs=ado.executeSelect(roomUseQuery);
				try {
					rs.next();
					labelStartTime.setText(rs.getString("StartTime"));
					labelExpectTime.setText(rs.getString("ExpectTime"));
					labelPrePayMoney.setText(rs.getFloat("PrePayMoney")+"");
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
		}
		
	}
	
	private void createGroup() {
		group = new Group(compositeRoomInfo, SWT.NONE);
		group.setLayout(null);
		group.setText("当前时间");
		group.setBounds(new Rectangle(26, 198, 210, 84));
		labelSystemTime = new Label(group, SWT.CENTER);
		labelSystemTime.setBounds(new Rectangle(34, 34, 133, 24));
		labelSystemTime.setText("");
		new TimeThread().start();
	}

	public Shell getsShell() {
		// TODO Auto-generated method stub
		return sShell;
	}
	void createTray(){
		
		tip=new ToolTip(sShell,SWT.BALLOON | SWT.ICON_INFORMATION);
		tray=Display.getDefault().getSystemTray();
		if(tray!=null){
			trayItem=new TrayItem(tray,SWT.NONE);
			trayItem.setVisible(false);
			trayItem.setToolTipText("KTV管理系统");
			trayItem.setToolTip(tip);
			trayItem.setImage(new Image(Display.getCurrent(),getClass().getResourceAsStream("/images/icon.png")));
			
			
			final Menu menuTray=new Menu(sShell,SWT.POP_UP);
			MenuItem pushDisplay = new MenuItem(menuTray, SWT.PUSH);
			pushDisplay.setText("显示");
			pushDisplay.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
					sShell.setVisible(!sShell.isVisible());
					if (sShell.getVisible()) {    
		                 sShell.setActive();    
		             } 
					tip.setText("KTV系统图标");
					tip.setMessage("右键单击图标，可以选择菜单");
					tip.setVisible(true);
				}
				public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
				}
			});
			
			MenuItem pushExit = new MenuItem(menuTray, SWT.PUSH);
			pushExit.setText("退出");
			pushExit.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
					System.exit(0);
				}
				public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
				}
			});
			
			 trayItem.addMenuDetectListener(new MenuDetectListener(){    
		            public void menuDetected(MenuDetectEvent e) {    
		            	 menuTray.setVisible(true);    
		             }    
		         });		
			 trayItem.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			 	public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			 		sShell.setVisible(!sShell.isVisible());
			 		if (sShell.getVisible()) {    
		                 sShell.setActive();    
		             } 
			 	}
			 	public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
			 	}
			 });
		}
	}
		
	}

