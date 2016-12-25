package szpt.Ktvsystem.dateclass;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
public class DateTimeUtil {
	private int hour;
	private int minute;
	private String day;
	
	public DateTimeUtil(String dateTimeStr){
		StringTokenizer stDateTime=new StringTokenizer(dateTimeStr," ");
		day=stDateTime.nextToken();
		String timeStr=stDateTime.nextToken();	
		StringTokenizer stTime=new StringTokenizer(timeStr,":");
		hour=Integer.parseInt(stTime.nextToken());
		minute=Integer.parseInt(stTime.nextToken());
	}
	public int getHour() {
		return hour;
	}
	public int getMinute() {
		return minute;
	}
	public String getDay() {
		return day;
	}
}

