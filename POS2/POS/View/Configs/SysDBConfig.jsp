<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<title>系統資料庫設定</title>
<link rel="stylesheet" type="text/css" href="./../../css_src/dci.css" />
<link rel="stylesheet" type="text/css" href="./../../extjs/resources/css/ext-all.css">
<script type="text/javascript" src="./../../extjs/ext-all.js"></script>
<script type="text/javascript" src="/POS/View/js/Configs/SysDBConfig.js"></script>

<script type="text/javascript">
	Ext.onReady(function() {
		Ext.QuickTips.init();
		showconfig("");
		/*this.dcistore.load(function(records) {
			if (records != null && records.length == 1) {
				showconfig(records[0].get('dcitagValue'));
			}
		});*/
	});
</script>


</head>
<body>
	<div id="ConfigForm" align="center"></div>
</body>
</html>