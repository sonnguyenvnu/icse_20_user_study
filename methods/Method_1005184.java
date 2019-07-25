/** 
 * datatables????
 * @return
 */
public StringBuffer datatables(){
  StringBuffer sb=new StringBuffer();
  sb.append("<link href=\"plug-in/hplus/css/plugins/dataTables/dataTables.bootstrap.css\" rel=\"stylesheet\">");
  sb.append("<script src=\"plug-in/hplus/js/plugins/dataTables/jquery.dataTables.js\"></script>");
  sb.append("<script type=\"text/javascript\">");
  sb.append("$(document).ready(function() {");
  sb.append("var oTable = $(\'#userList\').dataTable({");
  sb.append("\"bProcessing\" : true,");
  sb.append("\"bPaginate\" : true,");
  sb.append("\"sPaginationType\" : \"full_numbers\",");
  sb.append("\"bFilter\" : true,");
  sb.append("\"bSort\" : true, ");
  sb.append("\"bAutoWidth\" : true,");
  sb.append("\"bLengthChange\" : true,");
  sb.append("\"bInfo\" : true,");
  sb.append("\"sAjaxSource\" : \"" + actionUrl + "&field=" + fields + "\",");
  sb.append("\"bServerSide\" : true,");
  sb.append("\"oLanguage\" : {" + "\"sLengthMenu\" : \" _MENU_ ???\"," + "\"sZeroRecords\" : \"???????\"," + "\"sInfo\" : \"? _START_ ? _END_ ??? ? _TOTAL_ ?\"," + "\"sInfoEmtpy\" : \"????\"," + "\"sProcessing\" : \"??????...\"," + "\"sSearch\" : \"??\"," + "\"oPaginate\" : {" + "\"sFirst\" : \"??\"," + "\"sPrevious\" : \"??\", " + "\"sNext\" : \"??\"," + "\"sLast\" : \"??\"" + "}" + "},");
  sb.append("\"fnServerData\" : function(sSource, aoData, fnCallback, oSettings) {");
  sb.append("oSettings.jqXHR = $.ajax({" + "\"dataType\" : \'json\'," + "\"type\" : \"POST\"," + "\"url\" : sSource," + "\"data\" : aoData," + "\"success\" : fnCallback" + "});},");
  sb.append("\"aoColumns\" : [ ");
  int i=0;
  for (  DataGridColumn column : columnList) {
    i++;
    sb.append("{");
    sb.append("\"sTitle\":\"" + column.getTitle() + "\"");
    if (column.getField().equals("opt")) {
      sb.append(",\"mData\":\"" + idField + "\"");
      sb.append(",\"sWidth\":\"20%\"");
      sb.append(",\"bSortable\":false");
      sb.append(",\"bSearchable\":false");
      sb.append(",\"mRender\" : function(data, type, rec) {");
      this.getOptUrl(sb);
      sb.append("}");
    }
 else {
      int colwidth=(column.getWidth() == null) ? column.getTitle().length() * 15 : column.getWidth();
      sb.append(",\"sName\":\"" + column.getField() + "\"");
      sb.append(",\"mDataProp\":\"" + column.getField() + "\"");
      sb.append(",\"mData\":\"" + column.getField() + "\"");
      sb.append(",\"sWidth\":\"" + colwidth + "\"");
      sb.append(",\"bSortable\":" + column.isSortable() + "");
      sb.append(",\"bVisible\":" + !column.isHidden() + "");
      sb.append(",\"bSearchable\":" + column.isQuery() + "");
    }
    sb.append("}");
    if (i < columnList.size())     sb.append(",");
  }
  sb.append("]" + "});" + "});" + "</script>");
  sb.append("<table width=\"100%\"  class=\"" + style + "\" id=\"" + name + "\" toolbar=\"#" + name + "tb\"></table>");
  return sb;
}
