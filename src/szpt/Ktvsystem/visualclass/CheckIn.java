package szpt.Ktvsystem.visualclass;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Rectangle;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import szpt.Ktvsystem.dateclass.CommonADO;

public class CheckIn {

	private Shell sShell = null;
	private Composite composite = null;
	private Composite composite1 = null;
	private Composite composite2 = null;
	private Label label = null;
	private Label label1 = null;
	private Label label3 = null;
	private Label label4 = null;
	private Label label5 = null;
	private Label label6 = null;
	private Label label7 = null;
	private Label labelRoomNo = null;
	private Label labelRoomType = null;
	private Label labelCheckInTime = null;
	private Text textExpecTime = null;
	private Text textPrePayMoney = null;
	private Button buttonCheckIn = null;
	private Button buttonCancel = null;
	/**
	 * This method initializes composite	
	 *
	 */
	private void createComposite() {
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.CENTER;
		gridData.heightHint = 80;
		gridData.grabExcessHorizontalSpace = true;
		composite = new Composite(sShell, SWT.NONE);
		composite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite.setLayoutData(gridData);
		composite.setLayout(null);
		label = new Label(composite, SWT.NONE);
		label.setText("Label");
		label.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		label.setBounds(new Rectangle(99, 3, 60, 75));
		label.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/comsume.png")));
		label1 = new Label(composite, SWT.NONE);
		label1.setText("顾客开房");
		label1.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label1.setBounds(new Rectangle(167, 23, 128, 33));
		label1.setFont(new Font(Display.getDefault(), "隶书", 24, SWT.NORMAL));
	}

	/**
	 * This method initializes composite1	
	 *
	 */
	private void createComposite1() {
		GridData gridData1 = new GridData();
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.verticalAlignment = GridData.CENTER;
		gridData1.heightHint = 140;
		gridData1.horizontalAlignment = GridData.FILL;
		composite1 = new Composite(sShell, SWT.NONE);
		composite1.setLayoutData(gridData1);
		composite1.setLayout(null);
		label3 = new Label(composite1, SWT.NONE);
		label3.setText("包房编号：");
		label3.setBounds(new Rectangle(56, 22, 60, 12));
		labelRoomNo = new Label(composite1, SWT.NONE);
		labelRoomNo.setText("      ");
		labelRoomNo.setBounds(new Rectangle(121, 22, 60, 12));
		label4 = new Label(composite1, SWT.NONE);
		label4.setText("包房类型：");
		label4.setBounds(new Rectangle(56, 39, 60, 12));
		labelRoomType = new Label(composite1, SWT.NONE);
		labelRoomType.setText("      ");
		labelRoomType.setBounds(new Rectangle(121, 39, 59, 12));
		label5 = new Label(composite1, SWT.NONE);
		label5.setText("开房时间：");
		label5.setBounds(new Rectangle(56, 56, 60, 12));
		labelCheckInTime = new Label(composite1, SWT.NONE);
		labelCheckInTime.setText("      ");
		labelCheckInTime.setBounds(new Rectangle(121, 56, 61, 12));
		label6 = new Label(composite1, SWT.NONE);
		label6.setText("预定时间：");
		label6.setBounds(new Rectangle(56, 76, 60, 12));
		textExpecTime = new Text(composite1, SWT.BORDER);
		textExpecTime.setBounds(new Rectangle(121, 73, 70, 18));
		label7 = new Label(composite1, SWT.NONE);
		label7.setText("预交押金：");
		label7.setBounds(new Rectangle(56, 99, 60, 12));
		textPrePayMoney = new Text(composite1, SWT.BORDER);
		textPrePayMoney.setBounds(new Rectangle(121, 96, 70, 18));
	}

	/**
	 * This method initializes composite2	
	 *
	 */
	private void createComposite2() {
		GridData gridData2 = new GridData();
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.verticalAlignment = GridData.CENTER;
		gridData2.heightHint = 30;
		gridData2.horizontalAlignment = GridData.FILL;
		composite2 = new Composite(sShell, SWT.NONE);
		composite2.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite2.setLayoutData(gridData2);
		composite2.setLayout(null);
		buttonCheckIn = new Button(composite2, SWT.NONE);
		buttonCheckIn.setText("提交订单");
		buttonCheckIn.setBounds(new Rectangle(73, 3, 60, 22));
		buttonCheckIn
		.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				String roomNo=labelRoomNo.getText().trim();
				String checkInTime=labelCheckInTime.getText().trim();
				String expectTime=textExpecTime.getText().trim();
				float prePayMoney=Float.parseFloat(textPrePayMoney.getText().trim());
				
				String insertStr=
					"insert into RoomOrder(RoomNo,StartTime,ExpectTime,PrePayMoney) values('"
					+roomNo+"','"+checkInTime +"','"+ expectTime+"','"+prePayMoney+"')";
							
				CommonADO ado =CommonADO.getCommonADO();
				if(ado.executeUpdate(insertStr)>0){
					String updateStr="update room set State='使用' where RoomNo='"+roomNo+"'";
					if(ado.executeUpdate(updateStr)>0){
						SystemMainShell.lastSelectedRoom.setImage(new Image(Display.getCurrent(),getClass().getResourceAsStream("/images/redPhone.png")));
						SystemMainShell.lastSelectedRoom.getRoom().setState("使用");
						String roomType=SystemMainShell.lastSelectedRoom.getRoom().getType();
						if(roomType.equals("小包房")){
							SystemMainShell.littleLeft-=1;
							SystemMainShell.labelLittleLeft.setText(SystemMainShell.littleLeft+"");
							
						}
						else if(roomType.equals("中包房")){
							SystemMainShell.midLeft-=1;
							SystemMainShell.labelMidLeft.setText(SystemMainShell.midLeft+"");
						}
						else{
							SystemMainShell.largeLeft-=1;
							SystemMainShell.labelLargeLeft.setText(SystemMainShell.largeLeft+"");
						}
					MessageDialog.openInformation(sShell, "信息提示", "开房成功");
						
					sShell.dispose();
				}	
				}				
			}
		});
		buttonCancel = new Button(composite2, SWT.NONE);
		buttonCancel.setText("取消");
		buttonCancel.setBounds(new Rectangle(227, 5, 36, 22));
	}

	/**
	 * @param args
	 */
	
	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		sShell = new Shell();
		sShell.setText("顾客消费");
		sShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
		createComposite();
		createComposite1();
		createComposite2();
		sShell.setLayout(gridLayout);
		sShell.setSize(new Point(400, 300));
	
		labelRoomNo.setText(SystemMainShell.lastSelectedRoom.getRoom().getRoomNo());
		labelRoomType.setText(SystemMainShell.lastSelectedRoom.getRoom().getType());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateTime = new Date();
		String time = sdf.format(dateTime);
		labelCheckInTime.setText(time);
	
		

		
	}
	public CheckIn(){
		createSShell();
	}
	
	public Shell getsShell() {
		return sShell;
	}


	

}
