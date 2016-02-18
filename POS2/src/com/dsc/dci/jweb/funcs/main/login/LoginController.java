package com.dsc.dci.jweb.funcs.main.login;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Login.dsc")
public class LoginController {
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	HashMap<String, Object> postMethod(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> datas = null;
		//String dcikey = request.getParameter(DCIWebConstants.getPostTagId());
		String action = request.getParameter("action");
		String uid = request.getParameter("uid");
		HttpSession session = null;

		//if (dcikey != null && dcikey.equals(DCIWebConstants.getPostTagValue())) {
			session = request.getSession();
			if (action.equals("checkAuth")) {
				datas = new Login().Auth(request.getParameter("sql_id"));
			} else if (action.equals("query")) {
//				datas = new KanBan(session, uid).getKanBanData(request.getParameter("page"),
//						request.getParameter("pagesize"), request.getParameter("filter"), request.getParameter("sort"),
//						request.getParameter("sql_id"), request.getParameter("conn_id"));
			}
		//}
		return datas;
	}
}
