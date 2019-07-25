public StringBuffer end(){
  StringBuffer sb=this.getTagCache();
  if (sb != null) {
    return sb;
  }
  sb=new StringBuffer();
  if (iframe) {
    sb.append("<script type=\"text/javascript\">");
    sb.append("$(function(){");
    if (tabList.size() > 0) {
      for (      Tab tab : tabList) {
        sb.append("add" + id + "(\'" + tab.getTitle() + "\',\'" + tab.getHref() + "\',\'" + tab.getId() + "\',\'" + tab.getIcon() + "\',\'" + tab.isClosable() + "\');");
      }
    }
    sb.append("function add" + id + "(title,url,id,icon,closable) {");
    sb.append("$(\'#" + id + "\').tabs(\'add\',{");
    sb.append("id:id,");
    sb.append("title:title,");
    if (iframe) {
      sb.append("content:createFrame" + id + "(id),");
    }
 else {
      sb.append("href:url,");
    }
    sb.append("closable:closable=(closable =='false')?false : true,");
    sb.append("icon:icon");
    sb.append("});");
    sb.append("}");
    sb.append("$(\'#" + id + "\').tabs(");
    sb.append("{");
    sb.append("onSelect : function(title) {");
    sb.append("var p = $(this).tabs(\'getTab\', title);");
    if (tabList.size() > 0) {
      for (      Tab tab : tabList) {
        sb.append("if (title == \'" + tab.getTitle() + "\') {");
        sb.append("p.find(\'iframe\').attr(\'src\',");
        sb.append("\'" + tab.getHref() + "\');}");
      }
    }
    sb.append("}");
    sb.append("});");
    sb.append("function createFrame" + id + "(id)");
    sb.append("{");
    sb.append("var s = \'<iframe id=\"+id+\" scrolling=\"no\" frameborder=\"0\"  src=\"about:blank\" width=\"" + oConvertUtils.getString(width,"100%") + "\" height=\"" + oConvertUtils.getString(heigth,"99.5%") + "\"></iframe>\';");
    sb.append("return s;");
    sb.append("}");
    sb.append("});");
    sb.append("</script>");
  }
  if (tabs) {
    sb.append("<div id=\"" + id + "\" tabPosition=\"" + tabPosition + "\" border=flase style=\"margin:0px;padding:0px;overflow-x:hidden;width:" + oConvertUtils.getString(width,"auto") + ";\" class=\"easyui-tabs\" fit=\"" + fit + "\">");
    if (!iframe) {
      for (      Tab tab : tabList) {
        if (tab.getHref() != null) {
          sb.append("<div title=\"" + tab.getTitle() + "\" " + (tab.getIcon() != null ? ("iconCls=\"" + tab.getIcon() + "\" ") : "") + " href=\"" + tab.getHref() + "\" style=\"margin:0px;padding:0px;overflow-x:hidden;overflow-y:auto;width=auto;\"></div>");
        }
 else {
          sb.append("<div " + (tab.getIcon() != null ? ("iconCls=\"" + tab.getIcon() + "\" ") : "") + " title=\"" + tab.getTitle() + "\"  style=\"margin:0px;padding:0px;overflow-x:hidden;overflow-y:auto;width=auto;\">");
          sb.append("<iframe id=\"" + tab.getId() + "\" scrolling=\"no\" frameborder=\"0\"  src=\"" + tab.getIframe() + "\" width=\"" + oConvertUtils.getString(tab.getWidth(),"100%") + "\" height=\"" + oConvertUtils.getString(tab.getHeigth(),"99.5%") + "\"></iframe>");
          sb.append("</div>");
        }
      }
    }
    sb.append("</div>");
  }
  this.putTagCache(sb);
  return sb;
}
