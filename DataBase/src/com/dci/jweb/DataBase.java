package com.dci.jweb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
public class DataBase {
	public DataSource createConnectionPool(DBInfoBean infos) {
		DataSource ds = null;
		Connection conn = null;
		try {
			if (infos != null) {
				Properties dsProperties = new Properties();
				dsProperties.setProperty("url",
						getUrl(infos.getDBType(), infos.getDBAddr(), infos.getDBPort(), infos.getDBName()));
				dsProperties.setProperty("driverClassName", getDrivname(infos.getDBType()));
				dsProperties.setProperty("username", infos.getUserName());
				dsProperties.setProperty("password", infos.getPassword());
				dsProperties.setProperty("maxActive", Integer.toString(infos.getMaxActive()));
				dsProperties.setProperty("minIdle", Integer.toString(infos.getMinIdle()));
				dsProperties.setProperty("maxIdle", Integer.toString(infos.getMaxIdle()));
				dsProperties.setProperty("maxWait", Long.toString(infos.getMaxWait()));
				dsProperties.setProperty("RemoveAbandoned", Boolean.toString(infos.isRemoveAbandoned()));
				dsProperties.setProperty("RemoveAbandonedTimeout",
						Integer.toString(infos.getRemoveAbandonedTimeout()));

				ds = BasicDataSourceFactory.createDataSource(dsProperties);
				conn = ds.getConnection();
				System.out.println(infos.getConnID() + " connected");
			}
		} catch (Exception ex) {
			ds = null;
			System.err.println(ex.getClass().getName() + ": " + infos.getConnID() + " connect fail" + " "
					+ ex.getMessage());
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception ex) {

			}
		}

		return ds;
	}

	public boolean testConnedtion(DBType dbtype, String addr, String port, String dbname, String userid,
			String pwd) throws Exception {
		boolean ok = false;
		Connection conn = null;

		try {
			Class.forName(getDrivname(dbtype));
			DriverManager.setLoginTimeout(3);
			conn = DriverManager.getConnection(getUrl(dbtype, addr, port, dbname), userid, pwd);

			if (conn != null && !conn.isClosed()) {
				ok = true;
			} else {
				ok = false;
			}
		} catch (Exception ex) {
			ok = false;
			throw new Exception(ex.getMessage());
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception ex) {
			}
			conn = null;
		}

		return ok;
	}

	public String getUrl(DBType dbtype, String addr, String port, String dbname) {
		String url = null;

		if (dbtype == DBType.SqlServer) {
			url = "jdbc:sqlserver://" + addr + ":" + port + ";DatabaseName=" + dbname;
		} else if (dbtype == DBType.Oracle) {
			url = "jdbc:oracle:thin:@" + addr + ":" + port + ":" + dbname;
		} 

		return url;
	}

	private String getDrivname(DBType dbtype) {
		String driverName = null;
		if (dbtype == DBType.SqlServer) {
			driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		} else if (dbtype == DBType.Oracle) {
			driverName = "oracle.jdbc.driver.OracleDriver";
		}
		return driverName;
	}

}
