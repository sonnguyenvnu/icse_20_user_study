public StringBuffer end(){
  StringBuffer sb=new StringBuffer();
  width=(width == null) ? "140" : width;
  sb.append("<script type=\"text/javascript\">" + "$(function() { " + "$(\'#" + id + "\').combotree({		 " + "url :\'" + url + "\'," + "width :\'" + width + "\'," + "multiple:" + multiple + "," + "onlyLeafCheck:" + onlyLeafCheck + "," + "onLoadSuccess:function(){$(\'#" + id + "\').combotree('tree').tree('expandAll')}" + "});		" + "});	" + "</script>");
  sb.append("<input  name=\"" + name + "\" id=\"" + id + "\" ");
  if (value != null) {
    sb.append("value=" + value + "");
  }
  sb.append(">");
  return sb;
}
