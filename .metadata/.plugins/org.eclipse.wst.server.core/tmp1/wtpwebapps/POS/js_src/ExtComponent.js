/**
 * 
 */
//
function cal() {
			var num = Ext.getCmp('num').getValue();
			if (this.value == -1) {
				Ext.getCmp('num').setValue(0);
			} else if (this.value == -2) {
				Ext.getCmp('num').setValue(Math.floor(num / 10));
			} else {
				if (num > 0) {
					Ext.getCmp('num').setValue(num * 10 + this.value);
				} else {
					Ext.getCmp('num').setValue(this.value);
				}

			}

		}
Ext.define('FIZ.cus.calculator', {
	extend : 'Ext.window.Window',
	alias : 'FIZ.cus.calculator',
	bodyPadding : 5,
	height : 175,
	closeAction:'hide',
	resizable : false,
	closable : true,
	buttonAlign:'center',
	/*fbar : [ {xtype: 'tbfill'},'-',{
				text : '關閉',
				handler : function() {
					Ext.getCmp('calculatorBtn').value=0;
					this.up('window').hide();
				}
			}, '-','->'],*/
	layout : {
		type : 'table',
		columns : 3
	},
	defaults : {
		bodyStyle : 'padding:20px',
		handler : cal
	},
	items : [{
				xtype : 'button',
				value : 7,
				text : '7',
				width : 100
			}, {
				xtype : 'button',
				value : 8,
				text : '8',
				width : 100
			}, {
				xtype : 'button',
				value : 9,
				text : '9',
				width : 100
			}, {
				xtype : 'button',
				value : 4,
				text : '4',
				width : 100
			}, {
				xtype : 'button',
				value : 5,
				text : '5',
				width : 100
			}, {
				xtype : 'button',
				value : 6,
				text : '6',
				width : 100
			}, {
				xtype : 'button',
				value : 1,
				text : '1',
				width : 100
			}, {
				xtype : 'button',
				value : 2,
				text : '2',
				width : 100
			}, {
				xtype : 'button',
				value : 3,
				text : '3',
				width : 100
			}, {
				xtype : 'button',
				value : -1,
				text : '清除',
				width : 100
			}, {
				xtype : 'button',
				value : 0,
				text : '0',
				width : 100
			}, {
				xtype : 'button',
				value : -2,
				text : '刪除',
				width : 100
			}],
			 doClose:function(){
				 Ext.getCmp('calculatorBtn').value=0;
				 this.callParent(arguments);
			 }
});
Ext.define('FIZ.cus.numberField', {
	extend : 'Ext.form.Panel',
	xtype : 'cusNumberField',
	alias : 'FIZ.cus.numberField',
	layout : 'hbox',
	height:80,
	buttonAlign:'center',
	defaults : {
		style : 'padding:20px',
	},
	//resizable : true,
	items : [ {
		xtype : 'numberfield',
		labelWidth :80,
		labelHeight : 40,
		fieldLabel : '數量',
		id : 'num',
		name : 'num',
		value : '0',
		minValue : 0,
		maxValue : 999
	}, {
		xtype : 'button',
		id:'calculatorBtn',
		cls : 'calculator-toolbar',
		weight : 40,
		height : 40,
		value : 0,
		listeners : {
			click : function() {
				var digit =Ext.getCmp('calwindow');
				if (this.value == 1) {
					digit.hide();
					this.value=0;
				} else if (this.value == 0) {
					digit.show();
					this.value=1;
				}
			}
		}

	} ]
});