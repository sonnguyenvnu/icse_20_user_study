/** 
 * easyui????
 * @return
 */
public StringBuffer end(){
  StringBuffer sb=null;
  if (style.equals("easyui")) {
    sb=null;
  }
 else   if ("jqgrid".equals(style)) {
    sb=jqGrid();
  }
 else {
    sb=datatables();
  }
  String grid="";
  sb=new StringBuffer();
  if (btnCls != null && btnCls.indexOf("bootstrap") == 0) {
    sb.append("<link rel=\"stylesheet\" href=\"plug-in/bootstrap/css/bootstrap-btn.css\" type=\"text/css\"></link>");
  }
  boolean hasGroup=hasGroup();
  if (hasGroup) {
    sb.append("<link rel=\"stylesheet\" href=\"plug-in/tools/css/optgroup.css\" type=\"text/css\"></link>");
  }
  width=(width == null) ? "auto" : width;
  height=(height == null) ? "auto" : height;
  if (!treegrid && isShowSubGrid) {
    sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/extends/datagrid-detailview.js\"></script>");
  }
  sb.append("<script type=\"text/javascript\">");
  if (!treegrid && isShowSubGrid) {
    loadSubData(configId);
    sb.append("function  detailFormatterFun(){");
    sb.append("var s = '<div class=\"orderInfoHidden\" style=\"padding:2px;\">'+");
    sb.append("'<div class=\"easyui-tabs\"   style=\"height:230px;width:800px;\">'+");
    String subtableids[]=null;
    if (head.getSubTableStr() != null && head.getSubTableStr().length() >= 0) {
      subtableids=head.getSubTableStr().split(",");
      for (      String subtable : subtableids) {
        sb.append("'<div title=\"" + ((CgSubTableVO)tableData.get(subtable)).getHead().getContent() + "\" style=\"padding:2px;\">'+");
        sb.append("'<table class=\"" + ((CgSubTableVO)tableData.get(subtable)).getHead().getTableName() + "tablelines\" ></table>'+");
        sb.append("'</div>'+");
      }
    }
    sb.append("'</div>'+");
    sb.append("'</div>'; return s;}");
    sb.append("function onExpandRowFun(index,row){");
    sb.append("var tabs = $(this).datagrid('getRowDetail',index).find('div.easyui-tabs');");
    sb.append("tabs.tabs();");
    if (subtableids != null) {
      for (      String ss : subtableids) {
        CgSubTableVO submap=((CgSubTableVO)tableData.get(ss));
        String linkid="";
        String subfield="";
        String columns="";
        List<Map<String,Object>> subfieldlist=submap.getFieldList();
        for (        Map<String,Object> map : subfieldlist) {
          subfield+=map.get("field_name") + ",";
          columns+="{title:'" + map.get("content") + "',field:'" + map.get("field_name") + "',align:'left',width:50},";
        }
        List<CgFormFieldEntity> subFields=submap.getHead().getColumns();
        for (        CgFormFieldEntity subField : subFields) {
          if (StringUtils.isNotBlank(subField.getMainField())) {
            linkid=subField.getFieldName();
            break;
          }
        }
        sb.append("var " + submap.getHead().getTableName() + "durl = 'cgAutoListController.do?datagrid&configId=" + submap.getHead().getTableName() + "&" + linkid + "='+row.id+'&field=" + subfield + "&page=1&rows=100';");
        sb.append("var " + submap.getHead().getTableName() + "tablelines = $(this).datagrid('getRowDetail',index).find('table." + submap.getHead().getTableName() + "tablelines');");
        sb.append("" + submap.getHead().getTableName() + "tablelines.datagrid({");
        sb.append("singleSelect:true,");
        sb.append("loadMsg:'????',");
        sb.append("fitColumns:true,");
        sb.append("height:'180',");
        sb.append("pageSize : 50,");
        sb.append("pageList : [ 50, 150, 200, 250, 300 ],");
        sb.append("border:false,");
        sb.append("loadMsg:\"\",");
        sb.append("url: " + submap.getHead().getTableName() + "durl,");
        sb.append("idField : 'id',");
        sb.append("rownumbers : true,");
        sb.append("pagination : false,");
        sb.append("onLoadSuccess : function(a,b,c) {},");
        sb.append("columns:[[");
        sb.append(columns);
        sb.append("{field:'0000',hidden:true}");
        sb.append("]]");
        sb.append("});");
      }
    }
    sb.append("}");
  }
  sb.append("$(function(){  storage=$.localStorage;if(!storage)storage=$.cookieStorage;");
  sb.append(this.getNoAuthOperButton());
  if (treegrid) {
    grid="treegrid";
    sb.append("$(\'#" + name + "\').treegrid({");
    sb.append("idField:'id',");
    if (StringUtils.isNotEmpty(treeField)) {
      sb.append("treeField:'" + treeField + "',");
    }
 else {
      sb.append("treeField:'text',");
    }
    sb.append(" onBeforeLoad: function(row,param){\n" + "                    if (!row) {    \n" + "                     delete param.id;  \n" + "                    }\n" + "                },");
  }
 else {
    grid="datagrid";
    sb.append("$(\'#" + name + "\').datagrid({");
    if (this.isFilter()) {
      sb.append("onHeaderContextMenu: function(e, field){headerMenu(e, field);},");
    }
    sb.append("idField: '" + idField + "',");
  }
  if (title != null) {
    sb.append("title: \'" + title + "\',");
  }
  if (isShowSubGrid) {
    sb.append("view: detailview,");
    sb.append("detailFormatter:detailFormatterFun,");
    sb.append("onExpandRow: onExpandRowFun,");
  }
  if (autoLoadData)   sb.append("url:\'" + actionUrl + "&field=" + fields + "\',");
 else   sb.append("url:\'',");
  if (StringUtils.isNotEmpty(rowStyler)) {
    sb.append("rowStyler: function(index,row){ return " + rowStyler + "(index,row);},");
  }
  if (StringUtils.isNotEmpty(extendParams)) {
    sb.append(extendParams);
  }
  if (fit) {
    sb.append("fit:true,");
  }
 else {
    sb.append("fit:false,");
  }
  if (!nowrap) {
    sb.append("nowrap:false,");
  }
  sb.append("rownumbers: true,");
  if (collapsible) {
    sb.append("collapsible: true,");
  }
  if (hasQueryColum(columnList)) {
    String queryParams="";
    queryParams+="queryParams:{";
    for (    DataGridColumn col : columnList) {
      if (col.isQuery() && col.getDefaultVal() != null && !col.getDefaultVal().trim().equals("")) {
        if (!"group".equals(col.getQueryMode())) {
          queryParams+=col.getField() + ":'" + col.getDefaultVal() + "',";
        }
      }
    }
    if (queryParams.indexOf(",") > -1) {
      queryParams=queryParams.substring(0,queryParams.length() - 1);
    }
    queryParams+="},";
    sb.append(queryParams);
  }
  sb.append(StringUtil.replaceAll("loadMsg: \'{0}\',","{0}",MutiLangUtil.getLang("common.data.loading")));
  sb.append("pageSize: " + pageSize + ",");
  sb.append("pagination:" + pagination + ",");
  sb.append("pageList:[" + pageSize * 1 + "," + pageSize * 2 + "," + pageSize * 3 + "],");
  if (StringUtils.isNotBlank(sortName)) {
    sb.append("sortName:'" + sortName + "',");
  }
  sb.append("sortOrder:'" + sortOrder + "',");
  sb.append("rownumbers:true,");
  if (singleSelect == null) {
    sb.append("singleSelect:" + !checkbox + ",");
  }
 else {
    sb.append("singleSelect:" + singleSelect + ",");
  }
  if (fitColumns) {
    sb.append("fitColumns:true,");
  }
 else {
    sb.append("fitColumns:false,");
  }
  sb.append("striped:true,showFooter:true,");
  sb.append("frozenColumns:[[");
  this.getField(sb,0);
  sb.append("]],");
  sb.append("columns:[[");
  this.getField(sb);
  sb.append("]],");
  sb.append("onLoadSuccess:function(data){$(\"#" + name + "\")." + grid + "(\"clearChecked\");$(\"#" + name + "\")." + grid + "(\"clearSelections\");");
  if (openFirstNode && treegrid) {
    sb.append(" if(data==null){");
    sb.append(" var firstNode = $(\'#" + name + "\').treegrid('getRoots')[0];");
    sb.append(" $(\'#" + name + "\').treegrid('expand',firstNode.id)}");
  }
  sb.append("if(!" + treegrid + "){");
  sb.append("if(data.total && data.rows.length==0) {");
  sb.append("var grid = $(\'#" + name + "\');");
  sb.append("var curr = grid.datagrid(\'getPager\').data(\"pagination\").options.pageNumber;");
  sb.append("grid.datagrid({pageNumber:(curr-1)});}}");
  sb.append(" try{loadAjaxDict(data);}catch(e){}");
  if (hasGroup) {
    sb.append("optsMenuToggle('" + name + "');");
  }
  if (StringUtil.isNotEmpty(onLoadSuccess)) {
    sb.append(onLoadSuccess + "(data);");
  }
  sb.append("},");
  if (StringUtil.isNotEmpty(onDblClick)) {
    sb.append("onDblClickRow:function(rowIndex,rowData){" + onDblClick + "(rowIndex,rowData);},");
  }
  if (treegrid) {
    sb.append("onClickRow:function(rowData){");
  }
 else {
    sb.append("onClickRow:function(rowIndex,rowData){");
  }
  sb.append("rowid=rowData.id;");
  sb.append("gridname=\'" + name + "\';");
  if (StringUtil.isNotEmpty(onClick)) {
    if (treegrid) {
      sb.append("" + onClick + "(rowData);");
    }
 else {
      sb.append("" + onClick + "(rowIndex,rowData);");
    }
  }
  sb.append("}");
  sb.append("});");
  this.setPager(sb,grid);
  sb.append("try{restoreheader();}catch(ex){}");
  sb.append("});");
  sb.append("function reloadTable(){");
  sb.append("try{");
  sb.append("	$(\'#\'+gridname).datagrid(\'reload\');");
  sb.append("	$(\'#\'+gridname).treegrid(\'reload\');");
  sb.append("}catch(ex){}");
  sb.append("}");
  sb.append("function reload" + name + "(){" + "$(\'#" + name + "\')." + grid + "(\'reload\');" + "}");
  sb.append("function get" + name + "Selected(field){return getSelected(field);}");
  sb.append("function getSelected(field){" + "var row = $(\'#\'+gridname)." + grid + "(\'getSelected\');" + "if(row!=null)" + "{" + "value= row[field];" + "}" + "else" + "{" + "value=\'\';" + "}" + "return value;" + "}");
  sb.append("function get" + name + "Selections(field){" + "var ids = [];" + "var rows = $(\'#" + name + "\')." + grid + "(\'getSelections\');" + "for(var i=0;i<rows.length;i++){" + "ids.push(rows[i][field]);" + "}" + "ids.join(\',\');" + "return ids" + "};");
  sb.append("function getSelectRows(){");
  sb.append("	return $(\'#" + name + "\').datagrid('getChecked');");
  sb.append("}");
  sb.append(" function saveHeader(){");
  sb.append(" var columnsFields =null;var easyextends=false;try{columnsFields = $('#" + name + "').datagrid('getColumns');easyextends=true;");
  sb.append("}catch(e){columnsFields =$('#" + name + "').datagrid('getColumnFields');}");
  sb.append("	var cols = storage.get( '" + name + "hiddenColumns');var init=true;	if(cols){init =false;} " + "var hiddencolumns = [];for(var i=0;i< columnsFields.length;i++) {if(easyextends){");
  sb.append("hiddencolumns.push({field:columnsFields[i].field,hidden:columnsFields[i].hidden});}else{");
  sb.append(" var columsDetail = $('#" + name + "').datagrid(\"getColumnOption\", columnsFields[i]); ");
  sb.append("if(init){hiddencolumns.push({field:columsDetail.field,hidden:columsDetail.hidden,visible:(columsDetail.hidden==true?false:true)});}else{");
  sb.append("for(var j=0;j<cols.length;j++){");
  sb.append("		if(cols[j].field==columsDetail.field){");
  sb.append("					hiddencolumns.push({field:columsDetail.field,hidden:columsDetail.hidden,visible:cols[j].visible});");
  sb.append("		}");
  sb.append("}");
  sb.append("}} }");
  sb.append("storage.set( '" + name + "hiddenColumns',JSON.stringify(hiddencolumns));");
  sb.append("}");
  sb.append(" function isShowBut(){");
  sb.append("	  var isShowSearchId = $(\'#isShowSearchId\').val();");
  sb.append("	  if(isShowSearchId == \"true\"){");
  sb.append("		  $(\"#searchColums\").hide();");
  sb.append("	  	  $(\'#isShowSearchId\').val(\"false\");");
  sb.append("	  	  $(\'#columsShow\').remove(\"src\");");
  sb.append("	  	  $(\'#columsShow\').attr(\"src\",\"plug-in/easyui/themes/default/images/accordion_expand.png\");");
  sb.append("	  } else{");
  sb.append("		  $(\"#searchColums\").show();");
  sb.append("	  	  $(\'#isShowSearchId\').val(\"true\");");
  sb.append("	  	  $(\'#columsShow\').remove(\"src\");");
  sb.append("	  	  $(\'#columsShow\').attr(\"src\",\"plug-in/easyui/themes/default/images/accordion_collapse.png\");");
  sb.append("	  }");
  sb.append("}");
  sb.append("function restoreheader(){");
  sb.append("var cols = storage.get( '" + name + "hiddenColumns');if(!cols)return;");
  sb.append("for(var i=0;i<cols.length;i++){");
  sb.append("	try{");
  sb.append("if(cols.visible!=false)$('#" + name + "').datagrid((cols[i].hidden==true?'hideColumn':'showColumn'),cols[i].field);");
  sb.append("}catch(e){");
  sb.append("}");
  sb.append("}");
  sb.append("}");
  sb.append("function resetheader(){");
  sb.append("var cols = storage.get( '" + name + "hiddenColumns');if(!cols)return;");
  sb.append("for(var i=0;i<cols.length;i++){");
  sb.append("	try{");
  sb.append("  $('#" + name + "').datagrid((cols.visible==false?'hideColumn':'showColumn'),cols[i].field);");
  sb.append("}catch(e){");
  sb.append("}");
  sb.append("}");
  sb.append("}");
  if (columnList.size() > 0) {
    sb.append("function " + name + "search(){");
    sb.append("try { if(! $(\"#" + name + "Form\").Validform({tiptype:3}).check()){return false;} } catch (e){}");
    sb.append("if(true){");
    sb.append("var queryParams=$(\'#" + name + "\').datagrid('options').queryParams;");
    sb.append("$(\'#" + name + "tb\').find('*').each(function(){queryParams[$(this).attr('name')]=$(this).val();});");
    sb.append("$(\'#" + name + "\')." + grid + "({url:'" + actionUrl + "&field=" + searchFields + "',pageNumber:1});" + "}}");
    sb.append("function dosearch(params){");
    sb.append("var jsonparams=$.parseJSON(params);");
    sb.append("$(\'#" + name + "\')." + grid + "({url:'" + actionUrl + "&field=" + searchFields + "',queryParams:jsonparams});" + "}");
    searchboxFun(sb,grid);
    sb.append("function EnterPress(e){");
    sb.append("var e = e || window.event;");
    sb.append("if(e.keyCode == 13){ ");
    sb.append(name + "search();");
    sb.append("}}");
    sb.append("function searchReset(name){");
    sb.append(" $(\"#\"+name+\"tb\").find(\":input\").val(\"\");");
    sb.append("var queryParams=$(\'#" + name + "\').datagrid('options').queryParams;");
    sb.append("$(\'#" + name + "tb\').find('*').each(function(){queryParams[$(this).attr('name')]=$(this).val();});");
    sb.append("$(\'#" + name + "tb\').find(\"input[type='checkbox']\").each(function(){$(this).attr('checked',false);});");
    sb.append("$(\'#" + name + "tb\').find(\"input[type='radio']\").each(function(){$(this).attr('checked',false);});");
    sb.append("$(\'#" + name + "\')." + grid + "({url:'" + actionUrl + "&field=" + searchFields + "',pageNumber:1});");
    sb.append("}");
  }
  if (oConvertUtils.isNotEmpty(complexSuperQuery)) {
    sb.append("function " + name + "SuperQuery(queryCode){if(typeof(windowapi)=='undefined'){$.dialog({content:'url:superQueryMainController.do?dialog&code='+queryCode+'&tableName=" + name + "',width:880,height:400,zIndex:getzIndex(),title:'???????',cache:false,lock:true})}else{$.dialog({content:'url:superQueryMainController.do?dialog&code='+queryCode+'&tableName=" + name + "',width:880,height:400,zIndex:getzIndex(),title:title,cache:false,lock:true,parent:windowapi})}};");
  }
  getFilterFields(sb);
  sb.append("</script>");
  sb.append("<table width=\"100%\"   id=\"" + name + "\" toolbar=\"#" + name + "tb\"></table>");
  sb.append("<div id=\"" + name + "tb\" style=\"padding:3px; height: auto\">");
  if (hasQueryColum(columnList) && isShowSearch == true) {
    sb.append("<input  id=\"columsShow\" type=\"image\" src=\"plug-in/easyui/themes/default/images/accordion_collapse.png\" onclick=\"isShowBut()\">");
  }
  boolean blink=false;
  sb.append("<input  id=\"_complexSqlbuilder\" name=\"complexSqlbuilder\"   type=\"hidden\" />");
  if (hasQueryColum(columnList) && "group".equals(getQueryMode())) {
    blink=true;
    String searchColumStyle=toolBarList != null && toolBarList.size() != 0 ? "" : "style='border-bottom: 0px'";
    sb.append("<div name=\"searchColums\" id=\"searchColums\" " + searchColumStyle + ">");
    sb.append("<input  id=\"isShowSearchId\" type=\"hidden\" value=\"" + isShowSearch + "\"/>");
    sb.append("<input  id=\"_sqlbuilder\" name=\"sqlbuilder\"   type=\"hidden\" />");
    sb.append("<form onkeydown='if(event.keyCode==13){" + name + "search();return false;}' id='" + name + "Form'>");
    sb.append("<span style=\"max-width: 79%;display: inline-block;\">");
    sb.append("<span><img style=\"margin-top:-3px;vertical-align:middle;\" src=\"plug-in/easyui/themes/icons/ti.png\"  title=\"??????????: *??????????? ',' ???\" alt=\"??????????: *??????????? ',' ???\" /></span>");
    getSearchFormInfo(sb);
    sb.append("</span>");
    sb.append("<span>");
    getSearchButton(sb);
    sb.append("</span>");
    sb.append("</form></div>");
  }
 else   if (hasQueryColum(columnList) && "advanced".equals(getQueryMode())) {
    blink=true;
    String searchColumStyle=toolBarList != null && toolBarList.size() != 0 ? "" : "style='border-bottom: 0px'";
    sb.append("<div name=\"searchColums\" style=\"display:none;\" id=\"searchColums\" " + searchColumStyle + ">");
    sb.append("<input  id=\"isShowSearchId\" type=\"hidden\" value=\"" + isShowSearch + "\"/>");
    sb.append("<input  id=\"_sqlbuilder\" name=\"sqlbuilder\"   type=\"hidden\" />");
    sb.append("<form onkeydown='if(event.keyCode==13){" + name + "search();return false;}' id='" + name + "Form'>");
    sb.append("<span style=\"max-width: 79%;display: inline-block;\">");
    getSearchFormInfo(sb);
    sb.append("</span>");
    sb.append("<span>");
    getSearchButton(sb);
    sb.append("</span>");
    sb.append("</form></div>");
  }
  if (toolBarList == null || toolBarList.size() == 0) {
    sb.append("<div style=\"height:0px;\" >");
  }
 else {
    sb.append("<div style=\"border-bottom-width:0;\" class=\"datagrid-toolbar\">");
  }
  sb.append("<span style=\"float:left;\" >");
  if (toolBarList.size() > 0) {
    Boolean hasMore=false;
    for (    DataGridUrl toolBar : toolBarList) {
      if (toolBar.isInGroup()) {
        if (!hasMore) {
          hasMore=true;
        }
      }
 else {
        loadToolbar(toolBar,sb);
      }
    }
    if (hasMore) {
      loadToolbarMoreBtn(sb,true,null);
      sb.append("<div class='toolbar-more-container'><ul class='toolbar-more-list'>");
      for (      DataGridUrl toolBar : toolBarList) {
        if (toolBar.isInGroup()) {
          sb.append("<li>");
          loadToolbarMoreBtn(sb,false,toolBar);
          sb.append("</li>");
        }
      }
      sb.append("</ul></div>");
    }
  }
  sb.append("</span>");
  if ("single".equals(getQueryMode()) && hasQueryColum(columnList)) {
    sb.append("<span style=\"float:right\">");
    sb.append("<input id=\"" + name + "searchbox\" class=\"easyui-searchbox\"  data-options=\"searcher:" + name + StringUtil.replaceAll("searchbox,prompt:\'{0}\',menu:\'#","{0}",MutiLangUtil.getLang("common.please.input.keyword")) + name + "mm\'\"></input>");
    sb.append("<div id=\"" + name + "mm\" style=\"width:120px\">");
    for (    DataGridColumn col : columnList) {
      if (col.isQuery()) {
        sb.append("<div data-options=\"name:\'" + col.getField().replaceAll("_","\\.") + "\',iconCls:\'icon-ok\' \">" + col.getTitle() + "</div>  ");
      }
    }
    sb.append("</div>");
    sb.append("</span>");
  }
 else   if ("advanced".equals(getQueryMode()) && hasQueryColum(columnList)) {
    sb.append("<span style=\"float:right\">");
    if (btnCls != null && !btnCls.equals("easyui")) {
      if (btnCls.indexOf("bootstrap") == 0) {
        String defalutCls="btn btn-info btn-xs";
        if (btnCls.replace("bootstrap","").trim().length() > 0) {
          defalutCls=btnCls.replace("bootstrap","").trim();
        }
        if (superQuery) {
          sb.append("<button class=\"" + defalutCls + "\"  type=\"button\" onclick=\"queryBuilder()\">");
          sb.append("<i class=\"fa fa-search\"></i>");
          sb.append("<span class=\"bigger-110 no-text-shadow\">" + MutiLangUtil.getLang("common.superquery") + "</span>");
          sb.append("</button>");
          sb.append("</span>");
        }
      }
 else {
        if (superQuery) {
          sb.append("<a href=\"#\" class=\"" + btnCls + "\" onclick=\"queryBuilder('" + StringUtil.replaceAll("')\">{0}</a>","{0}",MutiLangUtil.getLang("common.superquery")));
        }
      }
    }
 else {
      if (superQuery) {
        sb.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"queryBuilder('" + StringUtil.replaceAll("')\">{0}</a>","{0}",MutiLangUtil.getLang("common.superquery")));
      }
    }
    sb.append("</span>");
  }
  sb.append("<div style=\"clear:both\"></div>");
  sb.append("</div>");
  if (blink) {
    sb.insert(0,"<link rel=\"stylesheet\" href=\"plug-in/Validform/css/style.css\" type=\"text/css\">" + "<link rel=\"stylesheet\" href=\"plug-in/Validform/css/tablefrom.css\" type=\"text/css\">" + "<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js\"></script>" + "<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_Datatype_zh-cn.js\"></script>" + "<script type=\"text/javascript\" src=\"plug-in/Validform/js/datatype_zh-cn.js\"></script>");
  }
  if (queryBuilder) {
    if (btnCls != null && !btnCls.equals("easyui")) {
      addQueryBuilder(sb,btnCls);
    }
 else {
      addQueryBuilder(sb,"easyui-linkbutton");
    }
  }
  if (superQuery) {
    if (btnCls != null && !btnCls.equals("easyui")) {
      addSuperQuery(sb,btnCls,columnList);
    }
 else {
      addSuperQuery(sb,"easyui-linkbutton",columnList);
    }
  }
  if (oConvertUtils.isNotEmpty(complexSuperQuery)) {
    if (btnCls != null && !btnCls.equals("easyui")) {
      addAdvancedQuery(sb,btnCls);
    }
 else {
      addAdvancedQuery(sb,"easyui-linkbutton");
    }
  }
  this.getFilter(sb);
  return sb;
}
