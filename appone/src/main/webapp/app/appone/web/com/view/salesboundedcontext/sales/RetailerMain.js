Ext.define('Appone.appone.web.com.view.salesboundedcontext.sales.RetailerMain', {
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "RetailerMainController",
     "restURL": "/Retailer",
     "defaults": {
          "split": true
     },
     "requires": ["Appone.appone.shared.com.model.salesboundedcontext.sales.RetailerModel", "Appone.appone.web.com.controller.salesboundedcontext.sales.RetailerMainController", "Appone.appone.shared.com.model.salesboundedcontext.sales.DistributorModel", "Appone.appone.shared.com.viewmodel.salesboundedcontext.sales.RetailerViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "Retailer",
               "name": "RetailerTreeContainer",
               "itemId": "RetailerTreeContainer",
               "restURL": "/Retailer",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "RetailerTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "isDockedItem": true,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "text": "Filter",
                              "name": "filterButton",
                              "handler": "onFilterClick"
                         }]
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "Retailer",
                    "title": "Retailer",
                    "name": "Retailer",
                    "itemId": "RetailerForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "retailercode",
                         "itemId": "retailercode",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Retailer code",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Retailer code<font color='red'> *<\/font>",
                         "fieldId": "C5786B76-A425-48D6-8A39-E38CC04504B5",
                         "minLength": "0",
                         "maxLength": "256",
                         "hidden": true,
                         "value": "",
                         "bindable": "retailercode"
                    }, {
                         "name": "retailername",
                         "itemId": "retailername",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Retailer",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Retailer<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "BA06B597-4041-41EF-B07B-23E645A90768",
                         "minLength": "0",
                         "maxLength": "256",
                         "bindable": "retailername",
                         "columnWidth": 0.5
                    }, {
                         "name": "distributorcode",
                         "itemId": "distributorcode",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Distributor",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Appone.appone.shared.com.model.salesboundedcontext.sales.DistributorModel"
                         },
                         "allowBlank": false,
                         "fieldLabel": "Distributor<font color='red'> *<\/font>",
                         "fieldId": "EFA52BC0-95F1-4F26-A22E-C762892E6F39",
                         "restURL": "Distributor",
                         "bindable": "distributorcode",
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "772196E8-5D28-488F-A4AC-6BE6B26B4D0C",
                         "bindable": "versionId",
                         "hidden": true
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 63,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 63,
                              "customId": 608
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": "0 5 0 5",
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 63,
                              "customId": 609,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": "0 5 0 5",
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 63,
                              "customId": 610,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }],
                         "defaults": {}
                    }],
                    "listeners": {
                         "scope": "controller"
                    },
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "Retailer",
                    "title": "Details Grid",
                    "name": "RetailerGrid",
                    "itemId": "RetailerGrid",
                    "restURL": "/Retailer",
                    "store": [],
                    "bodyPadding": 10,
                    "columns": [{
                         "header": "Retailer code",
                         "dataIndex": "retailercode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "Retailer",
                         "dataIndex": "retailername",
                         "flex": 1
                    }, {
                         "header": "Distributor",
                         "dataIndex": "distributorcode",
                         "renderer": "renderFormValue",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "width": 30,
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "handler": "onGridRefreshClick"
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "Retailer",
               "title": "Retailer",
               "name": "Retailer",
               "itemId": "RetailerForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "retailercode",
                    "itemId": "retailercode",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Retailer code",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Retailer code<font color='red'> *<\/font>",
                    "fieldId": "C5786B76-A425-48D6-8A39-E38CC04504B5",
                    "minLength": "0",
                    "maxLength": "256",
                    "hidden": true,
                    "value": "",
                    "bindable": "retailercode"
               }, {
                    "name": "retailername",
                    "itemId": "retailername",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Retailer",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Retailer<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "BA06B597-4041-41EF-B07B-23E645A90768",
                    "minLength": "0",
                    "maxLength": "256",
                    "bindable": "retailername",
                    "columnWidth": 0.5
               }, {
                    "name": "distributorcode",
                    "itemId": "distributorcode",
                    "xtype": "combo",
                    "customWidgetType": "vdCombo",
                    "displayName": "Distributor",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "store": {
                         "data": [],
                         "model": "Appone.appone.shared.com.model.salesboundedcontext.sales.DistributorModel"
                    },
                    "allowBlank": false,
                    "fieldLabel": "Distributor<font color='red'> *<\/font>",
                    "fieldId": "EFA52BC0-95F1-4F26-A22E-C762892E6F39",
                    "restURL": "Distributor",
                    "bindable": "distributorcode",
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "772196E8-5D28-488F-A4AC-6BE6B26B4D0C",
                    "bindable": "versionId",
                    "hidden": true
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 63,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 63,
                         "customId": 608
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": "0 5 0 5",
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 63,
                         "customId": 609,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": "0 5 0 5",
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 63,
                         "customId": 610,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }],
                    "defaults": {}
               }],
               "listeners": {
                    "scope": "controller"
               },
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});