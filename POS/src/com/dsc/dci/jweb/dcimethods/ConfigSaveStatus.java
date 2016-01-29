package com.dsc.dci.jweb.dcimethods;

import com.dci.jweb.ConnectionStatus;

public enum ConfigSaveStatus {
	NoData, SaveSuccess, SaveFail, ConnectionSuccess, ConnectionFail, SaveException, GuardConnectSuccess, GuardConnectFail;

	public static ConfigSaveStatus valueOf(ConnectionStatus value) {
		if (value == ConnectionStatus.Success) {
			return ConfigSaveStatus.ConnectionSuccess;
		} else if (value == ConnectionStatus.Fail) {
			return ConfigSaveStatus.ConnectionFail;
		} else {
			return ConfigSaveStatus.SaveException;
		}
	}
}
