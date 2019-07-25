public StringBuffer end(){
  StringBuffer nsb=new StringBuffer();
  nsb.append("<script type=\"text/javascript\">");
  nsb.append("$(document).ready(function() {").append("$(\"#" + name + "\").autocomplete(\"" + dataSource + "\",{").append("max: 5,minChars: " + minLength + ",width: 200,scrollHeight: 100,matchContains: true,autoFill: false,extraParams:{").append("featureClass : \"P\",style : \"full\",	maxRows : " + maxRows + ",labelField : \"" + searchField + "\",valueField : \"" + searchField + "\",").append("searchField : \"" + searchField + "\",entityName : \"" + entityName + "\",trem: getTremValue" + name + "}");
  if (StringUtil.isNotEmpty(parse)) {
    nsb.append(",parse:function(data){return " + parse + ".call(this,data);}");
  }
 else {
    nsb.append(",parse:function(data){return jeecgAutoParse.call(this,data);}");
  }
  if (StringUtil.isNotEmpty(formatItem)) {
    nsb.append(",formatItem:function(row, i, max){return " + formatItem + ".call(this,row, i, max);} ");
  }
 else {
    nsb.append(",formatItem:function(row, i, max){return row['" + searchField + "'];}");
  }
  nsb.append("}).result(function (event, row, formatted) {");
  if (StringUtil.isNotEmpty(result)) {
    nsb.append(result + ".call(this,row); ");
  }
 else {
    nsb.append("$(\"#" + name + "\").val(row['" + searchField + "']);");
  }
  nsb.append("}); });").append("function getTremValue" + name + "(){return $(\"#" + name + "\").val();}").append("</script>").append("<input type=\"text\" id=\"" + name + "\" name=\"" + name + "\" ");
  if (oConvertUtils.isNotEmpty(datatype)) {
    nsb.append("datatype=\"" + datatype + "\" nullmsg=\"" + nullmsg + "\" errormsg=\"" + errormsg + "\" ");
  }
  nsb.append("/>");
  if (StringUtil.isNotEmpty(defValue)) {
    nsb.append(" value=" + defValue + " readonly=true");
  }
  return nsb;
}
