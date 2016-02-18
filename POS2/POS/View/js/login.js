/**
 * 
 */
Ext.onReady(function() {
	var setBtn=Ext.create('Ext.Button', {
	    text: '資料庫設定',
	    renderTo: 'loginPage1',
	    handler: function() {
	    	window.location = "./View/Configs/SysDBConfig.jsp";
	    }
	});
	var loginForm = Ext.create('Ext.form.Panel', {
//		title : 'Login',
		width : 320,
		bodyPadding : 10,
		defaultType : 'textfield',
		defaults : {
			anchor : '100%'
		},
		items : [ {
			allowBlank : false,
			fieldLabel : 'User ID',
			name : 'user',
			emptyText : 'user id'
		}, {
			allowBlank : false,
			fieldLabel : 'Password',
			name : 'pass',
			emptyText : 'password',
			inputType : 'password'
		}, {
			xtype : 'checkbox',
			fieldLabel : 'Remember me',
			name : 'remember'
		} ],
		buttons : [
		{
			text : 'Login',
			listeners : {
				click : function(comp, newValue, oldValue, eOpts) {
					var form = this.up('form');
					window.location = "./FuncView/Orders/TheOrders.html";
					if (form != null) {
						Ext.Ajax.request({
							method : 'POST',
							url : './Login.dsc',
							params : {
								action : 'checkName',
							},
							success : function(a) {
								var result = Ext.JSON.decode(a.responseText);
								if (result.success) {
									//window.location = "./FuncView/test.html";
								} else {
								}
							},
							failure : function(f, action) {
							}
						});
					}
				}
			}
		} ]
	});

	var loginpage = Ext.create('Ext.panel.Panel', {
		renderTo : 'loginPage',
		items : [ loginForm ],
		layout : 'fit',
		title : "Login",
		collapsible : true
	});
});
