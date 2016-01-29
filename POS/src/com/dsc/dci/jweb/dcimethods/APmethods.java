package com.dsc.dci.jweb.dcimethods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dci.jweb.ConnectionStatus;
import com.dci.jweb.ConnectionType;
import com.dci.jweb.DBInfoBean;
import com.dci.jweb.DBType;
import com.dci.jweb.DataBaseObj;
import com.dsc.dci.jweb.sqlcode.methods.sqlAPmethods;

public class APmethods {
	private sqlAPmethods cmd;
	private DataBaseObj dbobj;
	public APmethods() {
		this.cmd = new sqlAPmethods();
		this.dbobj = new DataBaseObj();
	}
	public ConnectionStatus setConnectionPool() {
		Singleton s = Singleton.getInstance();
		ConnectionStatus status = ConnectionStatus.Fail;
		this.dbobj = DataBaseObj.getInstance();

		if (dbobj.isDataSourceExist(ConnectionType.SYS)) {
			dbobj.clearDataSource(ConnectionType.SYS);
		}
		ConfigControl cc = new ConfigControl();
		DBInfoBean info = cc.DBConfigLoader(APConstants.getConfigfilename());
		if (info == null) {
			status = ConnectionStatus.Fail;
		} else {
			//s.setDeflang(info.getLangType());
			//s.setOfficialUrl(info.getOfficialUrl());
			//RegisterObject.getInstance().setGuardInfo(info.getGuardIP(), info.getGuardPort());
			dbobj.createDataSource(info, ConnectionType.SYS);
			if (dbobj.isDataSourceExist(ConnectionType.SYS)) {
				status = setDataConnectionPool(dbobj);
				if (status == ConnectionStatus.Success) {
					s.setSysDBType(info.getDBType());
				}
			} else {
				status = ConnectionStatus.Fail;
			}
		}
		s.setDatabaseStatus(status == ConnectionStatus.Success);
		// System.out.println(status.toString());
		return status;
	}

	private ConnectionStatus setDataConnectionPool(DataBaseObj dbobj) {
		ConnectionStatus status = ConnectionStatus.Fail;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
//		DBControl dbctrl = new DBControl();
		// PublicMethods pm = new PublicMethods();
		DBInfoBean bean = null;
	//	DataDatabaseObject datadbobj = DataDatabaseObject.getInstance();

		try {
			conn = dbobj.getConnection(ConnectionType.SYS);
			ps = conn.prepareStatement(cmd.getConnInfo());
			rs = ps.executeQuery();
//			if (datadbobj.isDataSourceExist()) {
//				datadbobj.clearDataSource();
//			}
			while (rs.next()) {
				bean = new DBInfoBean();
				dbobj.copySysDBConfigSetting(bean);
				bean.setConnID(rs.getString("conn_id"));
				bean.setDBAddr(rs.getString("db_addr"));
				bean.setDBPort(rs.getString("db_port"));
				bean.setDBType(DBType.valueOf(rs.getString("db_type")));
				bean.setDBName(rs.getString("db_name"));
				bean.setUserName(rs.getString("db_user"));
				bean.setPassword(FizString.Decode(rs.getString("db_pwd")));
			//	datadbobj.createDataDataSource(bean, rs.getString("conn_id"));
			}
			status = ConnectionStatus.Success;
		} catch (Exception ex) {
			status = ConnectionStatus.Fail;
			ex.printStackTrace();
		} finally {
			dbobj.closeConnection(rs, ps, conn);
		}

		return status;
	}

	public void reloadProcess(boolean reloadConn) {
		System.out.println("Start DCI web process");
		System.out.println("Init singleton objects");
		Singleton s = Singleton.getInstance();
		// APPubMethods method = new APPubMethods();
		//checkLicense();

		System.out.println("Build all datasource");
		if (reloadConn) {
			setConnectionPool();
		}
		if (s.getDatabaseStatus()) {
			// System.out.println("Check Current Version");
			// new EKBVersionCheck();
//			System.out.println("Load System Config");
//			loadSystemConfig();
//			System.out.println("Load Multi Language");
//			loadMultiLanguage();
//			if (!s.getLicenseStatus()) {
//				System.out.println("license check fail");
//			}
		} else {
			System.out.println("set connection pool fail");
		}

	}
}
