package com.dsc.dci.jweb.funcs.main.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import com.dci.jweb.ConnectionType;
import com.dci.jweb.DataBaseObj;
import com.dsc.dci.jweb.dcimethods.FizString;
import com.dsc.dci.sqlcode.sqlLogin;
public class Login {
	private sqlLogin cmd;
	private DataBaseObj dbobj;

	public Login() {
		this.dbobj = DataBaseObj.getInstance();
		this.cmd = new sqlLogin();
	}
	public HashMap<String,Object> Auth(String loginInfos){
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HashMap<String, Object> datas = null;
		HashMap<String, Object> tmp = null;
		int cnt = 0;
		int page = 0;
		int pagesize = 0;
		int totalRow = 0;
		try {
			conn = this.dbobj.getConnection(ConnectionType.SYS);
			ps = conn.prepareStatement(this.cmd.getAuthData(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			rs = ps.executeQuery();
			
			if (rs.next()) {
				rs.last();
				totalRow = rs.getRow();
				rs.beforeFirst();
				if ((page - 1) * pagesize <= totalRow) {
					if ((page - 1) * pagesize != 0) {
						rs.absolute((page - 1) * pagesize);
					}
					cnt = 1;
					while (rs.next()) {
						tmp = new HashMap<String, Object>();
						tmp.put("id", cnt);
						tmp.put("conn_id", rs.getString("conn_id"));
						tmp.put("conn_name", rs.getString("conn_name"));
						tmp.put("conn_desc", rs.getString("conn_desc"));
						tmp.put("db_addr", rs.getString("db_addr"));
						tmp.put("db_name", rs.getString("db_name"));
						tmp.put("db_user", rs.getString("db_user"));
						tmp.put("db_pwd", FizString.Decode(rs.getString("db_pwd")));
						tmp.put("db_type", rs.getString("db_type"));
						tmp.put("db_port", rs.getString("db_port"));
						tmp.put("visible", rs.getString("visible"));
					}
				}
			} else {
				totalRow = 0;
			}

			datas = new HashMap<String, Object>();
//			datas.put("items", items);
			datas.put("total", totalRow);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			this.dbobj.closeConnection(rs, ps, conn);
		}
		return datas;
	}
}
