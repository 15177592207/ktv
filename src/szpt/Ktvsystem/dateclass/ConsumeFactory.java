package szpt.Ktvsystem.dateclass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsumeFactory {
	private List consumeList = new ArrayList();

	public ConsumeFactory(String roomNo) {
		CommonADO ado = CommonADO.getCommonADO();
		String queryStr = "select * from GoodsOrder where RoomNo='" + roomNo
				+ "' and PayState='Î´½áÕË'";
		ResultSet rs = ado.executeSelect(queryStr);
		try {
			while (rs.next()) {
				ConsumeEntity goodsConsume = new ConsumeEntity();
				goodsConsume.setGoodsOrderNo(rs.getInt("GoodsOrderNo"));
				goodsConsume.setRoomNo(rs.getString("RoomNo"));
				goodsConsume.setGoodsName(rs.getString("GoodsName"));
				goodsConsume.setConsumeNum(rs.getInt("ConsumeNum"));
				goodsConsume.setGoodsTotalPrice(rs.getFloat("GoodsTotalPrice"));
				goodsConsume.setPayState(rs.getString("PayState"));

				consumeList.add(goodsConsume);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List getConsumeList() {
		return consumeList;
	}

	public static boolean InsertDB(ConsumeEntity consumeGoods) {
		CommonADO con = CommonADO.getCommonADO();
		String insertSql = "insert into GoodsOrder(GoodsName,RoomNo,ConsumeNum,GoodsTotalPrice,PayState) values('"
				+ consumeGoods.getGoodsName()+ "','"
				+ consumeGoods.getRoomNo()+ "',"
				+ consumeGoods.getConsumeNum()+ ","
				+ consumeGoods.getGoodsTotalPrice()+ ",'"
				+ consumeGoods.getPayState() + "')";
		if (con.executeUpdate(insertSql) > 0) {
			return true;
		} else
			return false;
	}

	public static boolean deleteDB(ConsumeEntity consumeGoods) {
		CommonADO con = CommonADO.getCommonADO();
		String delSql = "delete from GoodsOrder where GoodsOrderNo="
				+ consumeGoods.getGoodsOrderNo();
		if (con.executeUpdate(delSql) > 0)
			return true;
		else
			return false;
	}
}
