package szpt.Ktvsystem.visualclass.RoomAddWizard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

import szpt.Ktvsystem.dateclass.CommonADO;


public class RoomAddComposite extends Composite {

	private Label label2 = null;
	private Label label3 = null;
	private Label label4 = null;
	private Label label5 = null;
	private Text textRoomNo = null;
	private Combo comboRoomType = null;
	private Text textPeopleNums = null;
	private Text textAreaRemarks = null;
	public RoomAddComposite(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {
		Label filler = new Label(this, SWT.NONE);
		filler.setBounds(new Rectangle(75, 5, 0, 12));
		Label filler11 = new Label(this, SWT.NONE);
		filler11.setBounds(new Rectangle(110, 5, 0, 12));
		Label filler1 = new Label(this, SWT.NONE);
		filler1.setBounds(new Rectangle(5, 25, 0, 12));
		Label filler2 = new Label(this, SWT.NONE);
		filler2.setBounds(new Rectangle(40, 25, 0, 12));
		label2 = new Label(this, SWT.NONE);
		label2.setText("包房编号：");
		label2.setFont(new Font(Display.getDefault(), "隶书", 12, SWT.NORMAL));
		label2.setBounds(new Rectangle(27, 49, 77, 19));
		textRoomNo = new Text(this, SWT.BORDER);
		textRoomNo.setBounds(new Rectangle(111, 50, 70, 18));
		Label filler3 = new Label(this, SWT.NONE);
		filler3.setBounds(new Rectangle(5, 49, 0, 12));
		Label filler4 = new Label(this, SWT.NONE);
		filler4.setBounds(new Rectangle(40, 49, 0, 12));
		label3 = new Label(this, SWT.NONE);
		label3.setText("包房类型：");
		label3.setFont(new Font(Display.getDefault(), "隶书", 12, SWT.NORMAL));
		label3.setBounds(new Rectangle(27, 74, 79, 19));
		createComboRoomType();
		Label filler5 = new Label(this, SWT.NONE);
		filler5.setBounds(new Rectangle(5, 73, 0, 12));
		Label filler6 = new Label(this, SWT.NONE);
		filler6.setBounds(new Rectangle(40, 73, 0, 12));
		label4 = new Label(this, SWT.NONE);
		label4.setText("容纳人数：");
		label4.setFont(new Font(Display.getDefault(), "隶书", 12, SWT.NORMAL));
		label4.setBounds(new Rectangle(26, 102, 79, 21));
		textPeopleNums = new Text(this, SWT.BORDER);
		textPeopleNums.setBounds(new Rectangle(111, 103, 70, 18));
		Label filler9 = new Label(this, SWT.NONE);
		filler9.setBounds(new Rectangle(5, 93, 0, 12));
		Label filler10 = new Label(this, SWT.NONE);
		filler10.setBounds(new Rectangle(40, 93, 0, 12));
		label5 = new Label(this, SWT.NONE);
		label5.setText("房间评价：");
		label5.setFont(new Font(Display.getDefault(), "隶书", 12, SWT.NORMAL));
		label5.setBounds(new Rectangle(24, 137, 81, 31));
		textAreaRemarks = new Text(this, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		textAreaRemarks.setBounds(new Rectangle(111, 136, 87, 36));
		this.setLayout(null);
		setSize(new Point(300, 200));
	}

	/**
	 * This method initializes comboRoomType	
	 *
	 */
	private void createComboRoomType() {
		comboRoomType = new Combo(this, SWT.NONE);
		comboRoomType.setBounds(new Rectangle(111, 73, 87, 20));
		comboRoomType
		.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				String type=comboRoomType.getText().trim();
				CommonADO ado=CommonADO.getCommonADO();
				String peoplesNumQuery="select * from RoomType where Type='"+type+"'";
				ResultSet rs=ado.executeSelect(peoplesNumQuery);
				try {
					if(rs.next())
						textPeopleNums.setText(rs.getInt("PeopleNums")+"");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			public void widgetDefaultSelected(
					org.eclipse.swt.events.SelectionEvent e) {
			}
		});

CommonADO ado=CommonADO.getCommonADO();
String roomTypeQuery="select * from RoomType";
ResultSet rs=ado.executeSelect(roomTypeQuery);
try {
	while(rs.next()){
		comboRoomType.add(rs.getString("Type"));
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	}

	public Text getTextRoomNo() {
		return textRoomNo;
	}

	public void setTextRoomNo(Text textRoomNo) {
		this.textRoomNo = textRoomNo;
	}

	public Text getTextPeopleNums() {
		return textPeopleNums;
	}

	public void setTextPeopleNums(Text textPeopleNums) {
		this.textPeopleNums = textPeopleNums;
	}

	public Text getTextAreaRemarks() {
		return textAreaRemarks;
	}

	public void setTextAreaRemarks(Text textAreaRemarks) {
		this.textAreaRemarks = textAreaRemarks;
	}

	public Combo getComboRoomType() {
		return comboRoomType;
	}

	public void setComboRoomType(Combo comboRoomType) {
		this.comboRoomType = comboRoomType;
	}
	

}
