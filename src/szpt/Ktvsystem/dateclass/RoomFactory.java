package szpt.Ktvsystem.dateclass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomFactory {
	private List roomsList = new ArrayList();

	public RoomFactory() {
		CommonADO ado = CommonADO.getCommonADO();
		String roomQueryStr= "select * from Room";
		ResultSet rs = ado.executeSelect(roomQueryStr);
		try {
			while (rs.next()) {
				Room room = new Room();
				room.setRoomNo(rs.getString("RoomNo"));
				room.setType(rs.getString("Type"));
				room.setState(rs.getString("State"));
				room.setRemarks(rs.getString("Remarks"));
				roomsList.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean InsertDB(Room room){
		CommonADO con=CommonADO.getCommonADO();
		String querySql="select * from Room where RoomNo='"+room.getRoomNo()+"'";
		String insertSql="insert into Room values('"+room.getRoomNo()+"','"
						+room.getType()+"','"+room.getState()+"','"+room.getRemarks()+"')";
		ResultSet rs=con.executeSelect(querySql);
		try {
			if(!rs.next()){
				con.executeUpdate(insertSql);
				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void deleteDB(Room room){
		CommonADO con=CommonADO.getCommonADO();
		String delSql="delete from Room where RoomNo='"+room.getRoomNo()+"'";
		con.executeUpdate(delSql);
	}
	
	public List getRoomsList() {
		return roomsList;
	}
}
