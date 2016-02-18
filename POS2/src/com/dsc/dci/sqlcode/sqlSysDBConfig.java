package com.dsc.dci.sqlcode;

public class sqlSysDBConfig {
	public String deleteSysConn() {
		return "delete from CONNINFO where conn_id = 'SYSID'";
	}

	public String addSysConn() {
		return "insert into CONNINFO (conn_name,conn_desc,db_addr,db_name,db_user,db_pwd,db_type,db_port,visible,conn_id) values ('System ID','This is system connection.',?,?,?,?,?,?,'1','SYSID')";
	}

}
