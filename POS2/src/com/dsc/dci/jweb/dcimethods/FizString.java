package com.dsc.dci.jweb.dcimethods;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;

public class FizString {
	public static boolean isNullOrEmpty(String str) {
		boolean isnull = false;

		if (str == null) {
			isnull = true;
		} else {
			if (str.trim().length() == 0) {
				isnull = true;
			}
		}

		return isnull;
	}
	public static String Encode(String str) {
		String code = null;
		try {
			if (str != null && !str.equals("")) {
				Base64 b64 = new Base64();
				code = new String(b64.encode(str.getBytes()));
			}
		} catch (Exception ex) {
			code = null;
			ex.printStackTrace();
		}
		return code;
	}
	public static String strFix(String str, int length, boolean fixLeft, String fixChar) {
		String tmp = null;

		tmp = str;

		for (int i = 0; i < length - str.length(); i++) {
			if (fixLeft) {
				tmp = fixChar + tmp;
			} else {
				tmp = tmp + fixChar;
			}
		}

		return tmp;
	}
	public static String Decode(String code) {
		String str = null;
		try {
			if (code != null && !code.equals("")) {
				Base64 b64 = new Base64();
				str = new String(b64.decode(code.getBytes()));
			}
		} catch (Exception ex) {
			str = null;
			ex.printStackTrace();
		}
		return str;
	}
	public static HashMap<String, Object> jsonTranToObjMap(String json) {
		HashMap<String, Object> result = null;
		try {
			if (!isNullOrEmpty(json)) {
				result = new ObjectMapper().readValue(json, HashMap.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result == null) {
				result = new HashMap<String, Object>();
			}
		}
		return result;
	}

	public static Object jsonTranToObjMap(String json, Class<?> c) {
		Object result = null;
		try {
			if (!isNullOrEmpty(json)) {
				result = new ObjectMapper().readValue(json, c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result == null) {
				result = new Object();
			}
		}
		return result;
	}

	public static String jsonTranFromArrayList(ArrayList<HashMap<String, String>> obj) {
		String result = null;
		try {
			result = new ObjectMapper().writeValueAsString(obj);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result == null) {
				result = "";
			}
		}
		return result;
	}

}
