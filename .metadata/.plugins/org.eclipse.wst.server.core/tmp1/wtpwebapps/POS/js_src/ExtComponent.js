/**
 * 
 */

Ext.define('FIZ.Customer.numberField', {
	extend : 'Ext.form.Panel',
	xtype : 'cusNumberField',
	alias : 'FIZ.Customer.numberField',
	layout : 'hbox',
	height:80,
	defaults : {
		style : 'padding:20px',
	},
	//resizable : true,
	items : [ {
		xtype : 'numberfield',
		labelWidth :80,
		labelHeight : 30,
		fieldLabel : '數量',
		id : 'num',
		name : 'num',
		value : '0',
		minValue : 0,
		maxValue : 999
	}, {
		xtype : 'button',
		cls : 'calculator-toolbar',
		weight : 30,
		height : 30,
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