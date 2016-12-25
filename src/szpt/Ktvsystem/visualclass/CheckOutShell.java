package szpt.Ktvsystem.visualclass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;

import szpt.Ktvsystem.dateclass.CommonADO;
import szpt.Ktvsystem.dateclass.DateTimeUtil;


public class CheckOutShell {

	private Shell sShell = null;
	private Composite composite = null;
	private Composite composite1 = null;
	private Label label = null;
	private Label label1 = null;
	private Label label3 = null;
	private Label label4 = null;
	private Label label5 = null;
	private Label label6 = null;
	private Label label9 = null;
	private Label label10 = null;
	private Label label11 = null;
	private Label labelRoomNo = null;
	private Label labelRoomMoney = null;
	private Label labelTotalMoney = null;
	private Label labelToPay = null;
	private Label labelCheckOutTime = null;
	private Label labelGoodsMoney = null;
	private Label labelPrePayMoney = null;
	private Button buttonCheckOut = null;
	private Button buttonCancel = null;
	private Button buttonConsumeList = null;

	
	private String roomNo=null;  //  @jve:decl-index=0:
	private String checkOutTime=null;
	private float roomMoney=0;
	private float goodsMoney=0;
	private float totalMoney=0;
	private float prePayMoney=0;
	private float toPayMoney=0;
	
	

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		sShell = new Shell();
		sShell.setText("顾客结账");
		sShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
		createComposite();
		createComposite1();
		sShell.setLayout(new GridLayout());
		sShell.setSize(new Point(450, 400));
	
		roomNo=SystemMainShell.lastSelectedRoom.getRoom().getRoomNo();
		labelRoomNo.setText(roomNo);
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateTime=new Date();	
		checkOutTime=format.format(dateTime);
		labelCheckOutTime.setText(checkOutTime);
		
		
	
		CommonADO ado=CommonADO.getCommonADO();
		String queryStr1="select * from RoomOrder where RoomNo='"+roomNo+"' and EndTime is NULL";
		String queryStr2="select * from RoomType where Type='"+SystemMainShell.lastSelectedRoom.getRoom().getType()+"'";
		ResultSet rs=null;
		rs=ado.executeSelect(queryStr1);
		
		try {
			rs.next();
			prePayMoney=rs.getFloat("PrePayMoney");
			labelPrePayMoney.setText(prePayMoney+"");
			String checkInTime=rs.getString("StartTime");
			rs=ado.executeSelect(queryStr2);
			rs.next();
			float roomPrice=rs.getFloat("Price");
			DateTimeUtil dateTimeUtil1=new DateTimeUtil(checkInTime);
			DateTimeUtil dateTimeUtil2=new DateTimeUtil(checkOutTime);

			int userTime=(dateTimeUtil2.getHour()*60+dateTimeUtil2.getMinute())
			 -(dateTimeUtil1.getHour()*60+dateTimeUtil1.getMinute());
			int userHour=0;
			if(userTime%60!=0){
				userHour=userTime/60+1;
			}
			else
				userHour=userTime/60;
			
			roomMoney=roomPrice*userHour;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		labelRoomMoney.setText(roomMoney+"");
		
	
		String goodsQueryStr="select sum(GoodsTotalPrice) as GoodsMoney from GoodsOrder where RoomNo='"+roomNo
		+"' and PayState='未结账'";
		rs=ado.executeSelect(goodsQueryStr);
		try {
			rs.next();
			goodsMoney=rs.getFloat("GoodsMoney");
			labelGoodsMoney.setText(goodsMoney+"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		totalMoney=roomMoney+goodsMoney;
		labelTotalMoney.setText(totalMoney+"");

		toPayMoney=totalMoney-prePayMoney;
		labelToPay.setText(toPayMoney+"");
		
	
		/*roomNo=SystemMainShell.lastSelectedRoom.getRoom().getRoomNo();
		labelRoomNo.setText(roomNo);

		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateTime=new Date();	
		checkOutTime=format.format(dateTime);
		labelCheckOutTime.setText(checkOutTime);
		
		

		CommonADO ado=CommonADO.getCommonADO();
		String queryStr1="select * from RoomOrder where RoomNo='"+roomNo+"' and EndTime is NULL";
		String queryStr2="select * from RoomType where Type='"+SystemMainShell.lastSelectedRoom.getRoom().getType()+"'";
		ResultSet rs=null;
		rs=ado.executeSelect(queryStr1);
		
		try {
			rs.next();
			prePayMoney=rs.getFloat("PrePayMoney");
			labelPrePayMoney.setText(prePayMoney+"");
			String checkInTime=rs.getString("StartTime");
			rs=ado.executeSelect(queryStr2);
			rs.next();
			float roomPrice=rs.getFloat("Price");
			DateTimeUtil dateTimeUtil1=new DateTimeUtil(checkInTime);
			DateTimeUtil dateTimeUtil2=new DateTimeUtil(checkOutTime);
			
			int userTime=(dateTimeUtil2.getHour()*60+dateTimeUtil2.getMinute())
			 -(dateTimeUtil1.getHour()*60+dateTimeUtil1.getMinute());
			int userHour=0;
			if(userTime%60!=0){
				userHour=userTime/60+1;
			}
			else
				userHour=userTime/60;
			
			roomMoney=roomPrice*userHour;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		labelRoomMoney.setText(roomMoney+"");
		
		

		String goodsQueryStr="select sum(GoodsTotalPrice) as GoodsMoney from GoodsOrder where RoomNo='"+roomNo
		+"' and PayState='未结账'";
		rs=ado.executeSelect(goodsQueryStr);
		try {
			rs.next();
			goodsMoney=rs.getFloat("GoodsMoney");
			labelGoodsMoney.setText(goodsMoney+"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		totalMoney=roomMoney+goodsMoney;
		labelTotalMoney.setText(totalMoney+"");

		toPayMoney=totalMoney-prePayMoney;
		labelToPay.setText(toPayMoney+"");*/
	}



	/**
	 * This method initializes composite	
	 *
	 */
	private void createComposite() {
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.CENTER;
		gridData.heightHint = 100;
		gridData.horizontalAlignment = GridData.FILL;
		composite = new Composite(sShell, SWT.NONE);
		composite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite.setLayoutData(gridData);
		composite.setLayout(null);
		label = new Label(composite, SWT.NONE);
		label.setText("Label");
		label.setBounds(new Rectangle(108, 17, 60, 60));
		label.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/checkout.png")));
		label1 = new Label(composite, SWT.NONE);
		label1.setText("顾客结账");
		label1.setBounds(new Rectangle(184, 25, 128, 33));
		label1.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label1.setFont(new Font(Display.getDefault(), "隶书", 24, SWT.NORMAL));
	}

	/**
	 * This method initializes composite1	
	 *
	 */
	private void createComposite1() {
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.heightHint = 250;
		gridData1.verticalAlignment = GridData.CENTER;
		composite1 = new Composite(sShell, SWT.NONE);
		composite1.setLayoutData(gridData1);
		composite1.setLayout(null);
		label3 = new Label(composite1, SWT.NONE);
		label3.setText("结账包房：");
		label3.setFont(new Font(Display.getDefault(), "隶书", 14, SWT.NORMAL));
		label3.setBounds(new Rectangle(51, 12, 88, 24));
		labelRoomNo = new Label(composite1, SWT.NONE);
		labelRoomNo.setText("      ");
		labelRoomNo.setBounds(new Rectangle(148, 11, 52, 18));
		label9 = new Label(composite1, SWT.NONE);
		label9.setText("结账时间：");
		label9.setFont(new Font(Display.getDefault(), "隶书", 14, SWT.NORMAL));
		label9.setBounds(new Rectangle(243, 10, 93, 18));
		labelCheckOutTime = new Label(composite1, SWT.NONE);
		labelCheckOutTime.setText("      ");
		labelCheckOutTime.setBounds(new Rectangle(346, 11, 56, 18));
		label4 = new Label(composite1, SWT.NONE);
		label4.setText("包房消费金额：");
		label4.setFont(new Font(Display.getDefault(), "隶书", 14, SWT.NORMAL));
		label4.setBounds(new Rectangle(5, 39, 135, 21));
		labelRoomMoney = new Label(composite1, SWT.NONE);
		labelRoomMoney.setText("      ");
		labelRoomMoney.setBounds(new Rectangle(147, 38, 51, 20));
		label10 = new Label(composite1, SWT.NONE);
		label10.setText("商品消费金额：");
		label10.setFont(new Font(Display.getDefault(), "隶书", 14, SWT.NORMAL));
		label10.setBounds(new Rectangle(206, 44, 131, 18));
		labelGoodsMoney = new Label(composite1, SWT.NONE);
		labelGoodsMoney.setText("      ");
		labelGoodsMoney.setBounds(new Rectangle(347, 42, 55, 20));
		label5 = new Label(composite1, SWT.NONE);
		label5.setText("消费总额：");
		label5.setFont(new Font(Display.getDefault(), "隶书", 14, SWT.NORMAL));
		label5.setBounds(new Rectangle(41, 69, 98, 20));
		labelTotalMoney = new Label(composite1, SWT.NONE);
		labelTotalMoney.setText("      ");
		labelTotalMoney.setBounds(new Rectangle(148, 74, 54, 18));
		label11 = new Label(composite1, SWT.NONE);
		label11.setText("已收押金：");
		label11.setFont(new Font(Display.getDefault(), "隶书", 14, SWT.NORMAL));
		label11.setBounds(new Rectangle(253, 70, 83, 22));
		labelPrePayMoney = new Label(composite1, SWT.NONE);
		labelPrePayMoney.setText("      ");
		labelPrePayMoney.setBounds(new Rectangle(347, 71, 54, 18));
		label6 = new Label(composite1, SWT.NONE);
		label6.setText("顾客应付：");
		label6.setFont(new Font(Display.getDefault(), "隶书", 14, SWT.NORMAL));
		label6.setBounds(new Rectangle(52, 95, 86, 26));
		labelToPay = new Label(composite1, SWT.NONE);
		labelToPay.setText("      ");
		labelToPay.setBounds(new Rectangle(148, 101, 56, 20));
		buttonCheckOut = new Button(composite1, SWT.NONE);
		buttonCheckOut.setBounds(new Rectangle(55, 156, 56, 22));
		buttonCheckOut.setText("结账");
		buttonCheckOut.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				
				String insertStr="insert into Acountcheck(RoomNo,ConsumeMoney,CheckOutTime) values('"
					+roomNo+"',"+totalMoney+",'"+checkOutTime+"')";
				String updateRoomOrder="update RoomOrder set EndTime='"+checkOutTime+"' where RoomNo='"+roomNo+"'";
				String updateGoodsOrder="update GoodsOrder set PayState='已结账' where RoomNo='"+roomNo+"'";
				String updateRoom="update Room set State='空闲' where RoomNo='"+roomNo+"'";
				CommonADO ado=CommonADO.getCommonADO();
				if(ado.executeUpdate(insertStr)>0){
					ado.executeUpdate(updateRoomOrder);
					ado.executeUpdate(updateGoodsOrder);
					ado.executeUpdate(updateRoom);
					
					SystemMainShell.lastSelectedRoom.getRoom().setState("空闲");
					SystemMainShell.lastSelectedRoom.setImage(new Image(Display.getCurrent()
							,getClass().getResourceAsStream("/images/greenPhone.png")));
					String roomType=SystemMainShell.lastSelectedRoom.getRoom().getType();
					if(roomType.equals("小包房")){
						SystemMainShell.littleLeft+=1;
						SystemMainShell.labelLittleLeft.setText(SystemMainShell.littleLeft+"");	
					}
					else if(roomType.equals("中包房")){
						SystemMainShell.midLeft+=1;
						SystemMainShell.labelMidLeft.setText(SystemMainShell.midLeft+"");
					}
					else{
						SystemMainShell.largeLeft+=1;
						SystemMainShell.labelLargeLeft.setText(SystemMainShell.largeLeft+"");
					}
					MessageDialog.openInformation(sShell, "信息提示", "结账成功!");
					sShell.dispose();
				}
				else{
					MessageDialog.openInformation(sShell, "信息提示", "结账不成功");
				}	
			}
		});
		buttonCancel = new Button(composite1, SWT.NONE);
		buttonCancel.setBounds(new Rectangle(163, 156, 50, 22));
		buttonCancel.setText("取消");
		buttonConsumeList = new Button(composite1, SWT.NONE);
		buttonConsumeList.setBounds(new Rectangle(247, 157, 85, 22));
		buttonConsumeList.setText("消费明细<<<");
		buttonConsumeList.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
		
				new ConsumeShell().getsShell().open();
			}
		});
	}
	public CheckOutShell(){
		super();
		createSShell();
	}
	public Shell getsShell() {
		return sShell;
	}

	
	
	

}
