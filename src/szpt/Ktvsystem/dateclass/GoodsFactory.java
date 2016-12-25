package szpt.Ktvsystem.dateclass;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsFactory {
	private List goodsList = new ArrayList();

	public GoodsFactory() {
		CommonADO ado = CommonADO.getCommonADO();
		String goodsQueryStr = "select * from Goods";
		ResultSet rs = ado.executeSelect(goodsQueryStr);
		try {
			while (rs.next()) {
				GoodsEntity goods = new GoodsEntity();
				goods.setGoodsNo(rs.getString("GoodsNo"));
				goods.setGoodsName(rs.getString("GoodsName"));
				goods.setGoodsPrice(rs.getFloat("GoodsPrice"));
				goodsList.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean InsertDB(GoodsEntity goods){
		CommonADO con=CommonADO.getCommonADO();
		String querySql="select * from Goods where GoodsNo='"+goods.getGoodsNo()+"'";
		String insertSql="insert into Goods values('"+goods.getGoodsNo()+"','"
						+goods.getGoodsName()+"','"+goods.getGoodsPrice()+"')";
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
	
	public static void deleteDB(GoodsEntity goods){
		CommonADO con=CommonADO.getCommonADO();
		String delSql="delete from Goods where GoodsNo='"+goods.getGoodsNo()+"'";
		con.executeUpdate(delSql);
	}
	
	
	public List getGoodsList() {
		return goodsList;
	}
}
