package szpt.Ktvsystem.visualclass;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Font;

import szpt.Ktvsystem.dateclass.CommonADO;





public class KTVLogin {

	private Shell sShell = null;
	private Label label1 = null;
	private Label label2 = null;
	private Label label3 = null;
	private Label labelName = null;
	private Label labelPass = null;
	private Text textUserName = null;
	private Text textPassWord = null;
	private Button buttonLogin = null;
	private Button buttonExit = null;
	private Label label5 = null;
	private Label label6 = null;
	private Label label = null;
	private Label label11 = null;
	private Label label10 = null;
	private Label label12 = null;
	private Text textUserName1 = null;
	private Text textPassword = null;
	private Button button = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* Before this is run, be sure to set up the launch configuration (Arguments->VM Arguments)
		 * for the correct SWT library path in order to run with the SWT dlls. 
		 * The dlls are located in the SWT plugin jar.  
		 * For example, on Windows the Eclipse SWT 3.1 plugin jar is:
		 *       installation_directory\plugins\org.eclipse.swt.win32_3.1.0.jar
		 */
		Display display = Display.getDefault();
		KTVLogin thisClass = new KTVLogin();
		thisClass.createSShell();
		thisClass.sShell.open();

		while (!thisClass.sShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		GridData gridData41 = new GridData();
		gridData41.horizontalAlignment = GridData.END;
		gridData41.verticalAlignment = GridData.CENTER;
		GridData gridData36 = new GridData();
		gridData36.horizontalSpan = 4;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.END;
		gridData.verticalAlignment = GridData.CENTER;
		sShell = new Shell(SWT.DIALOG_TRIM);
		sShell.setText("KTV登陆系统");
		sShell.setLayout(null);
		sShell.setSize(new Point(300, 200));
		label10 = new Label(sShell, SWT.NONE);
		label10.setText("用户名：");
		label10.setFont(new Font(Display.getDefault(), "隶书", 14, SWT.NORMAL));
		label10.setBounds(new Rectangle(57, 33, 68, 24));
		textUserName = new Text(sShell, SWT.BORDER);
		textUserName.setBounds(new Rectangle(153, 36, 70, 17));
		label12 = new Label(sShell, SWT.NONE);
		label12.setText("密码：");
		label12.setFont(new Font(Display.getDefault(), "隶书", 14, SWT.NORMAL));
		label12.setBounds(new Rectangle(60, 66, 59, 22));
		textPassword = new Text(sShell, SWT.BORDER);
		textPassword.setBounds(new Rectangle(152, 71, 70, 18));
		buttonLogin = new Button(sShell, SWT.NONE);
		buttonLogin.setText("登陆");
		buttonLogin.setBounds(new Rectangle(77, 103, 36, 22));
		buttonLogin.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				String userName=textUserName.getText().trim();
				String password=textPassword.getText().trim();
				CommonADO ado=CommonADO.getCommonADO();
				String queryStr="select * from Users where UserName='"+userName+"' and Password='"+password+"'";
				ResultSet rs=ado.executeSelect(queryStr);
				
				try {
				
					if(rs.next()){
						
						SystemMainShell.userType=rs.getString("UserType");
						System.out.print(rs.getString("UserType"));
						
						Shell oldShell=sShell;
						sShell=new SystemMainShell().getsShell();
						sShell.open();
						
						oldShell.setVisible(false);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		buttonExit = new Button(sShell, SWT.NONE);
		buttonExit.setText("退出");
		buttonExit.setBounds(new Rectangle(186, 101, 36, 22));
		buttonExit.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				System.exit(0);
			}
		});
		
		sShell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		sShell.setBackgroundImage(new Image(Display.getCurrent(),
				getClass().getResourceAsStream("/images/background.png")));
		
	}


	public Shell getsShell() {
		// TODO Auto-generated method stub
		return null;
	}

}
